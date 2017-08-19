package com.pfe.model;


public class Adresse{

	private Long numSeqAdr;
    private String immeuble;
    private String rue;
    private String cite;
    private String ville;
    private String codCpCp;
    private String codPaysPays;


    public Long getNumSeqAdr() {
		return numSeqAdr;
	}

	public void setNumSeqAdr(Long numSeqAdr) {
		this.numSeqAdr = numSeqAdr;
	}

	public void setImmeuble(String immeuble) {
        this.immeuble = immeuble;
    }

    public String getImmeuble() {
        return immeuble;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getRue() {
        return rue;
    }

    public void setCite(String cite) {
        this.cite = cite;
    }

    public String getCite() {
        return cite;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }


    public String toString() {
        String vImmeuble = " ";
        String vRue = " ";
        String vCite = " ";
        String vVille = " ";
        String vPays = " ";
        String vCodCpCp = " ";

        if (immeuble != null)
            vImmeuble = immeuble.trim();
        if (rue != null)
            vRue = rue.trim();
        if (cite != null)
            vCite = cite.trim();
        if (ville != null)
            vVille = ville.trim();
        if (codPaysPays != null)
            vPays = codPaysPays.trim();
        if (codCpCp != null)
            vCodCpCp = codCpCp.trim();

        return vImmeuble + ' ' + vRue + ' ' + vCite + ' ' + vVille + ' ' + 
            vPays + ' ' + vCodCpCp;
    }


    public void setCodCpCp(String codCpCp) {
        this.codCpCp = codCpCp;
    }

    public String getCodCpCp() {
        return codCpCp;
    }

    public void setCodPaysPays(String codPaysPays) {
        this.codPaysPays = codPaysPays;
    }

    public String getCodPaysPays() {
        return codPaysPays;
    }
}

