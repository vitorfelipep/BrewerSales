package com.algaworks.brewer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;

@Controller
@RequestMapping("/cidades")
public class CidadesController {
	
	@Autowired
	private Cidades cidades;
	
	@RequestMapping("/novo")
	public String nova() {
		return "cidade/CadastroCidade";
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorCodigoEstado(
				@RequestParam(name = "estado", defaultValue = "-1") Long codigoEstado) throws InterruptedException {
//		Thread.sleep(500);
		return cidades.findByEstadoCodigo(codigoEstado);
	}
	
}
