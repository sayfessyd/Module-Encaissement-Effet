package com.pfe.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.GlobalRemise;

@Repository
public class RemiseDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public Serializable createRemise(GlobalRemise r) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.save(r);
	}
	
	public void updateRemise(GlobalRemise r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(r);
	}

	@SuppressWarnings("unchecked")
	public List<GlobalRemise> findRemise(Long num) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from GlobalRemise where numRem=:num");
		query.setParameter("num", num);
		return query.list();
	}

	

}
