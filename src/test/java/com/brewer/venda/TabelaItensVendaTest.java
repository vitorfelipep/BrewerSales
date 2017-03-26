package com.brewer.venda;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.session.TabelaItensVenda;

public class TabelaItensVendaTest {
	
	private TabelaItensVenda itensVenda;
	
	@Before
	public void setUp() {
		this.itensVenda = new TabelaItensVenda();
	}
	
	@Test
	public void deveCalcularValorTotalSemItem() throws Exception {
		assertEquals(BigDecimal.ZERO, this.itensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("8.90");
		cerveja.setValor(valor);
		
		itensVenda.adicionarItem(cerveja, 1);
		
		assertEquals(valor, itensVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		BigDecimal v1 = new BigDecimal("9");
		c1.setValor(v1);
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		BigDecimal v2 = new BigDecimal("5");
		c2.setValor(v2);
		
		itensVenda.adicionarItem(c1, 1);
		itensVenda.adicionarItem(c2, 2);
		assertEquals(new BigDecimal("19"), itensVenda.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		itensVenda.adicionarItem(c1, 1);
		itensVenda.adicionarItem(c1, 1);
		
		assertEquals(1, itensVenda.total());
		assertEquals(new BigDecimal("9.00"), itensVenda.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuantidadeDoItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		itensVenda.adicionarItem(c1, 1);
		
		itensVenda.alterarQuantidadesItens(c1, 3);
		assertEquals(new BigDecimal("13.50"), itensVenda.getValorTotal());
	}
}
