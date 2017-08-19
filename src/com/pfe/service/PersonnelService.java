package com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.PersonnelDao;
import com.pfe.model.Personnel;

@Service("PersonnelService")
@Transactional(readOnly = true)
public class PersonnelService {

	@Autowired
	private PersonnelDao dao;

	public PersonnelDao getDao() {
		return dao;
	}

	public void setDao(PersonnelDao dao) {
		this.dao = dao;
	}

	public Personnel findPersonnel(String num) {
		List<Personnel> l = dao.findPersonnel(num);
		if (!l.isEmpty()) {
			Personnel p = l.get(0);
			p.getStructure().getDateJournee();
			return l.get(0);

		} else
			return null;
	}

}
