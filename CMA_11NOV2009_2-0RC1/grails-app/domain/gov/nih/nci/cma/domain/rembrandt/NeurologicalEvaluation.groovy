package gov.nih.nci.cma.domain.rembrandt

/**
 * The NeurologicalEvaluation entity.
 *
 * @author    
 * @version $Revision: 1.1 $, $Date: 2008-11-04 15:41:33 $
 *
 *
 */
class NeurologicalEvaluation {
	
	//static belongsTo = PatientData
	
    static mapping = {
         table 'NEUROLOGICAL_EVALUATION'
         version false
         id		  column:'NEURO_EVALUATION_ID', cache:'read-only'       
         institutionId column:'INSTITUTION_ID' , cache:'read-only'
         datasetId column:'DATASET_ID', cache:'read-only' 
         timePoint column:'TIME_POINT' , cache:'read-only'
         followupDate column:'FOLLOWUP_DATE', cache:'read-only' 
         followupMonth column:'FOLLOWUP_MONTH', cache:'read-only' 
         neuroEvaluationDate column:'NEURO_EVALUATION_DATE', cache:'read-only' 
         karnofskyScore column:'KARNOFSKY_SCORE', cache:'read-only' 
         lanskyScore column:'LANSKY_SCORE' , cache:'read-only'
         neuroExam column:'NEURO_EXAM' , cache:'read-only'
         mriCtScore column:'MRI_CT_SCORE' , cache:'read-only'
         steroidDoseStatus column:'STEROID_DOSE_STATUS' , cache:'read-only'
         antiConvulsantStatus column:'ANTI_CONVULSANT_STATUS', cache:'read-only' 
         neuroExamDesc column:'NEURO_EXAM_DESC' , cache:'read-only'
         mriCtScoreDesc column:'MRI_CT_SCORE_DESC', cache:'read-only' 
         patientDid column:'PATIENT_DID', cache:'read-only'
         //patientData column:'PATIENT_DID', cache:'read-only', insert="false", update="false"
    }    
    java.math.BigDecimal institutionId
    java.math.BigDecimal datasetId
    java.lang.String timePoint
    java.util.Date followupDate
    java.math.BigDecimal followupMonth
    java.util.Date neuroEvaluationDate
    java.math.BigDecimal karnofskyScore
    java.math.BigDecimal lanskyScore
    java.math.BigDecimal neuroExam
    java.math.BigDecimal mriCtScore
    java.lang.String steroidDoseStatus
    java.lang.String antiConvulsantStatus
    java.lang.String neuroExamDesc
    java.lang.String mriCtScoreDesc
    java.math.BigDecimal patientDid
    // Relation
    //PatientData patientData
    static constraints = {        
        institutionId(nullable: true, size: 0..22)
        datasetId(nullable: true, size: 0..22)
        timePoint(size: 0..30)
        patientDid(nullable: false, size: 0..22)
        followupDate(nullable: true)
        followupMonth(nullable: true, size: 0..22)
        neuroEvaluationDate(nullable: true)
        karnofskyScore(nullable: true, size: 0..22)
        lanskyScore(nullable: true, size: 0..22)
        neuroExam(nullable: true, size: 0..22)
        mriCtScore(nullable: true, size: 0..22)
        steroidDoseStatus(size: 0..50)
        antiConvulsantStatus(size: 0..50)
        neuroExamDesc(size: 0..200)
        mriCtScoreDesc(size: 0..200)
        //patientData()
    }
    String toString() {
        return "${id}" 
    }
}
