package com.pfe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.Personnel;

@Repository
public class PersonnelDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Personnel> findPersonnel(String num) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Personnel> l = session
				.createQuery("from Personnel where numMatrUser =:num")
				.setParameter("num", num).list();
		return l;
	}
}
