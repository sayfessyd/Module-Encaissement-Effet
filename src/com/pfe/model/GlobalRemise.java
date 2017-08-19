package com.pfe.model;

import java.util.Date;

public class GlobalRemise implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	Long numRem;
	Long montRem;
	Long nbrEffRem;
	String typeRem;
	Date datCreRem;
	String etatRem;
	String motifRem;
	
	public Long getNumRem() {
		return numRem;
	}
	public void setNumRem(Long numRem) {
		this.numRem = numRem;
	}
	public Long getMontRem() {
		return montRem;
	}
	public void setMontRem(Long montRem) {
		this.montRem = montRem;
	}
	public Long getNbrEffRem() {
		return nbrEffRem;
	}
	public void setNbrEffRem(Long nbrEffRem) {
		this.nbrEffRem = nbrEffRem;
	}
	public String getTypeRem() {
		return typeRem;
	}
	public void setTypeRem(String typeRem) {
		this.typeRem = typeRem;
	}
	public Date getDatCreRem() {
		return datCreRem;
	}
	public void setDatCreRem(Date datCreRem) {
		this.datCreRem = datCreRem;
	}
	public String getEtatRem() {
		return etatRem;
	}
	public void setEtatRem(String etatRem) {
		this.etatRem = etatRem;
	}
	public String getMotifRem() {
		return motifRem;
	}
	public void setMotifRem(String motifRem) {
		this.motifRem = motifRem;
	}
	
}
