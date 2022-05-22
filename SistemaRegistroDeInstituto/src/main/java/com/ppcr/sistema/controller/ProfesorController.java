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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.IProfesor;
import com.ppcr.sistema.model.Profesor;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

	@Autowired
	private IProfesor service;
	
	
	@RequestMapping(value={"/listar"},method = RequestMethod.GET)//,"/listabase/{param}"
	public String lista(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		model.addAttribute("titulo","Listado de Profesores");
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<Profesor> pageProfesor = null;
		
		if (param == null || param=="" ) {
			
			pageProfesor = service.GetAll(pageRequest);
			
		}else {
			try {
				pageProfesor = service.GetbyApe(param,pageRequest);
			}catch(Exception ex){
				return "redirect:/profesores/listar";
			}
		}
		
		int totalPage = pageProfesor.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		
		model.addAttribute("profes", pageProfesor.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/profesores/listar";
		
	}
	
	@RequestMapping(value = "/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}

		
		model.addAttribute("profes", new Profesor());
		return "/views/profesores/crear";
	}
	
	@PostMapping("/guardar")
	public String guardar(Model model,@Validated Profesor p) {
		service.guardar(p);
		return "redirect:/profesores/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
	
		
		Optional<Profesor>profesor=service.BuscarPorId(id);
		model.addAttribute("profes",profesor);
		return "/views/profesores/editar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(Model model,@PathVariable int id,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		service.eliminar(id);
		return "redirect:/profesores/listar";
	}
	

}
