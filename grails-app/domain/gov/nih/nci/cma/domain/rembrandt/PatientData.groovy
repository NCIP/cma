package gov.nih.nci.cma.domain.rembrandt

/**
 * The PatientData entity.
 *
 * @author    
 * @version $Revision: 1.2 $, $Date: 2008-11-28 20:13:47 $
 *
 *
 */
class PatientData {
    static mapping = {
         table 'PATIENT_DATA'
         version false
         id		  column: 'PATIENT_DID', cache:'read-only', insert:false, update:false
         sampleId column:'SAMPLE_ID', cache:'read-only' 
         biospecimenId column:'BIOSPECIMEN_ID', cache:'read-only' 
         //patientDid column:'PATIENT_DID', cache:'read-only' 
         populationTypeId column:'POPULATION_TYPE_ID', cache:'read-only' 
         age column:'AGE', cache:'read-only' 
         ageGroup column:'AGE_GROUP', cache:'read-only' 
         survivalLength column:'SURVIVAL_LENGTH', cache:'read-only' 
         survivLengthRange column:'SURVIV_LENGTH_RANGE', cache:'read-only' 
         gender column:'GENDER', cache:'read-only' 
         diseaseType column:'DISEASE_TYPE' , cache:'read-only'
         whoGrade column:'WHO_GRADE', cache:'read-only' 
         censoringStatus column:'CENSORING_STATUS', cache:'read-only' 
         race column:'RACE', cache:'read-only' 
         institutionId column:'INSTITUTION_ID', cache:'read-only' 
         institutionName column:'INSTITUTION_NAME', cache:'read-only' 
         specimenName column:'SPECIMEN_NAME', cache:'read-only'
         //neuroEvalList column:'PATIENT_DID', cache:'read-only', insert:false, update:false
         //priorChemoList column:'PATIENT_DID', cache:'read-only', insert="false", update="false"         
    }   
    java.lang.String sampleId
    java.math.BigDecimal biospecimenId
    java.math.BigDecimal patientDid
    java.math.BigDecimal populationTypeId
    java.math.BigDecimal age
    java.lang.String ageGroup
    java.math.BigDecimal survivalLength
    java.lang.String survivLengthRange
    java.lang.String gender
    java.lang.String diseaseType
    java.lang.String whoGrade
    java.lang.String censoringStatus
    java.lang.String race
    java.math.BigDecimal institutionId
    java.lang.String institutionName
    java.lang.String specimenName
    //static hasMany = [ priorChemoList : PriorChemotherapy , priorRadiationtherapyList : PriorRadiationtherapy , priorSurgeryList : PriorSurgery , ptChemoList : PtChemotherapy , ptRadiationtherapyList : PtRadiationtherapy , ptSurgeryList : PtSurgery , neuroEvalList : NeurologicalEvaluation ]
    //static mappedBy = [priorChemoList:"patientDid", neuroEvalList:"patientDid"]


    static constraints = {
        sampleId(size: 0..50)
        biospecimenId(nullable: true, size: 0..22)
        //patientDid(nullable: false, size: 0..22)
        populationTypeId(nullable: true, size: 0..22)
        age(nullable: true, size: 0..22)
        ageGroup(size: 0..20)
        survivalLength(nullable: true, size: 0..22)
        survivLengthRange(size: 0..15)
        gender(size: 0..5)
        diseaseType(size: 0..100)
        whoGrade(size: 0..5)
        censoringStatus(size: 0..5)
        race(size: 0..15)
        institutionId(nullable: true, size: 0..22)
        institutionName(size: 0..50)
        specimenName(size: 0..100)
        // Bidirectional oneToMany
       // priorChemoList()
        // Bidirectional oneToMany
       // priorRadiationtherapyList()
        // Bidirectional oneToMany
       // priorSurgeryList()
        // Bidirectional oneToMany
       // ptChemoList()
        // Bidirectional oneToMany
      //  ptRadiationtherapyList()
        // Bidirectional oneToMany
      //  ptSurgeryList()
        // Bidirectional oneToMany
        //neuroEvalList()
    }
    String toString() {
        return "${id}" 
    }
}
