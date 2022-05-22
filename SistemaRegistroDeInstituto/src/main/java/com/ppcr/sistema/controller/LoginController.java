package com.ppcr.sistema.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppcr.sistema.IService.IAdmin;
import com.ppcr.sistema.IService.IAlumno;
import com.ppcr.sistema.IService.IProfesor;
import com.ppcr.sistema.IService.IUsuario;
import com.ppcr.sistema.model.Administrador;
import com.ppcr.sistema.model.Alumno;
import com.ppcr.sistema.model.Profesor;
import com.ppcr.sistema.model.Usuario;


@Controller
@RequestMapping("/intranet")
public class LoginController {
	
	@Autowired
	private IUsuario us;
	@Autowired
	private IAdmin as1;
	@Autowired
	private IProfesor ps2;
	@Autowired
	private IAlumno as3;
	
	@GetMapping(value="/login")
	public String loginuser(Model model,HttpSession session, HttpServletRequest request) {
		

		if (session.getAttribute("session")==null) {
			
			if(session.getAttribute("logerror") != null ){
				
				session.invalidate();
				 model.addAttribute("logerror",1);
					return "views/intranet/login";
			}else {
				session.invalidate();
				model.addAttribute("logout",0);
				return "views/intranet/login";
			}
			
			
		}else {
			if (Integer.valueOf(session.getAttribute("session").toString()) == 0) { 
				session.invalidate();
		
				model.addAttribute("logout",1);
				return "views/intranet/login";
			}
			
		}
		return null;
		
		
	}
	@RequestMapping(value="/logout")
	public String logout( HttpSession session,Model model,HttpServletRequest request) {
		   session.setAttribute("session",0);
		return "redirect:/intranet/login";
	}
	
	@RequestMapping(value="/verifylogin")
	public String login(@RequestParam(name = "usuario",required=false) String usuario,@RequestParam(name = "contrasena", required=false) String contrasena,@RequestParam(name = "rol", required=false) Integer rol, Model model,HttpSession session,HttpServletRequest request) {
		
			Usuario result2= us.login(usuario,contrasena,rol);
			
			
			if (result2==null) {    
				
		        session.setAttribute("logerror",1);
				return "redirect:/intranet/login";  
				
			}else if (result2.getRol()==3) {
				
				Administrador objl1= as1.buscaporUser(result2.getIdusuario(), result2.getRol()); 
				session.setAttribute("user",objl1.getAdmin_nom());
				model.addAttribute("logerror",0);
				model.addAttribute("logout",0);
				session.setAttribute("session",1);
				session.setAttribute("idadmin", objl1.getIdadmin());
				return "redirect:/admin/menu"; 
				
			}else if (result2.getRol()==2){
				Profesor objl2 = ps2.buscaporUser(result2.getIdusuario(), result2.getRol()); 
				session.setAttribute("user",objl2);
				model.addAttribute("logerror",0);
				model.addAttribute("logout",0);
				session.setAttribute("session",1);
				session.setAttribute("idprof", objl2.getIdprofesor());
				return "redirect:/cp/menu/profesor";
				
			}else if (result2.getRol()==1){
				
				Alumno objl3 = as3.buscaporUser(result2.getIdusuario(), result2.getRol()); 
				session.setAttribute("user",objl3);
				model.addAttribute("logerror",0);
				model.addAttribute("logout",0);
				session.setAttribute("session",1);
				session.setAttribute("idalum", objl3.getIdalumno());
				return "redirect:/matriculas/visor/alumno";
			}
			return null;	
			
	}
	
	

}
