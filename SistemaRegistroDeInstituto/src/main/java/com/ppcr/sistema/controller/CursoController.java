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

import com.ppcr.sistema.IService.ICurso;
import com.ppcr.sistema.model.Curso;



@Controller
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private ICurso service;
	
	
	@RequestMapping(value={"/listar"},method = RequestMethod.GET)//,"/listabase/{param}"
	public String lista(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		model.addAttribute("titulo","Listado de Cursos");
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<Curso> pageCurso = null;
		
		if (param == null || param=="" ) {
			
			pageCurso = service.GetAll(pageRequest);
			
		}else {
			try {
				pageCurso = service.GetbyNomCur(param,pageRequest);
			}catch(Exception ex){
				return "redirect:/cursos/listar";
			}
		}
		
		int totalPage = pageCurso.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		
		model.addAttribute("cursos", pageCurso.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/cursos/listar";
		
	}
	
	@RequestMapping(value="/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		
		Curso cursos = new Curso();
		List<Curso> listado=service.listarTodosCursos();
		
		model.addAttribute("titulo","Agregar Cursos");
		model.addAttribute("cursos",cursos);
		model.addAttribute("lstCurso", listado);	
		return "/views/cursos/editar";
		
	}	
	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute Curso cursos) {		
		service.guardarCursos(cursos);
		return "redirect:/cursos/listar";
	}	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long idcurso, Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		
		Curso cursos=service.buscarPorIdCursos(idcurso);
		
		List<Curso> listado=service.listarTodosCursos();		
		model.addAttribute("titulo", "Editar curso");
		model.addAttribute("cursos", cursos);
		model.addAttribute("lstCurso", listado);	
		
		return "/views/cursos/editar";		
	}	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long idcurso,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		service.eliminarCursos(idcurso);
		return "redirect:/cursos/listar";
	}	
	
	
	
}
