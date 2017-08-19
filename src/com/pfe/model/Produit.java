package com.pfe.model;

public class

Produit  implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields  




    private Long codPrdPrd;
    private String libPrdPrd;
    


    // Constructors

    /** default constructor */
    public Produit() {
    }

    /** minimal constructor */
    public Produit(Long codPrdPrd, 
                   String libPrdPrd) {
        this.codPrdPrd = codPrdPrd;
        this.libPrdPrd = libPrdPrd;
    }

	public Long getCodPrdPrd() {
		return codPrdPrd;
	}

	public void setCodPrdPrd(Long codPrdPrd) {
		this.codPrdPrd = codPrdPrd;
	}

	public String getLibPrdPrd() {
		return libPrdPrd;
	}

	public void setLibPrdPrd(String libPrdPrd) {
		this.libPrdPrd = libPrdPrd;
	}

    // Property accessors
    
    

}
