package com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.OperationDao;
import com.pfe.model.ContratCpt;
import com.pfe.model.DetailOperMoyPaiement;
import com.pfe.model.Operation;
import com.pfe.model.OperationMoyPay;

@Service("OperationService")
@Transactional(readOnly = true)
public class OperationService {

	@Autowired
	private OperationDao dao;

	public OperationDao getDao() {
		return dao;
	}

	public void setDao(OperationDao dao) {
		this.dao = dao;
	}
	
	@Transactional
	public void createOperation(Operation o, OperationMoyPay oMP, DetailOperMoyPaiement dOMP) {
		dao.createOperation(o);
		dao.createOperationMoyPay(oMP);
		dao.createDetailOperMoyPaiement(dOMP);
	}
	
	public Operation findOperation(Long code) {
		List<Operation> l = dao.findOperation(code);
		if (!l.isEmpty()) 
			return l.get(0);
		else
			return null;
	}
	
	public List<OperationMoyPay> findAllOperationMoyPays(ContratCpt c) {
		return dao.findAllOperationMoyPays(c);
	}
	
}
