package com.pfe.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.RemiseDao;
import com.pfe.model.GlobalRemise;

@Service("RemiseService")
@Transactional(readOnly = true)
public class RemiseService {

	@Autowired
	private RemiseDao dao;

	@Transactional
	public Serializable createRemise(GlobalRemise r) {
		return dao.createRemise(r);
	}
	
	@Transactional
	public void updateRemise(GlobalRemise r) {
		dao.updateRemise(r);
	}
	
	public GlobalRemise findRemise(Long num) {
		List <GlobalRemise> l = dao.findRemise(num);
		if (!l.isEmpty())
		{
			GlobalRemise e = l.get(0);
			return e;
		}
		else
			return null;
	}
	
	public RemiseDao getDao() {
		return dao;
	}

	public void setDao(RemiseDao dao) {
		this.dao = dao;
	}

}
