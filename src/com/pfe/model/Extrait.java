package com.pfe.model;

import java.util.Date;

public class Extrait {
	private Date date;
	private String lib;
	private Long val;
	private String deb = "";
	private String cred = "";
	private Long sold;
	
	
	public Extrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Extrait(Date date, String lib, Long val, Long sold) {
		super();
		this.date = date;
		this.lib = lib;
		this.val = val;
		this.sold = sold;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getLib() {
		return lib;
	}


	public void setLib(String lib) {
		this.lib = lib;
	}


	public Long getVal() {
		return val;
	}


	public void setVal(Long val) {
		this.val = val;
	}


	public String getDeb() {
		return deb;
	}


	public void setDeb(String deb) {
		this.deb = deb;
	}


	public String getCred() {
		return cred;
	}


	public void setCred(String cred) {
		this.cred = cred;
	}


	public Long getSold() {
		return sold;
	}


	public void setSold(Long sold) {
		this.sold = sold;
	}
	
}
