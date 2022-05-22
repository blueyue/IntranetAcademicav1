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

import com.ppcr.sistema.IService.ICarrera;
import com.ppcr.sistema.model.Carrera;


@Controller
@RequestMapping("/carreras")
public class CarreraController {
	@Autowired
	private ICarrera service;
	
	@RequestMapping(value={"/listar"},method = RequestMethod.GET)//,"/listabase/{param}"
	public String lista(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		model.addAttribute("titulo","Listado de Carreras");
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<Carrera> pageCarrera = null;
		
		if (param == null || param=="" ) {
			
			pageCarrera = service.GetAll(pageRequest);
			
		}else {
			try {
				pageCarrera = service.GetbyNomCar(param,pageRequest);
			}catch(Exception ex){
				return "redirect:/carreras/listar";
			}
		}
		
		int totalPage = pageCarrera.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		
		model.addAttribute("carreras", pageCarrera.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/carreras/listar";
		
	}
	@RequestMapping(value="/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}

		
		Carrera carreras = new Carrera();
		model.addAttribute("titulo","Agregar Nueva Carrera");
		model.addAttribute("carreras",carreras);
		return "/views/carreras/crear";	
	}	
	
	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute Carrera carreras) {
		
		service.guardarCarrera(carreras);
		return "redirect:/carreras/listar";
	}	
	
	@GetMapping("/editar/{id}")
	public String Editar(@PathVariable("id") Integer idcarrera, Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}

		
		Carrera carreras=service.buscarPorIdCarrera(idcarrera);
		
		model.addAttribute("titulo", "Editar carreras");
		model.addAttribute("carreras", carreras);
		
		return "/views/carreras/crear";
	}
	

	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Integer idcarrera,HttpSession response) {

		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		service.eliminarCarrera(idcarrera);
		return "redirect:/carreras/listar";
	}	
	
	
}
