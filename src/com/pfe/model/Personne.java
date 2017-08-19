package com.pfe.model;
import java.beans.Transient;
import java.util.Date;

public class Personne implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long numSeqPers;
	private TypePiece typePiece;
	private String numPcePers;
	private Date datDlvPers;
	private String libTitrPers;
	private String nomRsPers;
	private String libSiglPers;
	private String nomNomPers;
	private String nomPrnPers;
	private String codSexPers; // M/F
	private Date datNaisPers;
	private String lieuNaisPers;
	private String nomPrnpPers;
	private String nomNommPers;
	private String nomPrnmPers;
	private String numTelPers;
	private String numFaxPers;
	private String adrMailPers;
	private String adrWebPers;
	private Adresse adresseResid;
	private Adresse adresseProf;
	// *** Transient *********//
	private String relation;
	private String sigleTpce;

	// Constructors

	/** default constructor */
	public Personne() {
	}

	/** minimal constructor */
	public Personne(Long numSeqPers, TypePiece typePiece, String numPcePers) {
		this.numSeqPers = numSeqPers;
		this.typePiece = typePiece;
		this.numPcePers = numPcePers;
	}

	public Personne(Long numSeqPers, TypePiece typePiece, String numPcePers,
			Date datDlvPers, String libTitrPers, String nomRsPers,
			String libSiglPers, String nomNomPers, String nomPrnPers,
			String codSexPers, Date datNaisPers, String lieuNaisPers,
			String nomNomoPers, String nomPrnoPers, String nomNomaPers,
			String nomPrnaPers, String nomPrnpPers, String nomNommPers,
			String nomPrnmPers, String numDecrPers, Date datDecrPers,
			String numTelPers, String numFaxPers, String adrMailPers,
			String adrWebPers, Adresse adresseResid, Adresse adresseProf,
			String relation, String sigleTpce) {
		super();
		this.numSeqPers = numSeqPers;
		this.typePiece = typePiece;
		this.numPcePers = numPcePers;
		this.datDlvPers = datDlvPers;
		this.libTitrPers = libTitrPers;
		this.nomRsPers = nomRsPers;
		this.libSiglPers = libSiglPers;
		this.nomNomPers = nomNomPers;
		this.nomPrnPers = nomPrnPers;
		this.codSexPers = codSexPers;
		this.datNaisPers = datNaisPers;
		this.lieuNaisPers = lieuNaisPers;
		this.nomPrnpPers = nomPrnpPers;
		this.nomNommPers = nomNommPers;
		this.nomPrnmPers = nomPrnmPers;
		this.numTelPers = numTelPers;
		this.numFaxPers = numFaxPers;
		this.adrMailPers = adrMailPers;
		this.adrWebPers = adrWebPers;
		this.adresseResid = adresseResid;
		this.adresseProf = adresseProf;
		this.relation = relation;
		this.sigleTpce = sigleTpce;
	}

	public Long getNumSeqPers() {
		return numSeqPers;
	}

	public void setNumSeqPers(Long numSeqPers) {
		this.numSeqPers = numSeqPers;
	}

	public TypePiece getTypePiece() {
		return typePiece;
	}

	public void setTypePiece(TypePiece typePiece) {
		this.typePiece = typePiece;
	}

	public String getNumPcePers() {
		return numPcePers;
	}

	public void setNumPcePers(String numPcePers) {
		this.numPcePers = numPcePers;
	}

	public Date getDatDlvPers() {
		return datDlvPers;
	}

	public void setDatDlvPers(Date datDlvPers) {
		this.datDlvPers = datDlvPers;
	}

	public String getLibTitrPers() {
		return libTitrPers;
	}

	public void setLibTitrPers(String libTitrPers) {
		this.libTitrPers = libTitrPers;
	}

	public String getNomRsPers() {
		return nomRsPers;
	}

	public void setNomRsPers(String nomRsPers) {
		this.nomRsPers = nomRsPers;
	}

	public String getLibSiglPers() {
		return libSiglPers;
	}

	public void setLibSiglPers(String libSiglPers) {
		this.libSiglPers = libSiglPers;
	}

	public String getNomNomPers() {
		return nomNomPers;
	}

	public void setNomNomPers(String nomNomPers) {
		this.nomNomPers = nomNomPers;
	}

	public String getNomPrnPers() {
		return nomPrnPers;
	}

	public void setNomPrnPers(String nomPrnPers) {
		this.nomPrnPers = nomPrnPers;
	}

	public String getCodSexPers() {
		return codSexPers;
	}

	public void setCodSexPers(String codSexPers) {
		this.codSexPers = codSexPers;
	}

	public Date getDatNaisPers() {
		return datNaisPers;
	}

	public void setDatNaisPers(Date datNaisPers) {
		this.datNaisPers = datNaisPers;
	}

	public String getLieuNaisPers() {
		return lieuNaisPers;
	}

	public void setLieuNaisPers(String lieuNaisPers) {
		this.lieuNaisPers = lieuNaisPers;
	}

	public String getNomPrnpPers() {
		return nomPrnpPers;
	}

	public void setNomPrnpPers(String nomPrnpPers) {
		this.nomPrnpPers = nomPrnpPers;
	}

	public String getNomNommPers() {
		return nomNommPers;
	}

	public void setNomNommPers(String nomNommPers) {
		this.nomNommPers = nomNommPers;
	}

	public String getNomPrnmPers() {
		return nomPrnmPers;
	}

	public void setNomPrnmPers(String nomPrnmPers) {
		this.nomPrnmPers = nomPrnmPers;
	}

	public String getNumTelPers() {
		return numTelPers;
	}

	public void setNumTelPers(String numTelPers) {
		this.numTelPers = numTelPers;
	}

	public String getNumFaxPers() {
		return numFaxPers;
	}

	public void setNumFaxPers(String numFaxPers) {
		this.numFaxPers = numFaxPers;
	}

	public String getAdrMailPers() {
		return adrMailPers;
	}

	public void setAdrMailPers(String adrMailPers) {
		this.adrMailPers = adrMailPers;
	}

	public String getAdrWebPers() {
		return adrWebPers;
	}

	public void setAdrWebPers(String adrWebPers) {
		this.adrWebPers = adrWebPers;
	}

	public Adresse getAdresseResid() {
		return adresseResid;
	}

	public void setAdresseResid(Adresse adresseResid) {
		this.adresseResid = adresseResid;
	}

	public Adresse getAdresseProf() {
		return adresseProf;
	}

	public void setAdresseProf(Adresse adresseProf) {
		this.adresseProf = adresseProf;
	}

	@Transient
	public String getRelation() {
		if (getNomRsPers() == null || getNomRsPers().length() < 1) {
			relation = getNomNomPers() + "  " + getNomPrnPers();
		} else {
			relation = getNomRsPers();
		}

		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setSigleTpce(String sigleTpce) {
		this.sigleTpce = sigleTpce;
	}

	@Transient
	public String getSigleTpce() {

		if (typePiece != null && typePiece.getCodTpceTpce() != null) {

			int codPiece = typePiece.getCodTpceTpce().intValue();

			switch (codPiece) {
			case 1:
				sigleTpce = "RNE";
				break;
			case 2:
				sigleTpce = "CIN";
				break;
			case 3:
				sigleTpce = "PAS";
				break;
			case 4:
				sigleTpce = "SEJ";
				break;
			case 5:
				sigleTpce = "VISA";
				break;
			case 6:
				sigleTpce = "MNS";
				break;
			case 7:
				sigleTpce = "CR";
				break;
			case 8:
				sigleTpce = "LOC";
				break;
			case 9:
				sigleTpce = "RCS";
				break;
			case 10:
				sigleTpce = "NC";
				break;
			case 11:
				sigleTpce = "NO";
				break;
			case 12:
				sigleTpce = "NCTX";
				break;
			case 13:
				sigleTpce = "NP";
				break;
			default:
				sigleTpce = "CIN";
				break;
			}
		}
		return sigleTpce;
	}
}
