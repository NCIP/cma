package gov.nih.nci.cma.domain.rembrandt

/**
 * The PtRadiationtherapy entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:34 $
 *
 *
 */
class PtRadiationtherapy {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'PT_RADIATIONTHERAPY'
         version false
         id column:'PT_RADIATIONTRX_ID', cache:'read-only'           
         institutionId column:'INSTITUTION_ID', cache:'read-only' 
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT' , cache:'read-only'
         radiationSite column:'RADIATION_SITE', cache:'read-only' 
         doseStartDate column:'DOSE_START_DATE', cache:'read-only' 
         fractionDose column:'FRACTION_DOSE', cache:'read-only' 
         fractionNumber column:'FRACTION_NUMBER', cache:'read-only' 
         doseStopDate column:'DOSE_STOP_DATE' , cache:'read-only'
         neurosisStatus column:'NEUROSIS_STATUS' , cache:'read-only'
         radiationType column:'RADIATION_TYPE', cache:'read-only'
         patientDid column:'PATIENT_DID', cache:'read-only'
    }    
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.lang.String radiationSite
    java.util.Date doseStartDate
    java.math.BigDecimal fractionDose
    java.math.BigDecimal fractionNumber
    java.util.Date doseStopDate
    java.lang.String neurosisStatus
    java.lang.String radiationType
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientDidPatientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        radiationSite(size: 0..200)
        doseStartDate(nullable: true)
        fractionDose(nullable: true, size: 0..22)
        fractionNumber(nullable: true, size: 0..22)
        doseStopDate(nullable: true)
        neurosisStatus(size: 0..30)
        radiationType(size: 0..30)
        patientDid(nullable: false, size: 0..22)
        //patientDidPatientData()
    }
    String toString() {
        return "${id}" 
    }
}
