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
import com.ppcr.sistema.IService.IDetalleCurso;
import com.ppcr.sistema.IService.IProfesor;
import com.ppcr.sistema.IService.ITurno;
import com.ppcr.sistema.model.Curso;
import com.ppcr.sistema.model.DetalleCurso;
import com.ppcr.sistema.model.Profesor;
import com.ppcr.sistema.model.Turno;


@Controller
@RequestMapping("/cp")
public class CursoProfesorController {

	@Autowired
	private IDetalleCurso detservice;
	
	@Autowired
	private IProfesor profservice;
	
	@Autowired
	private ICurso cursoservice;
	
	@Autowired
	private ITurno turnoservice;
	
	@RequestMapping(value={"/listabase"},method = RequestMethod.GET)//,"/listabase/{param}"
	public String listabase(Model model,@RequestParam(value = "param",required=false) String param,@RequestParam Map<String, Object> params, HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		

		List<Profesor> listadoprofesor=profservice.BuscarTodos();

		model.addAttribute("lstProfesor", listadoprofesor);	

		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<DetalleCurso> pageCursoProfesor = null;
		
		if (param != null) {
			try {
				pageCursoProfesor = detservice.buscarPorProf(Integer.valueOf(param),pageRequest);
			}catch(Exception ex){
				return "redirect:/cp/listabase";
			}
			
		}else {
			pageCursoProfesor = detservice.getAllDetC(pageRequest);
		}
		
		int totalPage = pageCursoProfesor.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("cursoprofesor", pageCursoProfesor.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/cursoprofesor/listar";
		
	}

	@RequestMapping(value={"/menu/profesor"},method = RequestMethod.GET)//,"/listabase/{param}"
	public String listamenup(Model model,@RequestParam Map<String, Object> params, HttpSession response) {
		if (response.getAttribute("idprof")==null) {
			return "redirect:/intranet/login";
		}
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		Page<DetalleCurso> pageCursoProfesor = null;

		pageCursoProfesor = detservice.buscarMenuProf(Integer.valueOf(response.getAttribute("idprof").toString()),pageRequest);
	
		int totalPage = pageCursoProfesor.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("cursoprofesor", pageCursoProfesor.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/profesores/index";
		
	}
	
	@PostMapping(value="/guardar")
	public String Guardar(@ModelAttribute DetalleCurso cursoprofesor) {		
		detservice.guardarDetC(cursoprofesor);
		return "redirect:/cp/listabase";
	}	
	
	
	@GetMapping(value={"/crear","/editar/{id}"})
	public String detalledesurso( Model model ,@PathVariable(value="id",required = false)Long id,HttpSession response ) {//@PathVariable("id")  Integer iddet//@RequestParam(value = "id",required=false)
	
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		DetalleCurso cursoprofesor= new DetalleCurso();
		
		if (id !=null) {
			cursoprofesor=detservice.buscarPorIdDetC(id);
		}
		
		
		List<Profesor> listadoprofesor=profservice.BuscarTodos();
		List<Curso> listadocurso=cursoservice.listarTodosCursos();
		List<Turno> listadoturno=turnoservice.listarTodos();
		
		
		model.addAttribute("titulo", "Centro de asignacion y configuracion de Horarios de Clases");
		model.addAttribute("cursoprofesor",cursoprofesor);
		model.addAttribute("lstProfesor", listadoprofesor);	
		model.addAttribute("lstCurso", listadocurso);	
		model.addAttribute("lstTurno", listadoturno);
		
		return "views/cursoprofesor/crear";		
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") Long iddet,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		detservice.eliminarDetC(iddet);
		return "redirect:/cp/listabase";
	}	
	
	
	
}
