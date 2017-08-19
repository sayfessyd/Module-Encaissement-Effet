package com.pfe.model;

import java.util.Date;
import java.util.HashSet;

import java.util.Set;

public class OperationMoyPay implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long numOperOmp;
	private Personnel personnelValideur;
	private Structure structureInitiatrice;
	private Personnel personnelInitiateur;
	private TypePiece typePieceDemandeur;//2:CIN
	private ContratCpt contratCpt;
	private Devise devise;//788:DINARS
	private Date datOperOmp;
	private String numPcedOmp;
	private String codEtatOmp;//V
	private String codSensOmp;//C :credit - D:Debit
	private Long montDinOmp;//mnt effet
	private String nomNomdOmp;
	private String nomPrndOmp;
	private Long montDevOmp;
	private String libMotfOmp;
	private Long montTvaOmp;
	private Produit produit;//1061
	private Date datSystOmp;
	private Long montApreOmp;
	private Long montDevApreOmp;// 
	private Long montSoldCcpt;
	private Long montSdevCcpt;
	private String codRefCfOmp;//numero effet
	private Operation operation;//722:remise

	private Set<DetailOperMoyPaiement> detailOperMoyPaiements = new HashSet<DetailOperMoyPaiement>(0);
	

	// Constructors

	/** default constructor */
	public OperationMoyPay() {
	}

	/** minimal constructor */
	public OperationMoyPay(Long numOperOmp, Personnel personnelValideur, TypePiece typePieceDemandeur,
			ContratCpt contratCpt, Devise devise, Date datOperOmp, String codDemOmp, String numPcedOmp, Date datValOmp,
			String codEtatOmp, String codSensOmp, Date datSystOmp) {
		this.numOperOmp = numOperOmp;

		this.personnelValideur = personnelValideur;
		this.typePieceDemandeur = typePieceDemandeur;
		this.contratCpt = contratCpt;
		this.devise = devise;
		this.datOperOmp = datOperOmp;
		this.numPcedOmp = numPcedOmp;
		this.codEtatOmp = codEtatOmp;
		this.codSensOmp = codSensOmp;
		this.datSystOmp = datSystOmp;
	}

	public OperationMoyPay(Long numOperOmp, Personnel personnelValideur, Structure structureInitiatrice,
			Personnel personnelInitiateur, TypePiece typePieceDemandeur, ContratCpt contratCpt, Devise devise,
			Date datOperOmp, String numPcedOmp, String codEtatOmp, String codSensOmp, Long montDinOmp,
			String nomNomdOmp, String nomPrndOmp, Long montDevOmp, String libMotfOmp, Long montTvaOmp, Produit produit,
			Date datSystOmp, Long montApreOmp, Long montDevApreOmp, Long montSoldCcpt, Long montSdevCcpt,
			String codRefCfOmp, Operation operation, Set<DetailOperMoyPaiement> detailOperMoyPaiements) {
		super();
		this.numOperOmp = numOperOmp;
		this.personnelValideur = personnelValideur;
		this.structureInitiatrice = structureInitiatrice;
		this.personnelInitiateur = personnelInitiateur;
		this.typePieceDemandeur = typePieceDemandeur;
		this.contratCpt = contratCpt;
		this.devise = devise;
		this.datOperOmp = datOperOmp;
		this.numPcedOmp = numPcedOmp;
		this.codEtatOmp = codEtatOmp;
		this.codSensOmp = codSensOmp;
		this.montDinOmp = montDinOmp;
		this.nomNomdOmp = nomNomdOmp;
		this.nomPrndOmp = nomPrndOmp;
		this.montDevOmp = montDevOmp;
		this.libMotfOmp = libMotfOmp;
		this.montTvaOmp = montTvaOmp;
		this.produit = produit;
		this.datSystOmp = datSystOmp;
		this.montApreOmp = montApreOmp;
		this.montDevApreOmp = montDevApreOmp;
		this.montSoldCcpt = montSoldCcpt;
		this.montSdevCcpt = montSdevCcpt;
		this.codRefCfOmp = codRefCfOmp;
		this.operation = operation;
		this.detailOperMoyPaiements = detailOperMoyPaiements;
	}

	public Long getNumOperOmp() {
		return numOperOmp;
	}

	public void setNumOperOmp(Long numOperOmp) {
		this.numOperOmp = numOperOmp;
	}

	public Personnel getPersonnelValideur() {
		return personnelValideur;
	}

	public void setPersonnelValideur(Personnel personnelValideur) {
		this.personnelValideur = personnelValideur;
	}

	public Structure getStructureInitiatrice() {
		return structureInitiatrice;
	}

	public void setStructureInitiatrice(Structure structureInitiatrice) {
		this.structureInitiatrice = structureInitiatrice;
	}

	public Personnel getPersonnelInitiateur() {
		return personnelInitiateur;
	}

	public void setPersonnelInitiateur(Personnel personnelInitiateur) {
		this.personnelInitiateur = personnelInitiateur;
	}

	public TypePiece getTypePieceDemandeur() {
		return typePieceDemandeur;
	}

	public void setTypePieceDemandeur(TypePiece typePieceDemandeur) {
		this.typePieceDemandeur = typePieceDemandeur;
	}

	public ContratCpt getContratCpt() {
		return contratCpt;
	}

	public void setContratCpt(ContratCpt contratCpt) {
		this.contratCpt = contratCpt;
	}

	public Devise getDevise() {
		return devise;
	}

	public void setDevise(Devise devise) {
		this.devise = devise;
	}

	public Date getDatOperOmp() {
		return datOperOmp;
	}

	public void setDatOperOmp(Date datOperOmp) {
		this.datOperOmp = datOperOmp;
	}

	public String getNumPcedOmp() {
		return numPcedOmp;
	}

	public void setNumPcedOmp(String numPcedOmp) {
		this.numPcedOmp = numPcedOmp;
	}

	public String getCodEtatOmp() {
		return codEtatOmp;
	}

	public void setCodEtatOmp(String codEtatOmp) {
		this.codEtatOmp = codEtatOmp;
	}

	public String getCodSensOmp() {
		return codSensOmp;
	}

	public void setCodSensOmp(String codSensOmp) {
		this.codSensOmp = codSensOmp;
	}

	public Long getMontDinOmp() {
		return montDinOmp;
	}

	public void setMontDinOmp(Long montDinOmp) {
		this.montDinOmp = montDinOmp;
	}

	public String getNomNomdOmp() {
		return nomNomdOmp;
	}

	public void setNomNomdOmp(String nomNomdOmp) {
		this.nomNomdOmp = nomNomdOmp;
	}

	public String getNomPrndOmp() {
		return nomPrndOmp;
	}

	public void setNomPrndOmp(String nomPrndOmp) {
		this.nomPrndOmp = nomPrndOmp;
	}

	public Long getMontDevOmp() {
		return montDevOmp;
	}

	public void setMontDevOmp(Long montDevOmp) {
		this.montDevOmp = montDevOmp;
	}

	public String getLibMotfOmp() {
		return libMotfOmp;
	}

	public void setLibMotfOmp(String libMotfOmp) {
		this.libMotfOmp = libMotfOmp;
	}

	public Long getMontTvaOmp() {
		return montTvaOmp;
	}

	public void setMontTvaOmp(Long montTvaOmp) {
		this.montTvaOmp = montTvaOmp;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Date getDatSystOmp() {
		return datSystOmp;
	}

	public void setDatSystOmp(Date datSystOmp) {
		this.datSystOmp = datSystOmp;
	}

	public Long getMontApreOmp() {
		return montApreOmp;
	}

	public void setMontApreOmp(Long montApreOmp) {
		this.montApreOmp = montApreOmp;
	}

	public Long getMontDevApreOmp() {
		return montDevApreOmp;
	}

	public void setMontDevApreOmp(Long montDevApreOmp) {
		this.montDevApreOmp = montDevApreOmp;
	}

	public Long getMontSoldCcpt() {
		return montSoldCcpt;
	}

	public void setMontSoldCcpt(Long montSoldCcpt) {
		this.montSoldCcpt = montSoldCcpt;
	}

	public Long getMontSdevCcpt() {
		return montSdevCcpt;
	}

	public void setMontSdevCcpt(Long montSdevCcpt) {
		this.montSdevCcpt = montSdevCcpt;
	}

	public String getCodRefCfOmp() {
		return codRefCfOmp;
	}

	public void setCodRefCfOmp(String codRefCfOmp) {
		this.codRefCfOmp = codRefCfOmp;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Set<DetailOperMoyPaiement> getDetailOperMoyPaiements() {
		return detailOperMoyPaiements;
	}

	public void setDetailOperMoyPaiements(Set<DetailOperMoyPaiement> detailOperMoyPaiements) {
		this.detailOperMoyPaiements = detailOperMoyPaiements;
	}

	 
}
