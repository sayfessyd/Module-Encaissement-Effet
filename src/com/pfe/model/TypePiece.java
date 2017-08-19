package com.pfe.model;

public class TypePiece implements java.io.Serializable {


    // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codTpceTpce;
    private String libTpceTpce;
    private String libSiglTpce;


    // Constructors

    /** default constructor */
    public TypePiece() {
    }

    /** minimal constructor */
    public TypePiece(Long codTpceTpce, String libTpceTpce, 
                     String libSiglTpce) {
        this.codTpceTpce = codTpceTpce;
        this.libTpceTpce = libTpceTpce;
        this.libSiglTpce = libSiglTpce;
    }

	public Long getCodTpceTpce() {
		return codTpceTpce;
	}

	public void setCodTpceTpce(Long codTpceTpce) {
		this.codTpceTpce = codTpceTpce;
	}

	public String getLibTpceTpce() {
		return libTpceTpce;
	}

	public void setLibTpceTpce(String libTpceTpce) {
		this.libTpceTpce = libTpceTpce;
	}

	public String getLibSiglTpce() {
		return libSiglTpce;
	}

	public void setLibSiglTpce(String libSiglTpce) {
		this.libSiglTpce = libSiglTpce;
	}

}
