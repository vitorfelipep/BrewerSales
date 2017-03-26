package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.Usuarios;
import com.algaworks.brewer.service.exception.EmailUsuarioJaExistenteException;
import com.algaworks.brewer.service.exception.NomeUsuarioJaExistenteException;
import com.algaworks.brewer.service.exception.SenhaObrigatorioUsuarioException;

@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEnconder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByNomeIgnoreCase(usuario.getNome());
		if(usuarioExistente.isPresent()) {
			throw new NomeUsuarioJaExistenteException("Nome de usuário já cadastrado!");
		}
		
		Optional<Usuario> usuarioEmailExistente = usuarios.findByEmail(usuario.getEmail());
		if(usuarioEmailExistente.isPresent()) {
			throw new EmailUsuarioJaExistenteException("Email já cadastrado!");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatorioUsuarioException("Senha é obrigatória para novo usuário.");
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(passwordEnconder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}
	
}
