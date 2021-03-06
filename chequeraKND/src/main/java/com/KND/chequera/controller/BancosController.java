package com.KND.chequera.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.KND.chequera.constant.ViewConstant;
import com.KND.chequera.model.BancoModel;
import com.KND.chequera.service.BancosService;

@Controller
@RequestMapping("/bancos")
public class BancosController {

	@Autowired
	@Qualifier("bancosService")
	private BancosService bancoService;
	
	private static final Log LOG = LogFactory.getLog(BancosController.class);
	
	@GetMapping("/listbancos")
	public ModelAndView listAllBancos() {
		ModelAndView mav = new ModelAndView(ViewConstant.LIST_BANCOS_VIEW);
		mav.addObject("listbancos", bancoService.listAllBancos());
		
		return mav;
	}
	
	@GetMapping("/bancosform")
	public String bancosForm(Model model) {
		model.addAttribute("bancoModel", new BancoModel());
		return ViewConstant.ADD_BANCOS_VIEW;
	}
	
	@PostMapping("/addbanco")
	public String addBancos(@ModelAttribute("bancoModel") BancoModel bancoModel, Model model) {
		LOG.info("METHOD: addBanco() --PARAMS "+bancoModel.toString());

		if(null != bancoService.addBanco(bancoModel)) {
			//model.addAttribute("result", 1);
			LOG.info("Result: 1");
		}else {
			//model.addAttribute("result", 0);
			LOG.info("Result: 0");
		}
		return "redirect:/bancos/listbancos";
		//return ViewConstant.LIST_BANCOS_VIEW;
	}
	
	@GetMapping("removebanco")
	public ModelAndView removeBanco(@RequestParam(name="id", required=true) int id) {
		bancoService.removeBancos(id);
		return listAllBancos();
	}
	
	
	
}
