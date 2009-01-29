package gov.nih.nci.cma.domain

class CmaRembClin {
	String sampleId
	String parm
    Integer repNum
	String parmCharValue
	Integer parmNumValue
	
	public String toString() {
	  return "${sampleId}, ${parm}, ${repNum}, ${parmCharValue}, ${parmNumValue}"
	}	
}
