package com.algaworks.brewer.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.brewer.storage.FotoStorage;

@Component
public class CervejaListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#cervejaSalvaEvent.temFoto()")
	public void cervejaSalva(CervejaSalvaEvent cervejaSalvaEvent) {
		fotoStorage.salvar(cervejaSalvaEvent.getCerveja().getFoto());
	}
}
