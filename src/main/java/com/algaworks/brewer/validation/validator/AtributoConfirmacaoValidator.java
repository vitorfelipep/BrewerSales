package com.algaworks.brewer.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.algaworks.brewer.validation.AtributosConfirmacao;

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributosConfirmacao, Object>{
	
	private String atributo;
	private String atributoConfirmacao;
	
	
	@Override
	public void initialize(AtributosConfirmacao constraintAnotation) {
		this.atributo = constraintAnotation.atributo();
		this.atributoConfirmacao = constraintAnotation.atributoDeConfirmacao();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		boolean valido = false;
		try {
			Object primeiroValorAtributo = BeanUtils.getProperty(object, this.atributo);
			Object segundoValorAtributo = BeanUtils.getProperty(object, this.atributoConfirmacao);
			
			valido = ambosSaoNull(primeiroValorAtributo, segundoValorAtributo) || ambasSaoIguais(primeiroValorAtributo, segundoValorAtributo);
		}catch (Exception e) {
			throw new RuntimeException("Erro recuperando um dos atributos!", e);
		}
		
		if(!valido) {
			context.disableDefaultConstraintViolation();
			String mensagem = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(mensagem);
			violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
		}
		return valido;
	}

	private boolean ambasSaoIguais(Object primeiroValorAtributo, Object segundoValorAtributo) {
		return primeiroValorAtributo != null && primeiroValorAtributo.equals(segundoValorAtributo);
	}

	private boolean ambosSaoNull(Object primeiroValorAtributo, Object segundoValorAtributo) {
  
		return primeiroValorAtributo == null && segundoValorAtributo == null;
	}

}
