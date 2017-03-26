package com.algaworks.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControler {
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "404";
	}
	
	@RequestMapping("/500")
	public String errorNaPagina() {
		return "500";
	}
}
