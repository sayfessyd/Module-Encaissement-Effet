package com.pfe.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.ContratCpt;

@Repository
public class ContratDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public void updateContrat(ContratCpt c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
	}

	@SuppressWarnings("unchecked")
	public List<ContratCpt> findContrat(Long codStrc, Long codPrd, Long num) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from ContratCpt where contratCptId.codStrcStrc=:codStrc and contratCptId.codPrdPrd=:codPrd and contratCptId.numCcptCcpt=:num");
		query.setParameter("codStrc", codStrc);
		query.setParameter("codPrd", codPrd);
		query.setParameter("num", num);
		List<ContratCpt> l = query.list();
		return l;
	}
	
}
