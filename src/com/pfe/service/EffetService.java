package com.pfe.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.EffetDao;
import com.pfe.model.ContratCpt;
import com.pfe.model.DetailEffet;
import com.pfe.model.MotifRejet;
import com.pfe.model.Personne;

@Service("EffetService")
@Transactional(readOnly = true)
public class EffetService {

	@Autowired
	private EffetDao dao;

	@Transactional
	public void createEffet(DetailEffet e) {
		dao.createEffet(e);
	}
	
	@Transactional
	public void updateEffet(DetailEffet e) {
		dao.updateEffet(e);
	}
	
	public List<DetailEffet> findEffetByCode(String num, List<String> code) {
		List <DetailEffet> l = dao.findEffetByCode(num, code);
		if (!l.isEmpty())
		{
			DetailEffet e = l.get(0);
			ContratCpt c = e.getcontratCpt();
			c.getMontSoldCcpt();
			Personne p = c.getPersonne();
			p.getNumPcePers();
			p.getTypePiece();
			return l;
		}
		else
			return null;
	}
	
	public List<DetailEffet> findAllEffets() {
		return dao.findAllEffets();
	}
	
	public List<DetailEffet> findAllEffets(int off, int mx) {
		return dao.findAllEffets(off,mx);
	}
	
	public List<DetailEffet> findAllEffetsByCode(List<String> code) {
		return dao.findAllEffetsByCode(code);
	}
	
	public List<DetailEffet> findAllEffetsByCode(int off, int mx, List<String> code) {
		return dao.findAllEffetsByCode(off, mx, code);
	}
	
	public List<DetailEffet> findAllEffetsByCodeDate(List<String> code, Date dt) {
		return dao.findAllEffetsByCodeDate(code, dt);
	}
	
	public List<DetailEffet> findAllEffetsByCodeDate(int off, int mx, List<String> code, Date dt) {
		return dao.findAllEffetsByCodeDate(off, mx, code, dt);
	}
	
	public List<DetailEffet> findAllEffetsByCompte(int off, int mx, ContratCpt c) {
		return dao.findAllEffetsByCompte(off, mx, c);
	}
	
	public int findAllEffetsCount()
	{
		return dao.findAllEffetsCount();
	}
	
	public int findAllEffetsByCodeCount(List<String> code) {
		return dao.findAllEffetsByCodeCount(code);
	}
	
	public int findAllEffetsByCodeDateCount(List<String> code, Date dt) {
		return dao.findAllEffetsByCodeDateCount(code, dt);
	}
	
	public int findAllEffetsByCompteCount(ContratCpt c)
	{
		return dao.findAllEffetsByCompteCount(c);
	}

	public EffetDao getDao() {
		return dao;
	}

	public void setDao(EffetDao dao) {
		this.dao = dao;
	}
	
	public List<MotifRejet> findAllMotifs() {
		return dao.findAllMotifs();
	}
	
	public MotifRejet findMotifByCode(Long code) {
		List <MotifRejet> l = dao.findMotifByCode(code);
		if (!l.isEmpty())
		{
			return l.get(0);
		}
		else
			return null;
	}

}
