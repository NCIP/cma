package gov.nih.nci.cma.domain

class GeneDim {
	/*
	 * GENE_DIM_ID       NUMBER(19)                  NOT NULL,
	   FEATURE_NAME      VARCHAR2(2000 BYTE),
	   GENE_ID           VARCHAR2(255 BYTE),
	   CHROMOSOME        VARCHAR2(5 BYTE),
	   CHROMOSOME_START  NUMBER,
	   CHROMOSOME_END    NUMBER,
	   LOAD_DATE         DATE
	 */
	 Integer geneDimId
	 String featureName
	 String geneId
	 String chromosome
	 Integer chromosomeStart
	 Integer chromosomeEnd
	 Date date

}
