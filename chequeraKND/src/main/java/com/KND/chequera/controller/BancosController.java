package com.KND.chequera.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.KND.chequera.entity.Bancos;
import com.KND.chequera.service.BancosService;

@Controller
@RequestMapping("/bancos")
public class BancosController {

	@Autowired
	@Qualifier("bancoService")
	private BancosService bancoService;
	
	@GetMapping("/listBancos")
	public ModelAndView listAllBancos() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("bancos", bancoService.listAllBancos());
		
		return mav;
	}
	
	@PostMapping("/addcourse")
	public String addBancos(@ModelAttribute("bancos") Bancos banco) {
		bancoService.addBanco(banco);
		return "redirect:/listBancos";
	}
	
}