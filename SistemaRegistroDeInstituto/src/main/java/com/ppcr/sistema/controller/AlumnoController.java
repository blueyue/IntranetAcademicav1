package com.ppcr.sistema.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.IAlumno;
import com.ppcr.sistema.model.Alumno;


@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	
	@Autowired
	private IAlumno service;

	
	@RequestMapping(value={"/listar"},method = RequestMethod.GET)
	public String listageneral(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}

		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 7);
		
		Page<Alumno> pageAlumnoUser = null;
		
		if (param == null || param =="") {
			
			pageAlumnoUser = service.GetAllGnl(pageRequest);
			
		}else {
			try {
				pageAlumnoUser = service.GetbyApeGnl(pageRequest,param);
			}catch(Exception ex){
				return "redirect:/alumno/listar";
			}
		}
		
		int totalPage = pageAlumnoUser.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("titulo","Listado de Alumnos");
		model.addAttribute("alumnos", pageAlumnoUser.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "/views/alumnos/listar";
		
	}
	
	@RequestMapping(value="/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		request.setAttribute("idadmin", response.getAttribute("idadmin").toString());
		Alumno alumno = new Alumno();
		model.addAttribute("titulo","Agregar Alumno");
		model.addAttribute("alumno",alumno);
		return "/views/alumnos/crear";	
	}	
	
	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute Alumno alumno) {
		try {
			service.guardar(alumno);
			return "redirect:/alumno/listar";
		}catch (Exception ex) {
			return "redirect:/intranet/login";
		}
		
	}	
	
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long idalumno, Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		request.setAttribute("idadmin", response.getAttribute("idadmin").toString());
		
		Alumno alumno=service.buscarPorId(idalumno);
	
		model.addAttribute("titulo", "Editar Alumnos");
		model.addAttribute("alumno", alumno);
		
		
		return "/views/alumnos/crear";
		
	}
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long idalumno,HttpSession response) {

		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		service.eliminar(idalumno);
		return "redirect:/listar-alumnos";
	}	
	
	//a pedido
	
	@RequestMapping(value={"/listadouser"},method = RequestMethod.GET)
	public String listabase(Model model,@RequestParam(value = "paramuser",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		request.setAttribute("idadmin", response.getAttribute("idadmin").toString());
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<Alumno> pageAlumnoUser = null;
		
		if (param == null || param =="") {
			
			pageAlumnoUser = service.GetAll(pageRequest);
			
		}else {
			try {
				pageAlumnoUser = service.GetbyApe(pageRequest,param);
			}catch(Exception ex){
				return "redirect:/alumno/listadouser";
			}
		}
		
		int totalPage = pageAlumnoUser.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("alumnos", pageAlumnoUser.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/usuarios/listarbyalumnos";
		
	}
}
