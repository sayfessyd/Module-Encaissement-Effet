package com.pfe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.Structure;

@Repository
public class StructureDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Structure> findStructure(long code) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Structure> l = session.createQuery(
				"from Structure where codStrcStrc = :code").setParameter("code", code).list();
		return l;

	}
}
