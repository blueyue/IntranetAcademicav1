package com.ppcr.sistema.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.IUsuario;
import com.ppcr.sistema.model.Usuario;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuario service;
	
	//
	@RequestMapping(value={"/listar"},method = RequestMethod.GET)
	public String listar(Model model,@RequestParam(value = "param",required=false) String param,
			@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
	
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 6);
		
		Page<Usuario> pageUsuarios = null;
		
		if (param == null || param=="" ) {
			
			pageUsuarios = service.listarTodos(pageRequest);
			
		}else {
			try {
				pageUsuarios = service.GetbyUser(param,pageRequest);
			}catch(Exception ex){
				return "redirect:/usuario/listar";
			}
		}
		
		int totalPage = pageUsuarios.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("usuarios", pageUsuarios.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/usuarios/listar";
		
	}
	
	
	@RequestMapping(value="/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		request.setAttribute("idadmin", response.getAttribute("idadmin").toString());
		
		Usuario usuario = new Usuario();
		model.addAttribute("titulo","Agregar Usuario");
		model.addAttribute("modusuario",usuario);
		return "/views/usuarios/crear";	
	}

	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute Usuario usuario) {
		try {
			service.guardar(usuario);
			return "redirect:/usuario/listar";
		}catch (Exception ex) {
			return "redirect:/intranet/login";
		}
		
	}	
	
	
	@RequestMapping(value="/edit-estado",method = RequestMethod.GET)//@PathVariable("id") int id
	public String EditEstado(HttpSession response,
			@RequestParam(value = "kfu") int id,
			@RequestParam(value = "est") Integer est) {

		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		try {
		
			service.ChangeStated(id,est);
			return "redirect:/usuario/listar";
		}catch (Exception ex) {
			//return "";
			return "redirect:/intranet/login";
		}
		
	}	
	
	@RequestMapping(value={"/mydata"},method = RequestMethod.GET)
	public String UserDataAlumno (Model model, HttpSession response){
		if (response.getAttribute("idalum")==null) {
			return "redirect:/intranet/login";
		}
		model.addAttribute("titulo","Mis Datos");
		model.addAttribute("user",response.getAttribute("user"));
		
		return "views/alumnos/ver";
	}
	@RequestMapping(value="/editaruser/{id}",method = RequestMethod.GET)
	public String editaruser (Model model,HttpSession response,@PathVariable("id") Integer id) {
		if (response.getAttribute("idalum")==null) {
			return "redirect:/intranet/login";
		}
		
		
		Optional<Usuario> usuario = service.buscarPorId(id);
		model.addAttribute("titulo","Editar Usuario");
		model.addAttribute("usuario",usuario);
		return "/views/usuarios/editalumno";	
	}
	@PostMapping(value="/guardaredit")
	public String GuardarEdit(@ModelAttribute Usuario usuario) {
		try {
			service.guardareditbyalum(usuario);
			return "redirect:/usuario/mydata";
		}catch (Exception ex) {
			return "redirect:/intranet/login";
		}
		
	}
}
