package com.pfe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.EffetDao;
import com.pfe.dao.OperationDao;
import com.pfe.model.DetailEffet;
import com.pfe.model.DetailOperMoyPaiement;
import com.pfe.model.OperationMoyPay;

@Service("OperationEffetService")
@Transactional(readOnly = true)
public class OperationEffetService {

	@Autowired
	private EffetDao dao;
	
	@Autowired
	private OperationDao oDao;

	@Transactional
	public void createOperationEffet(DetailEffet e, OperationMoyPay oMP, DetailOperMoyPaiement dOMP) {
		dao.createEffet(e);
		oDao.createOperationMoyPay(oMP);
		oDao.createDetailOperMoyPaiement(dOMP);
	}
	
	@Transactional
	public void updateOperationEffet(DetailEffet e, OperationMoyPay oMP, DetailOperMoyPaiement dOMP) {
		dao.updateEffet(e);
		oDao.createOperationMoyPay(oMP);
		oDao.createDetailOperMoyPaiement(dOMP);
	}
	
	@Transactional
	public void createOperationEffet(DetailEffet e, OperationMoyPay oMP) {
		dao.createEffet(e);
		oDao.createOperationMoyPay(oMP);
	}
	
	@Transactional
	public void updateOperationEffet(DetailEffet e, OperationMoyPay oMP) {
		dao.updateEffet(e);
		oDao.createOperationMoyPay(oMP);
	}
	
	
	
	public EffetDao getDao() {
		return dao;
	}


	public void setDao(EffetDao dao) {
		this.dao = dao;
	}

	public OperationDao getoDao() {
		return oDao;
	}

	public void setoDao(OperationDao oDao) {
		this.oDao = oDao;
	}

}
