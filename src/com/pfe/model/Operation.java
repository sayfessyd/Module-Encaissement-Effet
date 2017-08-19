package com.pfe.model;

public class Operation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long codOperOper;
	private String codTypOper;
	private String libOperOper;

	// Constructors

	/** default constructor */
	public Operation() {
		super();
	}

	/** minimal constructor */
	public Operation(Long codOperOper, String libOperOper) {
		this.codOperOper = codOperOper;
		this.libOperOper = libOperOper;
	}

	public Operation(Long codOperOper, String codTypOper, String libOperOper) {
		super();
		this.codOperOper = codOperOper;
		this.codTypOper = codTypOper;
		this.libOperOper = libOperOper;
	}

	public Long getCodOperOper() {
		return codOperOper;
	}

	public void setCodOperOper(Long codOperOper) {
		this.codOperOper = codOperOper;
	}

	public String getCodTypOper() {
		return codTypOper;
	}

	public void setCodTypOper(String codTypOper) {
		this.codTypOper = codTypOper;
	}

	public String getLibOperOper() {
		return libOperOper;
	}

	public void setLibOperOper(String libOperOper) {
		this.libOperOper = libOperOper;
	}

	@Override
	public String toString() {
		return "Operation [codOperOper=" + codOperOper + ", codTypOper=" + codTypOper + ", libOperOper=" + libOperOper
				+ "]";
	}

}
