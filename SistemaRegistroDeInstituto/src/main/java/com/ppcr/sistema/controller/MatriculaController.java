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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.ICarrera;
import com.ppcr.sistema.IService.IDetalleCurso;
import com.ppcr.sistema.IService.IMatricula;
import com.ppcr.sistema.model.Carrera;
import com.ppcr.sistema.model.DetalleCurso;
import com.ppcr.sistema.model.Matricula;


@Controller
@RequestMapping("/matriculas")
public class MatriculaController {

	
	@Autowired
	private IMatricula sm;
	
	@Autowired
	private ICarrera scr;
	
	@Autowired
	private IDetalleCurso sdetc;
	
	
	@RequestMapping(value={"/verificacion"},method = RequestMethod.GET)
	public String listarEnAdmin(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 7);
		
		Page<Matricula> pageMatriculas = null;
		
		if (param == null || param =="") {
			
			pageMatriculas = sm.GetAll(pageRequest);
			
		}else {
			try {
				pageMatriculas = sm.GetAllbyCodAlum(pageRequest,param);
			}catch(Exception ex){
				return "redirect:/alumno/listar";
			}
		}
		
		int totalPage = pageMatriculas.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("titulo","Listado de Matriculas");
		model.addAttribute("matriculas", pageMatriculas.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "/views/matricula/listar";
		
	}
	

	
	@RequestMapping(value = "/crear",method = RequestMethod.GET)
	public String crear(Model model,HttpSession request,HttpSession response) {
		request.setAttribute("idalum", response.getAttribute("idalum").toString());
		
		model.addAttribute("idalum", response.getAttribute("idalum"));
		model.addAttribute("modmatricula", new Matricula());
		List<Carrera> lstcar=scr.getCarrera();
		model.addAttribute("cbocar",lstcar);
		List<DetalleCurso> lstdcr=sdetc.listarTodosDetC();
		model.addAttribute("cbodcr",lstdcr);
		return "views/matricula/crear";
	}
	
	@PostMapping(value="/registrar")
	public String guardarnuevo(@Validated Matricula c, Model model) {
		sm.guardarnuevo(c);
		return "redirect:/matriculas/visor/alumno";
	}


	@GetMapping(value ="/visor/alumno")
	public String listarEnUsuario(@RequestParam Map<String, Object> params, Model model,/* @PathVariable("id") int id*/HttpSession response) {
		if (response.getAttribute("idalum")==null) {
			return "redirect:/intranet/login";
		}
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		Page<Matricula> pagePersona = sm.GetAllByIdalumno(pageRequest,Integer.valueOf(response.getAttribute("idalum").toString()));
		
		
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("user",response.getAttribute("user"));
		model.addAttribute("list", pagePersona.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/matricula/listarbyAlum";
	}
	@GetMapping("/mismatriculas/cancelar/{id}")
	public String eliminarbyalumno(Model model,@PathVariable int id) {
		sm.eliminarByalumno(id);
		return "redirect:/matriculas/visor/alumno";
	}
	@GetMapping(value ="/mismatriculas/editar/{id}")
	public String editbyalumno(@PathVariable int id, Model model,HttpSession request,HttpSession response) {
		request.setAttribute("idalum", response.getAttribute("idalum").toString());
		
		Matricula matricula=sm.buscarPorId(id);
		model.addAttribute("modmatricula",matricula);
		List<Carrera> lstcar=scr.getCarrera();
		model.addAttribute("cbocar",lstcar);
		List<DetalleCurso> lstdcr=sdetc.listarTodosDetC();
		model.addAttribute("cbodcr",lstdcr);
		return "views/matricula/editar";
	}
	
	@PostMapping(value="/mismatriculas/guardar")
	public String guardar(@Validated Matricula c, Model model) {
		sm.guardar(c);
		return "redirect:/matriculas/visor/alumno";
	}
}
