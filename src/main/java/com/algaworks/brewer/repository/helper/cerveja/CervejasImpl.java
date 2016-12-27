package com.algaworks.brewer.repository.helper.cerveja;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;
import com.algaworks.brewer.repository.pagination.PaginacaoUtil;

public class CervejasImpl implements CervejasQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil PaginacaoUtil;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {
		Criteria criteria =  manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		PaginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(cervejaFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(cervejaFilter));
	}
	
	private Long total(CervejaFilter cervejaFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionarFiltro(cervejaFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CervejaFilter cervejaFilter, Criteria criteria) {
		if(null != cervejaFilter) {
			if(!StringUtils.isEmpty(cervejaFilter.getSku())) {
				criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));
			}
			
			if(!StringUtils.isEmpty(cervejaFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", cervejaFilter.getNome(), MatchMode.ANYWHERE));
			}
			
			if (isEstiloPresente(cervejaFilter)) {
				criteria.add(Restrictions.eq("estilo", cervejaFilter.getEstilo()));
			}

			if (cervejaFilter.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", cervejaFilter.getSabor()));
			}

			if (cervejaFilter.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", cervejaFilter.getOrigem()));
			}

			if (cervejaFilter.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", cervejaFilter.getValorDe()));
			}

			if (cervejaFilter.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", cervejaFilter.getValorAte()));
			}
		}
	}

	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}
}
