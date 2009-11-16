package gov.nih.nci.cma.domain.rembrandt

/**
 * The PtSurgery entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:34 $
 *
 *
 */
class PtSurgery {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'PT_SURGERY'
         version false
         id column:'PT_SURGERY_ID', cache:'read-only'         
         institutionId column:'INSTITUTION_ID', cache:'read-only' 
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT', cache:'read-only' 
         surgeryDate column:'SURGERY_DATE', cache:'read-only' 
         procedureTitle column:'PROCEDURE_TITLE', cache:'read-only' 
         indication column:'INDICATION', cache:'read-only' 
         histoDiagnosis column:'HISTO_DIAGNOSIS', cache:'read-only' 
         surgeryOutcome column:'SURGERY_OUTCOME', cache:'read-only' 
         patientDataId column:'PATIENT_DID', cache:'read-only'
    }    
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.util.Date surgeryDate
    java.lang.String procedureTitle
    java.lang.String indication
    java.lang.String histoDiagnosis
    java.lang.String surgeryOutcome
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        surgeryDate(nullable: true)
        procedureTitle(size: 0..50)
        indication(size: 0..20)
        histoDiagnosis(size: 0..100)
        surgeryOutcome(size: 0..25)
        patientDid(nullable: false, size: 0..22)
        //patientData()
    }
    String toString() {
        return "${id}" 
    }
}
