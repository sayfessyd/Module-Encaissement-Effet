package com.pfe.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.pfe.model.ContratCpt;
import com.pfe.model.Personne;
import com.pfe.service.ContratService;

@ManagedBean(name = "ContratCtrl")
@ViewScoped
public class ContratManagedBean implements Serializable {

	private static final long serialVersionUID = 8307945057636952735L;
	@ManagedProperty(value = "#{ContratService}")
	private ContratService service;

	private ContratCpt c;
	private Long input;
	private String nomPrenom = "";
	private String pce = "";
	private String codeS = "";
	private String etatC = "";
	private String type = "Pièce";
	private String lieu = "";
	private String adresse = "";
	private boolean inputDisabled = false;

	public void afficherInfoPersonne() {
		Long codPrd;
		Long numCpt;
		try {
			Long prefixe = Long.valueOf(input.toString().substring(0, 3));
			Long suffixe = Long.valueOf(input.toString().substring(3));
			codPrd = Long.valueOf(suffixe.toString().substring(0, 3));
			numCpt = Long.valueOf(suffixe.toString().substring(3));
			c = service.findContratWithPers(prefixe, codPrd, numCpt);
			if (c==null)
			{
				error("Compte introuvable.");
				return;
			}
			else
				info("Information","Compte trouvé.");
			Personne p = c.getPersonne();
			nomPrenom = p.getNomNomPers()+" "+p.getNomPrnPers();
			etatC = p.getNomRsPers();
			codeS = p.getCodSexPers();
			lieu = p.getLieuNaisPers();
			adresse = p.getAdresseProf().toString();
			pce = p.getNumPcePers();
			type = p.getTypePiece().getLibSiglTpce();
			inputDisabled = true;
		} catch (Exception e) {
			error("Compte invalide.");
			e.printStackTrace();
		}
	}
	
	public void chercherCompte(String input) {
		Long codPrd;
		Long numCpt;
		try {
			Long prefixe = Long.valueOf(input.toString().substring(0, 3));
			Long suffixe = Long.valueOf(input.toString().substring(3));
			codPrd = Long.valueOf(suffixe.toString().substring(0, 3));
			numCpt = Long.valueOf(suffixe.toString().substring(3));
			c = service.findContrat(prefixe, codPrd, numCpt);
			if (c==null)
			{
				error("Compte introuvable.");
				return;
			}
			else
				info("Information","Compte trouvé.");
			inputDisabled = true;
		} catch (Exception e) {
			error("Compte invalide.");
			e.printStackTrace();
		}
	}
	
	public void info(String title,String message) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, title, message));
		
	}

	public void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATTENTION !", message));
	}
	
	public ContratService getService() {
		return service;
	}

	public void setService(ContratService service) {
		this.service = service;
	}

	public ContratCpt getC() {
		return c;
	}

	public void setC(ContratCpt c) {
		this.c = c;
	}

	public Long getInput() {
		return input;
	}

	public void setInput(Long input) {
		this.input = input;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	

	public String getPce() {
		return pce;
	}


	public void setPce(String pce) {
		this.pce = pce;
	}


	public String getCodeS() {
		return codeS;
	}


	public void setCodeS(String codeS) {
		this.codeS = codeS;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getEtatC() {
		return etatC;
	}


	public void setEtatC(String etatC) {
		this.etatC = etatC;
	}


	public boolean isInputDisabled() {
		return inputDisabled;
	}


	public void setInputDisabled(boolean inputDisabled) {
		this.inputDisabled = inputDisabled;
	}


}
