package com.pfe.model;

import java.util.Date;


public class Structure implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codStrcStrc;
	private String libStrcStrc;
	private String codBctStrc;
	private String adrImmStrc;
	private String adrRueStrc;
	private String adrCitStrc;
	private String numTelStrc;
	private Long numFaxStrc;
	private Date dateJournee;
	
	
	// Constructors

	/** default constructor */
	public Structure() {
	}

	/** minimal constructor */
	public Structure(Long codStrcStrc,
			 String libStrcStrc) {
		this.codStrcStrc = codStrcStrc;
		this.libStrcStrc = libStrcStrc;

	}

	public Structure(Long codStrcStrc, String libStrcStrc, String codBctStrc, String adrImmStrc, String adrRueStrc,
			String adrCitStrc, String numTelStrc, Long numFaxStrc, Date dateJournee) {
		super();
		this.codStrcStrc = codStrcStrc;
		this.libStrcStrc = libStrcStrc;
		this.codBctStrc = codBctStrc;
		this.adrImmStrc = adrImmStrc;
		this.adrRueStrc = adrRueStrc;
		this.adrCitStrc = adrCitStrc;
		this.numTelStrc = numTelStrc;
		this.numFaxStrc = numFaxStrc;
		this.dateJournee = dateJournee;
	}

	public Long getCodStrcStrc() {
		return codStrcStrc;
	}

	public void setCodStrcStrc(Long codStrcStrc) {
		this.codStrcStrc = codStrcStrc;
	}

	public String getLibStrcStrc() {
		return libStrcStrc;
	}

	public void setLibStrcStrc(String libStrcStrc) {
		this.libStrcStrc = libStrcStrc;
	}

	public String getCodBctStrc() {
		return codBctStrc;
	}

	public void setCodBctStrc(String codBctStrc) {
		this.codBctStrc = codBctStrc;
	}

	public String getAdrImmStrc() {
		return adrImmStrc;
	}

	public void setAdrImmStrc(String adrImmStrc) {
		this.adrImmStrc = adrImmStrc;
	}

	public String getAdrRueStrc() {
		return adrRueStrc;
	}

	public void setAdrRueStrc(String adrRueStrc) {
		this.adrRueStrc = adrRueStrc;
	}

	public String getAdrCitStrc() {
		return adrCitStrc;
	}

	public void setAdrCitStrc(String adrCitStrc) {
		this.adrCitStrc = adrCitStrc;
	}

	public String getNumTelStrc() {
		return numTelStrc;
	}

	public void setNumTelStrc(String numTelStrc) {
		this.numTelStrc = numTelStrc;
	}

	public Long getNumFaxStrc() {
		return numFaxStrc;
	}

	public void setNumFaxStrc(Long numFaxStrc) {
		this.numFaxStrc = numFaxStrc;
	}

	public Date getDateJournee() {
		return dateJournee;
	}

	public void setDateJournee(Date dateJournee) {
		this.dateJournee = dateJournee;
	}

	
	

}