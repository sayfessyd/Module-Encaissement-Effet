package com.pfe.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.ContratCpt;
import com.pfe.model.DetailOperMoyPaiement;
import com.pfe.model.Operation;
import com.pfe.model.OperationMoyPay;

@Repository
public class OperationDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void createOperation(Operation o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(o);
	}

	public void createOperationMoyPay(OperationMoyPay oMP) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(oMP);
	}

	public void createDetailOperMoyPaiement(DetailOperMoyPaiement dOMP) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(dOMP);
	}

	@SuppressWarnings("unchecked")
	public List<Operation> findOperation(Long code) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Operation> l = session.createQuery("from Operation where codOperOper = :code").setParameter("code", code)
				.list();
		return l;
	}

	@SuppressWarnings("unchecked")
	public List<OperationMoyPay> findAllOperationMoyPays(ContratCpt c) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OperationMoyPay> l = session.createQuery("from OperationMoyPay  where contratCpt = :compte order by numOperOmp")
				.setParameter("compte", c).list();
		return l;
	}

}
