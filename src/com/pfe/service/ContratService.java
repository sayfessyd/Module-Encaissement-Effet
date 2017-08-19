package com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.ContratDao;
import com.pfe.model.ContratCpt;
import com.pfe.model.Personne;

@Service("ContratService")
@Transactional(readOnly = true)
public class ContratService {

	@Autowired
	private ContratDao dao;
	
	@Transactional
	public void updateContrat(ContratCpt c) {
		dao.updateContrat(c);
	}

	public ContratCpt findContrat(Long codStrc, Long codPrd, Long num) {
		List <ContratCpt> l = dao.findContrat(codStrc, codPrd, num);
		if (!l.isEmpty())
		{
			return l.get(0);
			
		}
		else
			return null;
	}

	public ContratCpt findContratWithPers(Long codStrc, Long codPrd, Long num) {
		List <ContratCpt> l = dao.findContrat(codStrc, codPrd, num);
		if (!l.isEmpty())
		{
			ContratCpt c = l.get(0);
			Personne p = c.getPersonne();
			p.getAdresseProf();
			p.getNomNomPers();
			p.getNomPrnmPers();
			p.getAdresseResid();
			p.getNumPcePers();
			p.getLieuNaisPers();
			p.getNumTelPers();
			return c;
		}
		else
			return null;
		
	}

	public ContratCpt findContratWithEffets(Long codStrc, Long codPrd, Long num) {
		List <ContratCpt> l = dao.findContrat(codStrc, codPrd, num);
		if (!l.isEmpty())
		{
			ContratCpt c = l.get(0);
			c.getEffets();
			return c;
		}
		else
			return null;
		
	}
	
	public ContratDao getDao() {
		return dao;
	}

	public void setDao(ContratDao dao) {
		this.dao = dao;
	}

}
