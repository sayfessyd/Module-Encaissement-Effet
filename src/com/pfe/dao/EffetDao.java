package com.pfe.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pfe.model.ContratCpt;
import com.pfe.model.DetailEffet;
import com.pfe.model.MotifRejet;

@Repository
public class EffetDao {

	@Autowired
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	public void createEffet(DetailEffet e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(e);
	}
	
	public void updateEffet(DetailEffet e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
	}

	@SuppressWarnings("unchecked")
	public List<DetailEffet> findEffetByCode(String num, List<String> code) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where numEff=:num and codEtat in(:code)");
		query.setParameter("num", num);
		query.setParameterList("code", code);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffets() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from DetailEffet order by numSeq").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffets(int off, int mx) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet order by numSeq");
		query.setFirstResult(off);
		query.setMaxResults(mx);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffetsByCode(List<String> code) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where codEtat in(:code) order by numSeq");
		query.setParameterList("code", code);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffetsByCode(int off, int mx, List<String> code) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where codEtat in(:code) order by numSeq");
		query.setParameterList("code", code);
		query.setFirstResult(off);
		query.setMaxResults(mx);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffetsByCodeDate(List<String> code, Date dt) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, +1);
		Date dtB = c.getTime();
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where codEtat in(:code) and :dateA<=datOpe and datOpe<:dateB order by numSeq");
		query.setParameterList("code", code);
		query.setDate("dateA", dt);
		query.setDate("dateB", dtB);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffetsByCodeDate(int off, int mx, List<String> code, Date dt) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where codEtat in(:code) and :date<=datOpe order by numSeq");
		query.setParameterList("code", code);
		query.setDate("date", dt);
		query.setFirstResult(off);
		query.setMaxResults(mx);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<DetailEffet> findAllEffetsByCompte(int off, int mx, ContratCpt c) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from DetailEffet where contratCpt=:compte order by numSeq");
		query.setParameter("compte", c);
		query.setFirstResult(off);
		query.setMaxResults(mx);
		return query.list();
	}
	
	public int findAllEffetsCount()
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(numSeq) from DetailEffet";
		Query query = session.createQuery(hql);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int findAllEffetsByCodeCount(List<String> code)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(numSeq) from DetailEffet where codEtat in(:code)";
		Query query = session.createQuery(hql);
		query.setParameterList("code", code);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int findAllEffetsByCodeDateCount(List<String> code, Date dt)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(numSeq) from DetailEffet where codEtat in(:code) and :date<=datOpe";
		Query query = session.createQuery(hql);
		query.setParameterList("code", code);
		query.setDate("date", dt);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	public int findAllEffetsByCompteCount(ContratCpt c)
	{
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "select count(numSeq) from DetailEffet where contratCpt=:compte";
		Query query = session.createQuery(hql);
		query.setParameter("compte", c);
		return ((Number) query.uniqueResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<MotifRejet> findAllMotifs() {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from MotifRejet").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<MotifRejet> findMotifByCode(Long code) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from MotifRejet where codeRej=:code");
		query.setParameter("code", code);
		return query.list();
	}

}
