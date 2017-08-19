package com.pfe.model;



public class Devise implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codDevDev;
	private String libDevDev;
	private String libFracDev;
	private String libSiglDev;
	private Long nbrDecDev;
	private Long nbrUnitDev;
	

	// Constructors

	/** default constructor */
	public Devise() {
	}

	/** default constructor */
	public Devise(Long codDevDev) {
		this.codDevDev = codDevDev;
	}

	/** minimal constructor */
	public Devise(Long codDevDev, String libDevDev, String libFracDev, String libSiglDev, Long montPoffDev,
			Long nbrDecDev, Long nbrUnitDev) {
		this.codDevDev = codDevDev;
		this.libDevDev = libDevDev;
		this.libFracDev = libFracDev;
		this.libSiglDev = libSiglDev;
		this.nbrDecDev = nbrDecDev;
		this.nbrUnitDev = nbrUnitDev;
	}

	// Property accessors
	
	

	public int compareTo(Devise obj) {

		if (this == obj)
			return 0;
		if (obj == null)
			return 1;
		Devise other = (Devise) obj;
		if (codDevDev != null) {

			return codDevDev.compareTo(other.getCodDevDev());

		} else {
			return -1;
		}
	}

	public Long getCodDevDev() {
		return codDevDev;
	}

	public void setCodDevDev(Long codDevDev) {
		this.codDevDev = codDevDev;
	}

	public String getLibDevDev() {
		return libDevDev;
	}

	public void setLibDevDev(String libDevDev) {
		this.libDevDev = libDevDev;
	}

	public String getLibFracDev() {
		return libFracDev;
	}

	public void setLibFracDev(String libFracDev) {
		this.libFracDev = libFracDev;
	}

	public String getLibSiglDev() {
		return libSiglDev;
	}

	public void setLibSiglDev(String libSiglDev) {
		this.libSiglDev = libSiglDev;
	}

	public Long getNbrDecDev() {
		return nbrDecDev;
	}

	public void setNbrDecDev(Long nbrDecDev) {
		this.nbrDecDev = nbrDecDev;
	}

	public Long getNbrUnitDev() {
		return nbrUnitDev;
	}

	public void setNbrUnitDev(Long nbrUnitDev) {
		this.nbrUnitDev = nbrUnitDev;
	}

	@Override
	public String toString() {
		if (libSiglDev != null) {
			return libSiglDev;
		} else {
			return "";
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codDevDev == null) ? 0 : codDevDev.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Devise other = (Devise) obj;
		if (codDevDev == null) {
			if (other.codDevDev != null)
				return false;
		} else if (!codDevDev.equals(other.codDevDev))
			return false;
		return true;
	}


}
