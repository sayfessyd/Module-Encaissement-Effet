package com.pfe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.PersonneDao;

@Service("PersonneService")
@Transactional(readOnly = true)
public class PersonneService {

	@Autowired
	private PersonneDao dao;

	public PersonneDao getDao() {
		return dao;
	}

	public void setDao(PersonneDao dao) {
		this.dao = dao;
	}

}
