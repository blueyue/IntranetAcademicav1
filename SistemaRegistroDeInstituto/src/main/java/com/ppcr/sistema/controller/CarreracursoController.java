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

import com.ppcr.sistema.IService.ICarcur;
import com.ppcr.sistema.IService.ICarrera;
import com.ppcr.sistema.IService.ICurso;
import com.ppcr.sistema.model.Carcur;
import com.ppcr.sistema.model.Carrera;
import com.ppcr.sistema.model.Curso;


@Controller
@RequestMapping("/cc")
public class CarreracursoController {

	@Autowired
	private ICarcur service;
	
	@Autowired
	private ICarrera carreraservice;
	
	@Autowired
	private ICurso cursoservice;
	

	@GetMapping(value="/listar")
	public String findAll(@RequestParam Map<String, Object> params, Model model,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
	
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<Carcur> pageCarcur = service.getAll(pageRequest);

		int totalPage = pageCarcur.getTotalPages();
		if(totalPage > 0) {
	
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
	
			model.addAttribute("pages", pages);
		}
		

		model.addAttribute("carreracurso", pageCarcur.getContent());

		model.addAttribute("current", page + 1);
	
		model.addAttribute("next", page + 2);
	
		model.addAttribute("prev", page);
	
		model.addAttribute("last", totalPage);
		return "views/carreracurso/listar";
	}	
	
	@RequestMapping(value="/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession response) {
		
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		Carcur carcur = new Carcur();
		List<Carrera> listadocarrera=carreraservice.listarTodosCarrera();
		List<Curso> listadocurso=cursoservice.listarTodosCursos();
		
		model.addAttribute("titulo","Agregar relacion Carrera Curso");
		model.addAttribute("carcur",carcur);
		model.addAttribute("lstCarrera", listadocarrera);	
		model.addAttribute("lstCurso", listadocurso);	
		return "views/carreracurso/crear";
		
	}	
	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute Carcur carcur) {		
		service.guardar(carcur);
		return "redirect:/cc/listar";
	}	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer idcc, Model model,HttpSession response) {
		
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		Carcur carcur=service.buscarPorId(idcc);
		
		List<Carrera> listadocarrera=carreraservice.listarTodosCarrera();
		List<Curso> listadocurso=cursoservice.listarTodosCursos();		
		model.addAttribute("titulo", "Editar relacion carrera curso");
		model.addAttribute("carcur",carcur);
		model.addAttribute("lstCarrera", listadocarrera);	
		model.addAttribute("lstCurso", listadocurso);		
		
		return "views/carreracurso/crear";		
	}	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer idcc,HttpSession response) {
		
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		service.eliminar(idcc);
		return "redirect:/cc/listar";
	}	
	
	
	
}
