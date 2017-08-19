package com.pfe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.Personne;

@Repository
public class PersonneDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Personne> getPersonne(long num) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Personne> l = session
				.createQuery("from Personne where numSeqPers =:num")
				.setParameter("num", num).list();
		return l;

	}
}
