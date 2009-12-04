package gov.nih.nci.cma.domain.rembrandt

/**
 * The PtChemotherapy entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:33 $
 *
 *
 */
class PtChemotherapy {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'PT_CHEMOTHERAPY'
         version false
         id column:'PT_CHEMOTRX_ID', cache:'read-only'           
         institutionId column:'INSTITUTION_ID' , cache:'read-only'
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT' , cache:'read-only'
         agentId column:'AGENT_ID' , cache:'read-only'
         agentName column:'AGENT_NAME' , cache:'read-only'
         regimenNumber column:'REGIMEN_NUMBER', cache:'read-only' 
         doseStartDate column:'DOSE_START_DATE', cache:'read-only' 
         courseCount column:'COURSE_COUNT' , cache:'read-only'
         doseStopDate column:'DOSE_STOP_DATE' , cache:'read-only'
         studySource column:'STUDY_SOURCE', cache:'read-only' 
         protocolNumber column:'PROTOCOL_NUMBER', cache:'read-only' 
         patientDid column:'PATIENT_DID', cache:'read-only'
    }    
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.math.BigDecimal agentId
    java.lang.String agentName
    java.math.BigDecimal regimenNumber
    java.util.Date doseStartDate
    java.math.BigDecimal courseCount
    java.util.Date doseStopDate
    java.lang.String studySource
    java.lang.String protocolNumber
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientDidPatientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        agentId(nullable: true, size: 0..22)
        agentName(size: 0..100)
        regimenNumber(nullable: true, size: 0..22)
        doseStartDate(nullable: true)
        courseCount(nullable: true, size: 0..22)
        doseStopDate(nullable: true)
        studySource(size: 0..20)
        protocolNumber(size: 0..20)
        patientDid(nullable: false, size: 0..22)
        //patientDidPatientData()
    }
    String toString() {
        return "${id}" 
    }
}
