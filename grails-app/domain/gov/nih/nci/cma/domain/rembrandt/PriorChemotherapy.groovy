package gov.nih.nci.cma.domain.rembrandt

/**
 * The PriorChemotherapy entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:34 $
 *
 *
 */
class PriorChemotherapy {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'PRIOR_CHEMOTHERAPY'
         version false
         id column:'PRIOR_CHEMOTRX_ID', cache:'read-only'
         institutionId column:'INSTITUTION_ID', cache:'read-only' 
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT', cache:'read-only' 
         agentId column:'AGENT_ID' , cache:'read-only'
         agentName column:'AGENT_NAME' , cache:'read-only'
         courseCount column:'COURSE_COUNT', cache:'read-only' 
         doseStartDate column:'DOSE_START_DATE' , cache:'read-only'
         doseStopDate column:'DOSE_STOP_DATE', cache:'read-only' 
         studySource column:'STUDY_SOURCE', cache:'read-only' 
         protocolNumber column:'PROTOCOL_NUMBER' , cache:'read-only'
         patientDid column:'PATIENT_DID', cache:'read-only', insert="false", update="false"
         //patientData column:'PATIENT_DID', cache:'read-only', insert="false", update="false"
    }
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.math.BigDecimal agentId
    java.lang.String agentName
    java.math.BigDecimal courseCount
    java.util.Date doseStartDate
    java.util.Date doseStopDate
    java.lang.String studySource
    java.lang.String protocolNumber
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        patientDid(nullable: false, size: 0..22)
        agentId(nullable: true, size: 0..22)
        agentName(size: 0..100)
        courseCount(nullable: true, size: 0..22)
        doseStartDate(nullable: true)
        doseStopDate(nullable: true)
        studySource(size: 0..20)
        protocolNumber(size: 0..20)
        //patientData()
    }
    String toString() {
        return "${id}" 
    }
}
