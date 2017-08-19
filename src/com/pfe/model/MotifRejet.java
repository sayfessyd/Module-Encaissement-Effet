package com.pfe.model;

public class MotifRejet implements Comparable<MotifRejet> {

	private Long codeRej;
	private String motifRej;

	@Override
	public int compareTo(MotifRejet mr) {
	    return mr.getMotifRej().compareTo(this.getMotifRej());
	}
	
	public Long getCodeRej() {
		return codeRej;
	}

	public void setCodeRej(Long codeRej) {
		this.codeRej = codeRej;
	}

	public String getMotifRej() {
		return motifRej;
	}

	public void setMotifRej(String motifRej) {
		this.motifRej = motifRej;
	}

	@Override
	public String toString() {
		return motifRej;
	}
	
	

}
