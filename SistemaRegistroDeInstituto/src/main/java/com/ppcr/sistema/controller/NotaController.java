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

import com.ppcr.sistema.IService.IAlumno;
import com.ppcr.sistema.IService.ICurso;
import com.ppcr.sistema.IService.INota;
import com.ppcr.sistema.model.Alumno;
import com.ppcr.sistema.model.Curso;
import com.ppcr.sistema.model.Nota;




@Controller
@RequestMapping("/notas")
public class NotaController {

	@Autowired
	private INota notaservice;
	
	@Autowired
	private IAlumno servAlumno;
	
	@Autowired
	private ICurso servCurso;

	
	@RequestMapping(value={"/visor/alumno"},method = RequestMethod.GET)
	public String listar(Model model,
			@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idalum")==null) {
			return "redirect:/intranet/login";
		}

		model.addAttribute("titulo","Reporte General de Notas");
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 6);
		
		Page<Nota> pageNotas = notaservice.GetAllbyAlumn( Integer.valueOf(response.getAttribute("idalum").toString()),pageRequest);//(String) da error , asi mejor 
		
		int totalPage = pageNotas.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		model.addAttribute("user",response.getAttribute("user"));
		model.addAttribute("notas", pageNotas.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/notas/listarbyalum";
		
	}
	
	
	@PostMapping(value="/guardar")
	public String guardar(@Validated Nota n, Model model,HttpSession response) {
		notaservice.guardarNota(n);
		return "redirect:/notas/visor/profesor/"+response.getAttribute("iddet").toString();
	}
	@PostMapping(value="/registrar")
	public String guardarnuevo(@Validated Nota n, Model model) {
		notaservice.guardarnuevoNota(n);
		return "redirect:/notas/listar";
	}
	@RequestMapping(value = "/editar/hfu={id}&cbt={idcurso}&ki={idre}",method = RequestMethod.GET)
	public String crear(Model model,@PathVariable("id") Long id,@PathVariable("idcurso") Long idcurso,
		@PathVariable("idre") Integer idre,HttpSession request,HttpSession response) {/*session.setAttribute("idprofe", result2.getCodprofile());*/
		
		if (response.getAttribute("idprof")==null) {
			return "redirect:/intranet/login";
		}
		Nota nota=notaservice.buscarPorIdNota(idre);
		model.addAttribute("modnota", nota);
		Alumno lstalu=servAlumno.buscarPorId(id);
		model.addAttribute("cboalu",lstalu);
		Curso lstcur=servCurso.buscarPorIdCursos(idcurso);
		model.addAttribute("cbocur",lstcur);
		model.addAttribute("titulo","Agregar Notas de Alumno por Curso");
		return "views/notas/crear";
	}
	
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idreporte) {
		notaservice.eliminarNota(idreporte);
		return "redirect:/notas/listar";
	}	
	
	@RequestMapping(value={"/visor/profesor/{id}"},method = RequestMethod.GET)
	public String listarprof(Model model,@PathVariable("id") Integer id,
			@RequestParam Map<String, Object> params, HttpSession response,HttpSession request) {
		if (response.getAttribute("idprof")==null ||response.getAttribute("user") ==null) {
			return "redirect:/intranet/login";
		}

		request.setAttribute("iddet", id);
		model.addAttribute("titulo","Reporte General de Notas");
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 6);
		
		Page<Nota> pageNotas = notaservice.GetbyProf(id,pageRequest);
		
		int totalPage = pageNotas.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
	
		model.addAttribute("profe",response.getAttribute("user"));
		model.addAttribute("notas", pageNotas.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/notas/listarbyprof";
		
	}
	
}
