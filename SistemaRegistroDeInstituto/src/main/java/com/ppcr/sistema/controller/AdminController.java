package com.ppcr.sistema.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@GetMapping(value="/menu")
	public String menuadmin(HttpSession response,Model model) {
	
		if (response.getAttribute("session")==null) {
			return "redirect:/intranet/login";
		}
		
		model.addAttribute("codadmin",response.getAttribute("codadmin"));
		return "views/admin/menu";
		
	}
}
