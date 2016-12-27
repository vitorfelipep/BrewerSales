package com.algaworks.brewer.repository.pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginacaoUtil {
	
	public void preparar(Criteria criteria, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistorsPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistorsPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistorsPorPagina);
		
		Sort sort = pageable.getSort();
		if(null != sort) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
	}
	
}
