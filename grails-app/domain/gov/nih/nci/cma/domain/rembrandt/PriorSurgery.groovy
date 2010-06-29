package gov.nih.nci.cma.domain.rembrandt

/**
 * The PriorSurgery entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:34 $
 *
 *
 */
class PriorSurgery {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'PRIOR_SURGERY'
         version false
         id column:'PRIOR_SURGERY_ID', cache:'read-only'
         institutionId column:'INSTITUTION_ID', cache:'read-only' 
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT' , cache:'read-only'
         procedureTitle column:'PROCEDURE_TITLE', cache:'read-only' 
         tumorHistology column:'TUMOR_HISTOLOGY' , cache:'read-only'
         surgeryDate column:'SURGERY_DATE', cache:'read-only' 
         surgeryOutcome column:'SURGERY_OUTCOME' , cache:'read-only'
         patientDid column:'PATIENT_DID', cache:'read-only'
    }    
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.lang.String procedureTitle
    java.lang.String tumorHistology
    java.util.Date surgeryDate
    java.lang.String surgeryOutcome
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientDidPatientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        patientDid(nullable: false, size: 0..22)
        procedureTitle(size: 0..50)
        tumorHistology(size: 0..50)
        surgeryDate(nullable: true)
        surgeryOutcome(size: 0..200)
        //patientDidPatientData()
    }
    String toString() {
        return "${id}" 
    }
}
