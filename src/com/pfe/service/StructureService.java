package com.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfe.dao.StructureDao;
import com.pfe.model.Structure;

@Service("StructureService")
@Transactional(readOnly = true)
public class StructureService {

	@Autowired
	private StructureDao dao;

	public List<Structure> findStructure(long code) {
		List<Structure> l = dao.findStructure(code);
		if (!l.isEmpty())
			return l;
		else
			return null;
	}

	public StructureDao getDao() {
		return dao;
	}

	public void setDao(StructureDao dao) {
		this.dao = dao;
	}

}
