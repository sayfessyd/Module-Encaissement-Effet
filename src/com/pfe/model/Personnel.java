package com.pfe.model;



public class Personnel implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

    private String numMatrUser;   
    private Structure structure;   
    private Long codStatUser;
    private String numCinUser;
    


    // Constructors

    /** default constructor */
    public Personnel() {
    }

    /** minimal constructor */
    public Personnel(String numMatrUser,  Structure structure, 
                     String nomNomUser, String nomPrnUser) {
        this.numMatrUser = numMatrUser;
        
        this.structure = structure;
     
    }

	public Personnel(String numMatrUser, Structure structure, Long codStatUser, String numCinUser) {
		super();
		this.numMatrUser = numMatrUser;
		this.structure = structure;
		this.codStatUser = codStatUser;
		this.numCinUser = numCinUser;
	}
	
    // Property accessors


	public String getNumMatrUser() {
		return numMatrUser;
	}

	public void setNumMatrUser(String numMatrUser) {
		this.numMatrUser = numMatrUser;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public Long getCodStatUser() {
		return codStatUser;
	}

	public void setCodStatUser(Long codStatUser) {
		this.codStatUser = codStatUser;
	}

	public String getNumCinUser() {
		return numCinUser;
	}

	public void setNumCinUser(String numCinUser) {
		this.numCinUser = numCinUser;
	}




	
    
}
