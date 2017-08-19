package com.pfe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.pfe.model.ContratCpt;
import com.pfe.model.DetailEffet;
import com.pfe.service.EffetService;

public class LazyEffetDataModel extends LazyDataModel<DetailEffet> {

	private static final long serialVersionUID = 1L;
	private EffetService service;
	private String etatListEffet = "";
	private Date dateListEffet = null;
	private ContratCpt compte;
	private List<DetailEffet> datasource;

	public LazyEffetDataModel(EffetService service) {
		super();
		this.service = service;
	}

	public LazyEffetDataModel(EffetService service, ContratCpt compte) {
		super();
		this.service = service;
		this.compte = compte;
	}

	@Override
	public List<DetailEffet> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		int dataSize = 0;
		if (etatListEffet.equals("I")) {
			List<String> l = new ArrayList<String>();
			l.add("I");
			datasource = service.findAllEffetsByCode(first, pageSize, l);
			dataSize = service.findAllEffetsByCodeCount(l);
		} else if (etatListEffet.equals("V")) {
			List<String> l = new ArrayList<String>();
			l.add("V");
			if (dateListEffet != null) {
				datasource = service.findAllEffetsByCodeDate(first, pageSize, l, dateListEffet);
				dataSize = service.findAllEffetsByCodeDateCount(l, dateListEffet);
			} else {
				datasource = service.findAllEffetsByCode(first, pageSize, l);
				dataSize = service.findAllEffetsByCodeCount(l);
			}
		} else if (etatListEffet.equals("A")) {
			List<String> l = new ArrayList<String>();
			l.add("A");
			datasource = service.findAllEffetsByCode(first, pageSize, l);
			dataSize = service.findAllEffetsByCodeCount(l);
		} else if (etatListEffet.equals("R")) {
			List<String> l = new ArrayList<String>();
			l.add("R");
			if (dateListEffet != null) {
				datasource = service.findAllEffetsByCodeDate(first, pageSize, l, dateListEffet);
				dataSize = service.findAllEffetsByCodeDateCount(l, dateListEffet);
			} else {
				datasource = service.findAllEffetsByCode(first, pageSize, l);
				dataSize = service.findAllEffetsByCodeCount(l);
			}
		} else if (etatListEffet.equals("E")) {
			List<String> l = new ArrayList<String>();
			l.add("E");
			datasource = service.findAllEffetsByCode(first, pageSize, l);
			dataSize = service.findAllEffetsByCodeCount(l);
		} else {
			if (compte != null) {
				datasource = service.findAllEffetsByCompte(first, pageSize, compte);
				dataSize = service.findAllEffetsByCompteCount(compte);
			} else {
				datasource = service.findAllEffets(first, pageSize);
				dataSize = service.findAllEffetsCount();
			}

		}
		// filter
		List<DetailEffet> data = new ArrayList<DetailEffet>();
		for (DetailEffet effet : datasource) {
			boolean match = true;
			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Object field = effet.getClass().getField(filterProperty).get(effet);
						String fieldValue;
						if (field instanceof Date) {
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							fieldValue = dateFormat.format(field);
						} else
							fieldValue = String.valueOf(field);

						if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
						e.printStackTrace();
					}
				}
			}
			if (match) {
				data.add(effet);
			}
		}
		datasource = data;
		if (sortField != null) {
			Collections.sort(datasource, new LazyEffetSorter(sortField, sortOrder));
		}
		this.setRowCount(dataSize);
		return datasource;
	}

	@Override
	public DetailEffet getRowData(String rowKey) {
		for (DetailEffet effet : datasource) {
			if (effet.getNumSeq().toString().equals(rowKey))
				return effet;
		}
		return null;
	}

	@Override
	public Object getRowKey(DetailEffet effet) {
		return effet.getNumSeq().toString();
	}

	public String getEtatListEffet() {
		return etatListEffet;
	}

	public void setEtatListEffet(String etatListEffet) {
		this.etatListEffet = etatListEffet;
	}

	public Date getDateListEffet() {
		return dateListEffet;
	}

	public void setDateListEffet(Date dateListEffet) {
		this.dateListEffet = dateListEffet;
	}

	public ContratCpt getCompte() {
		return compte;
	}

	public void setCompte(ContratCpt compte) {
		this.compte = compte;
	}

}
