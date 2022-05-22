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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.IBoleta;
import com.ppcr.sistema.model.Boleta;




@Controller
@RequestMapping("/boletas")
public class BoletaController {

	@Autowired
	private IBoleta sb;
	
	
	@RequestMapping(value={"/proceso"},method = RequestMethod.GET)//@GetMapping(value ="/proceso")
	public String find(@RequestParam (value="codalum",required=false) String codalum,
			@RequestParam (value="estado",required=false) Integer estado,
			@RequestParam Map<String, Object> params, Model model,HttpSession request,HttpSession response) {
		if (response.getAttribute("idadmin")==null) {
			return "redirect:/intranet/login";
		}
		
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 8);
		
		
		Page<Boleta> pagePersona= null;
		
		
		if (codalum == null || codalum=="") {
			pagePersona = sb.getAll(pageRequest);
		}else {
			if(estado != null) {
				pagePersona = sb.getAllCodEstado(codalum, estado,pageRequest);
			}else {
				pagePersona = sb.getAllCod(codalum,pageRequest);
			}
		}
		
		
		
		int totalPage = pagePersona.getTotalPages();
		if(totalPage > 0) {
			List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages", pages);
		}
		
		model.addAttribute("list", pagePersona.getContent());
		model.addAttribute("current", page + 1);
		model.addAttribute("next", page + 2);
		model.addAttribute("prev", page);
		model.addAttribute("last", totalPage);
		return "views/boleta/listar";
	}
	
	@GetMapping("/confirmacion-pago/{id}")
	public String confirmarmatricula(Model model,@PathVariable(value="id") int idboleta) {
		sb.ConfirmacionPagos(idboleta);
		return "redirect:/boletas/proceso";
	}
}
