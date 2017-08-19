package com.pfe.model;

public class DetailOperMoyPaiement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields    

     private Long numCodDomp;
     private OperationMoyPay operationMoyPay;
	 private String libCommission;//Lib//:commission exp:commission de remise effet

     private String codTypDomp;//C//:commission
     private Long montValDomp;//:montant commission

    // Constructors

    /** default constructor */
    public DetailOperMoyPaiement() {
    }

    
    /** full constructor */
    public DetailOperMoyPaiement(Long numCodDomp, OperationMoyPay operationMoyPay, String codTypDomp, Long montValDomp,Long montValDDomp) {
        this.numCodDomp = numCodDomp;
        this.operationMoyPay = operationMoyPay;
        this.codTypDomp = codTypDomp;
        this.montValDomp = montValDomp;
    }


	public Long getNumCodDomp() {
		return numCodDomp;
	}


	public void setNumCodDomp(Long numCodDomp) {
		this.numCodDomp = numCodDomp;
	}


	public OperationMoyPay getOperationMoyPay() {
		return operationMoyPay;
	}


	public void setOperationMoyPay(OperationMoyPay operationMoyPay) {
		this.operationMoyPay = operationMoyPay;
	}


	public String getLibCommission() {
		return libCommission;
	}


	public void setLibCommission(String libCommission) {
		this.libCommission = libCommission;
	}


	public String getCodTypDomp() {
		return codTypDomp;
	}


	public void setCodTypDomp(String codTypDomp) {
		this.codTypDomp = codTypDomp;
	}


	public Long getMontValDomp() {
		return montValDomp;
	}


	public void setMontValDomp(Long montValDomp) {
		this.montValDomp = montValDomp;
	}

   

    
}
