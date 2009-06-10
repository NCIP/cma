  import java.util.Date;
  
  import java.text.DateFormat;
  import java.text.SimpleDateFormat;

  import org.apache.log4j.Logger;
  
  import org.springframework.web.context.request.RequestContextHolder;

  import gov.nih.nci.caintegrator.application.lists.UserList;
  import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
  import gov.nih.nci.caintegrator.application.lists.ListItem;
  import gov.nih.nci.caintegrator.application.lists.ListType

  import gov.nih.nci.cma.domain.tcgaovarian.ClinicalOvarian;
  
  import gov.nih.nci.cma.clinical.TCGAOvarianClinicalReportBean;


class TCGAOvarianClinicalService extends AbstractClinicalService {

    boolean transactional = false
    
    private static Logger logger = Logger.getLogger(TCGAOvarianClinicalService.class);

    def serviceMethod() {
    }
           
    public List getColumnNames() {
       return Collections.emptyList()
    }
    
    public List<String> getPermissibleValues(String paramName) {
    	List<String> permValues = new ArrayList()
    	
    	if (paramName.equals("tumorTissueSite")) {
    		permValues.add("OVARY")
    	}
    	else if (paramName.equals("vitalStatus")) {
    		permValues.add("LIVING")
    		permValues.add("DECEASED")
    	}
    	else if (paramName.equals("gender")) {
    		permValues.add("MALE")
    		permValues.add("FEMALE")
    	}
    	else if (paramName.equals("race")) {
    		permValues.add("WHITE")
    		permValues.add("ASIAN")
    		permValues.add("BLACK OR AFRICAN AMERICAN")
    		permValues.add("NATIVE HAWAIIAN OR PACIFIC ISLANDER")
    	}
    	else if (paramName.equals("yesNo") ) {
    		permValues.add("Yes")
    		permValues.add("No")
    	}
    	else if (paramName.equals("anatomicOrganSubdivision")) {
    		permValues.add("Left")
    		permValues.add("Right")
    		permValues.add("Bilateral")
    	}
    	else if (paramName.equals("initPathologicDxMethod")) {
    		permValues.add("CYTOLOGY")
    		permValues.add("INCISION BIOPSY")
    		permValues.add("TUMOR RESECTION")
    		permValues.add("EXCISIONAL BIOPSY")
    		permValues.add("FINE NEEDLE ASPIRATION BIOPSY")
    	}
    	else if (paramName.equals("personNeoplasmStatus")) {
    		permValues.add("TUMOR FREE")
    		permValues.add("WITH TUMOR")
    	}
    	else if (paramName.equals("siteOfFirstRecurrence")) {
    		permValues.add("LOCO-REGIONAL")
    		permValues.add("METASTASIS")
    	}
    	else if (paramName.equals("tumorStage")) {
    		permValues.add("IA")
    		permValues.add("IB")
    		permValues.add("IIB")
    		permValues.add("IIC")
    		permValues.add("IIIB")
    		permValues.add("IIIB")
    		permValues.add("IV")
    	}
    	else if (paramName.equals("tumorGrade")) {
    		permValues.add("G2")
    		permValues.add("G3")
    		permValues.add("GX")
    	}
    	else if (paramName.equals("tumorResidualDisease")) {
    		permValues.add("> 20 mm")
    		permValues.add("11-20 mm")
    		permValues.add("No Macroscopic disease")
    		permValues.add("1-10 mm")
    	}
    	else if (paramName.equals("primaryTherapyOutcomeSuccess")) {
    		permValues.add("STABLE DISEASE")
    		permValues.add("PARTIAL RESPONSE")
    		permValues.add("COMPLETE RESPONSE")
    		permValues.add("PROGRESSIVE DISEASE")
    	}
    	else if (paramName.equals("histologicalType")) {
    		permValues.add("Serous Cystadenocarcinoma")
    	}
    	else if (paramName.equals("ethnicity")) {
    		permValues.add("HISPANIC OR LATINO")
    		permValues.add("NOT HISPANIC OR LATINO")
    	}
    	else if (paramName.equals("jewishOrigin")) {
    		permValues.add("ASHKENAZI")
    		permValues.add("SEPHARDIC")
    	}
    	
    	return permValues;    	
    }
    
    /**
     * Extracts a list of unique ids from a list of domain objects
     */
    private List getIdList(List domainObjs) {
      if (domainObjs == null) {
    	logger.warn("getIdList passed null parameter")
    	return Collections.emptyList()
      }
      Set retSet = new HashSet()
      domainObjs.each { d ->
        if (d != null) {
          retSet.add(d.getPtid())  
        }
        else {
          logger.warn("getIdList got null domain object.")
        }
      }
      return new ArrayList(retSet)
    }
           
    
    
    public List<String> getIdsForTumorTissueSite(String value) {
       List clinList = ClinicalOvarian.findAllByTumorTissueSiteLike(value.toUpperCase())
       println("getIdsForTumorTissueSite returned numRows=${clinList.size()} value=${value}")
       return getIdList(clinList)
    }
    
    public List<String> getIdsForDob(Date startValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByDobBetween(startValue, endValue)
        println("getIdsForDob returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForDod(Date startValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByDodBetween(startValue, endValue)
        println("getIdsForDod returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForLastFollowUp(Date startValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByLastFollowUpBetween(startValue, endValue)
        println("getIdsForLastFollowUp returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForVitalStatus(String value) {
    	List clinList = ClinicalOvarian.findAllByVitalStatusLike(value.toUpperCase())
    	println("getIdsForVitalStatus returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    /*
    public List<String> getIdsForPriorGlioma(String value) {
    	List clinList = ClinicalOvarian.findAllByPriorGliomaLike(value.toUpperCase())
    	println("getIdsForPriorGlioma returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    */
    
    public List<String> getIdsForRace(String value) {
    	List clinList = ClinicalOvarian.findAllByRaceLike(value.toUpperCase())
    	println("getIdsForRace returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    /*
    public List<String> getIdsForSmokingHistory(String value) {
    	List clinList = ClinicalOvarian.findAllBySmokingHistoryLike(value.toUpperCase())
    	println("getIdsForSmokingHistory returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAlcoholConsumption(String value) {
    	List clinList = ClinicalOvarian.findAllByAlcoholConsumptionLike(value.toUpperCase())
    	println("getIdsForAlcoholConsumption returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForExperimentalExposure(String value) {
    	List clinList = ClinicalOvarian.findAllByExperimentalExposureLike(value.toUpperCase())
    	println("getIdsForExperimentalExposure returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    */
    
    public List<String> getIdsForGender(String value) {
    	List clinList = ClinicalOvarian.findAllByGenderLike(value.toUpperCase())
    	println("getIdsForGender returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForInformedConsentAcquired(String value) {
    	//List clinList = ClinicalOvarian.findAllByInformedConsentAcquiredLike(value.toUpperCase())
    	List clinList = ClinicalOvarian.findAllByInformedConsentAcquiredLike(value)
    	println("getIdsForInformedConsentAcquired returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForHistologicalType(String value) {
    	//List clinList = ClinicalOvarian.findAllByHistologicalTypeLike(value.toUpperCase())
    	List clinList = ClinicalOvarian.findAllByHistologicalTypeLike(value)
    	println("getIdsForHistologicalType returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    /*
    public List<String> getIdsForBcrSiteId(String value) {
    	List clinList = ClinicalOvarian.findAllByBcrSiteIdLike(value.toUpperCase())
    	println("getIdsForBcrSiteId returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForRevision(String value) {
    	List clinList = ClinicalOvarian.findAllByRevisionLike(value.toUpperCase())
    	println("getIdsForRevision returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    */
    
    public List<String> getIdsForPretreatmentTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByPretreatmentTherapyLike(value)
    	println("getIdsForPretreatmentHistory returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForRadiationTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByRadiationTherapyLike(value)
    	println("getIdsForRadiationTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForInitPathologicDxMethod(String value) {
    	List clinList = ClinicalOvarian.findAllByInitPathologicDxMethodLike(value)
    	println("getIdsForInitPathologicDxMethod returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForInitPathologicDxDate(Date startValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByInitPathologicDxDateBetween(startValue, endValue)
        println("getIdsForInitPathologicDxDate returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForChemotherapy(String value) {
    	//List clinList = ClinicalOvarian.findAllByChemotherapyLike(value)
    	List clinList = ClinicalOvarian.findAllByChemotherapyLike(value)
    	println("getIdsForChemotherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForImmunotherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByImmunotherapyLike(value)
    	println("getIdsForImmunotherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
     public List<String> getIdsForHormonalTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByHormonalTherapyLike(value)
    	println("getIdsForHormonalTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForTargetedMolecularTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByTargetedMolecularTherapyLike(value)
    	println("getIdsForTargetedMolecularTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForTumorPrgr(Date beginValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByTumorPrgrBetween(beginDate, endDate)
        println("getIdsForTumorPrgr returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForTumorRecur(Date beginValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllByTumorRecurBetween(beginDate, endDate)
        println("getIdsForTumorRecur returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForEthnicity(String value) {
    	List clinList = ClinicalOvarian.findAllByEthnicityLike(value.toUpperCase())
    	println("getIdsForEthnicity returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAdditionalRadiationTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByAdditionalRadiationTherapyLike(value)
    	println("getIdsForAdditionalRadiationTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
     
    public List<String> getIdsForAdditionalChemotherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByAdditionalChemotherapyLike(value)
    	println("getIdsForAdditionalChemotherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAdditionalImmunotherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByAdditionalImmunotherapyLike(value)
    	println("getIdsForAdditionalImmunotherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAdditionalHormoneTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByAdditionalHormoneTherapyLike(value)
    	println("getIdsForAdditionalHormoneTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAdditionalDrugTherapy(String value) {
    	List clinList = ClinicalOvarian.findAllByAdditionalDrugTherapyLike(value)
    	println("getIdsForAdditionalDrugTherapy returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForAnatomicOrganSubdivision(String value) {
    	List clinList = ClinicalOvarian.findAllByAnatomicOrganSubdivisionLike(value)
    	println("getIdsForAnatomicOrganSubdivision returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForPersonNeoplasmStatus(String value) {
    	List clinList = ClinicalOvarian.findAllByPersonNeoplasmStatusLike(value)
    	println("getIdsForPersonNeoplasmStatus returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForSiteOfTumorFirstRecurrence(String value) {
    	List clinList = ClinicalOvarian.findAllBySiteOfTumorFirstRecurrenceLike(value)
    	println("getIdsForSiteOfTumorFirstRecurrence returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForJewishOrigin(String value) {
    	List clinList = ClinicalOvarian.findAllByJewishOriginLike(value)
    	println("getIdsForJewishOrigin returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    /*
    public List<String> getIdsForSurProcPrfm(Date startValue, Date endValue) {
    	List clinList = ClinicalOvarian.findAllBySurProcPrfmLike(startValue, endValue)
        println("getIdsForSurProcPrfm returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForUncOsEvent(String value) {
    	List clinList = ClinicalOvarian.findAllByUncOsEventLike(value.toUpperCase())
    	println("getIdsForUncOsEvent returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForUncOsDuration(String value) {
    	List clinList = ClinicalOvarian.findAllByUncOsDurationLike(value.toUpperCase())
    	println("getIdsForUncOsDuration returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
    */
    
    public List<String> getIdsForDodMinusDx(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodMinusDxBetween(lower, upper)
        println("getIdsForDodMinusDx returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
    
    public List<String> getIdsForDodfuMinusDx(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodfuMinusDxBetween(lower, upper)
        println("getIdsForDodfuMinusDx returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)
    }
      
    public List<String> getIdsForDodMinusDop(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodMinusDopBetween(lower, upper)
        println("getIdsForDodfuMinusDx returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(getIdsForDodMinusDop)
    }

    public List<String> getIdsForDodfuMinusDop(Integer lower, Integer upper) {
    	List clinList = ClinicalNew.findAllByDodfuMinusDopBetween(lower, upper)
        println("getIdsForDodfuMinusDop returned numRows=${clinList.size()} lowerValue=${startValue} upperValue=${endValue}")
    	return getIdList(clinList)   	
    }
    
    public List<String> getIdsForPtid(String value) {
    	List clinList = ClinicalOvarian.findAllByPtidLike(value.toUpperCase())
    	println("getIdsForPtid returned numRows=${clinList.size()} value=${value}")
    	return getIdList(clinList)
    }
      
    
    def getClinicalData = { clinicalForm -> 
               
	    clinicalForm.getParameterNames().each	{
	    	println(it + ": " + clinicalForm.getParameter(it))
	    }
	    	    
	    String[] sampleGroups = (String[])clinicalForm.getParameterValues("sampleGroups")	    
	    String patientId = (String) clinicalForm.getParameter("patientId")
		String tumorTissueSite = (String) clinicalForm.getParameter("tumorTissueSite")
		//String priorGlioma = (String) clinicalForm.getParameter("priorGlioma")
	    String vitalStatus = (String) clinicalForm.getParameter("vitalStatus")
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")
	    Date dobBegin = (clinicalForm.getParameter("dobBegin") != null && clinicalForm.getParameter("dobBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("dobBegin")) : null
	    Date dobEnd = (clinicalForm.getParameter("dobBegin") != null && clinicalForm.getParameter("dobBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("dobEnd")) : null
	    Date dodBegin = (clinicalForm.getParameter("dodBegin") != null && clinicalForm.getParameter("dodBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("dodBegin")) : null
	    Date dodEnd = (clinicalForm.getParameter("dodEnd") != null && clinicalForm.getParameter("dodEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("dodEnd")) : null
	    Date lastFollowUpBegin = (clinicalForm.getParameter("lastFollowUpBegin") != null && clinicalForm.getParameter("lastFollowUpBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("lastFollowUpBegin")) : null
	    Date lastFollowUpEnd = (clinicalForm.getParameter("lastFollowUpEnd") != null && clinicalForm.getParameter("lastFollowUpEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("lastFollowUpEnd")) : null
		String race = (String) clinicalForm.getParameter("race")
		//String smokingHistory = (String) clinicalForm.getParameter("smokingHistory")
		//String alcoholConsumption = (String) clinicalForm.getParameter("alcoholConsumption")
		//String experimentalExposure = (String) clinicalForm.getParameter("experimentalExposure")
		String gender = (String)clinicalForm.getParameter("gender")			
		String histologicalType = (String)clinicalForm.getParameter("histologicalType")
		//String bcrSiteId = (String)clinicalForm.getParameter("bcrSiteId")
		//String revision = (String)clinicalForm.getParameter("bcrSiteId")
		String pretreatmentTherapy = (String)clinicalForm.getParameter("pretreatmentTherapy")
		String radiationTherapy = (String)clinicalForm.getParameter("radiationTherapy")
		String chemotherapy = (String)clinicalForm.getParameter("chemotherapy")
		String immunotherapy = (String)clinicalForm.getParameter("immunotherapy")
		String hormonalTherapy = (String)clinicalForm.getParameter("hormonalTherapy")
		String targetedMolecularTherapy = (String)clinicalForm.getParameter("targetedMolecularTherapy")
	    Date tumorPrgrBegin = (clinicalForm.getParameter("tumorPrgrBegin") != null && clinicalForm.getParameter("tumorPrgrBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("tumorPrgrBegin")) : null
	    Date tumorPrgrEnd = (clinicalForm.getParameter("tumorPrgrEnd") != null && clinicalForm.getParameter("tumorPrgrEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("tumorPrgrEnd")) : null
	    Date tumorRecurBegin = (clinicalForm.getParameter("tumorRecurBegin") != null && clinicalForm.getParameter("tumorRecurBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("tumorRecurBegin")) : null
	    Date tumorRecurEnd = (clinicalForm.getParameter("tumorRecurEnd") != null && clinicalForm.getParameter("tumorRecurEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("tumorRecurEnd")) : null
		String ethnicity = (String)clinicalForm.getParameter("ethnicity")
		String additionalRadiationTherapy = (String)clinicalForm.getParameter("additionalRadiationTherapy")
		String additionalChemotherapy = (String)clinicalForm.getParameter("additionalChemotherapy")
		String additionalImmunotherapy = (String)clinicalForm.getParameter("additionalChemotherapy")
		String additionalHormoneTherapy = (String)clinicalForm.getParameter("additionalHormoneTherapy")
		String additionalDrugTherapy = (String)clinicalForm.getParameter("additionalDrugTherapy")
		String anatomicOrganSubdivision = (String)clinicalForm.getParameter("anatomicOrganSubdivision")
	    Date initPathologicDxDateBegin = (clinicalForm.getParameter("initPathologicDxDateBegin") != null && clinicalForm.getParameter("initPathologicDxDateBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("initPathologicDxDateBegin")) : null
	    Date initPathologicDxDateEnd = (clinicalForm.getParameter("initPathologicDxDateEnd") != null && clinicalForm.getParameter("initPathologicDxDateEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("initPathologicDxDateEnd")) : null
		String initPathologicDxMethod = (String)clinicalForm.getParameter("initPathologicDxMethod")
		String personNeoplasmStatus = (String)clinicalForm.getParameter("personNeoplasmStatus")
		String siteOfTumorFirstRecurrence = (String)clinicalForm.getParameter("siteOfTumorFirstRecurrence")
		String tumorStage = (String)clinicalForm.getParameter("tumorStage")
		String tumorGrade = (String)clinicalForm.getParameter("tumorGrade")
		String tumorResidualDisease = (String)clinicalForm.getParameter("tumorResidualDisease")
		String primaryTherapyOutcomeSuccess = (String)clinicalForm.getParameter("primaryTherapyOutcomeSuccess")
		String jewishOrigin = (String)clinicalForm.getParameter("jewishOrigin")
	    //Date surProcPrfmBegin = (clinicalForm.getParameter("surProcPrfmBegin") != null && clinicalForm.getParameter("surProcPrfmBegin") != "") ? sdf.parse((String)clinicalForm.getParameter("surProcPrfmBegin")) : null
	    //Date surProcPrfmEnd = (clinicalForm.getParameter("surProcPrfmEnd") != null && clinicalForm.getParameter("surProcPrfmEnd") != "") ? sdf.parse((String)clinicalForm.getParameter("surProcPrfmEnd")) : null
		//String uncOsEvent = (String)clinicalForm.getParameter("uncOsEvent")
		//String uncOsDuration = (String)clinicalForm.getParameter("uncOsDuration")
		String informedConsentAcquired = (String)clinicalForm.getParameter("informedConsentAcquired")
		Integer dodMinusDopLower = clinicalForm.getParameter("dodMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopLower")) : null
		Integer dodMinusDopUpper = clinicalForm.getParameter("dodMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopUpper")) : null
		Integer dodfuMinusDopLower = clinicalForm.getParameter("dodfuMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopLower")) : null
		Integer dodfuMinusDopUpper = clinicalForm.getParameter("dodfuMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopUpper")) : null
		Integer dodMinusDxLower = clinicalForm.getParameter("dodMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopLower")) : null
		Integer dodMinusDxUpper = clinicalForm.getParameter("dodMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodMinusDopUpper")) : null
		Integer dodfuMinusDxLower = clinicalForm.getParameter("dodfuMinusDopLower")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopLower")) : null
		Integer dodfuMinusDxUpper = clinicalForm.getParameter("dodfuMinusDopUpper")!=null ? Integer.valueOf(clinicalForm.getParameter("dodfuMinusDopUpper")) : null
								
    	println "\n========================================================\n"
    	
	    List groupNames = new ArrayList()
		Set idSet = new HashSet()
        if ((sampleGroups != null) && (sampleGroups.length > 0)) {
    	  for (int i=0; i < sampleGroups.length ; i++) {
    		 groupNames.add(sampleGroups[i])
    	  }
    	  List ids = getIdsForSampleGroups(groupNames)    	  
    	  idSet.addAll(ids)
    	 println "\n\n The ID SET has ${idSet.size()} elements!!! \n\n"
        }
        
        // BCR Patient Barcode = Patient ID																
		if ((patientId != null ) && (!patientId.trim().equals("ANY")) && (patientId.trim().length() > 0)) {
		  idSet.add(patientId.trim())
    	println "\n\n AFTER adding Patient ID::The ID SET has ${idSet.size()} elements!!! \n\n"
		}
		
		if ((tumorTissueSite != null) && (!tumorTissueSite.equals("ANY"))) {
		  List ttsIds = getIdsForTumorTissueSite(tumorTissueSite)
		  idSet.retainAll(ttsIds)
    	println "\n\n AFTER adding Tumor Tissue Site::The ID SET has ${idSet.size()} elements!!! \n\n"
		}
		
		/*
        if ( (priorGlioma != null) && (!priorGlioma.equals("ANY"))) {
          List pgIds = getIdsForPriorGlioma(priorGlioma)
          idSet.retainAll(pgIds)        
    	println " AFTER adding Prior Glioma::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
        */
		
        if ((vitalStatus != null) && (!vitalStatus.equals("ANY"))) {
          List vsIds = getIdsForVitalStatus(vitalStatus)
          idSet.retainAll(vsIds)        
    	println "\n\n AFTER adding VItal Status::The ID SET has ${idSet.size()} elements!!! \n\n"
        }

        if ( (dobBegin != null) && (dobEnd != null) ) {
          List dobIds = getIdsForDob(dobBegin, dobEnd)
          idSet.retainAll(dobIds)        
    	println "\n\n AFTER adding DOB::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ( (dodBegin != null) && (dodEnd != null)  ) {
          List dodIds = getIdsForDod(dodBegin, dodEnd)
          idSet.retainAll(dodIds)        
    	println "\n\n AFTER adding DOD::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ( (lastFollowUpBegin != null) && (lastFollowUpEnd != null) ) {
          List lastFollowUpIds = getIdsForLastFollowUp(lastFollowUpBegin, lastFollowUpEnd)
          idSet.retainAll(lastFollowUpIds)        
    	println "\n\n AFTER adding LastFollowUpIds::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((race != null) && (!race.equals("ANY"))) {
          List raceIds = getIdsForRace(race)
          idSet.retainAll(raceIds)        
    	println "\n\n AFTER adding RACE::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
		/*
        if ((smokingHistory != null) && (!smokingHistory.equals("ANY"))) {
          List smokingHistoryIds = getIdsForSmokingHistory(smokingHistory)
          idSet.retainAll(smokingHistoryIds)        
    	println "\n\n AFTER adding RACE::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((alcoholConsumption != null) && (!alcoholConsumption.equals("ANY"))) {
          List alcoholConsumptionIds = getIdsForAlcoholConsumption(alcoholConsumption)
          idSet.retainAll(alcoholConsumptionIds)        
    	println "\n\n AFTER adding SmokingHistory::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((experimentalExposure != null) && (!experimentalExposure.equals("ANY"))) {
          List experimentalExposureIds = getIdsForExperimentalExposure(experimentalExposure)
          idSet.retainAll(experimentalExposureIds)        
    	println "\n\n AFTER adding ExperimentalExposure::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
        */
        
		if ((gender != null) && (!gender.equals("ANY"))) {
		  List genderIds = getIdsForGender(gender)
	      idSet.retainAll(genderIds)
    	println "\n\n AFTER adding GENDER::The ID SET has ${idSet.size()} elements!!! \n\n"
		}
		
		if (informedConsentAcquired != null) {
		  List icIds = getIdsForInformedConsentAcquired(informedConsentAcquired)
		  idSet.retainAll(icIds)
    	println "\n\n AFTER adding InformedConsentAcquired::The ID SET has ${idSet.size()} elements!!! \n\n"
		}
		
        if ((histologicalType != null) && (!histologicalType.equals("ANY"))) {
          List histologicalTypeIds = getIdsForHistologicalType(histologicalType)
          idSet.retainAll(histologicalTypeIds)        
    	println "\n\n AFTER adding HistologicalType::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
		/*
        if ((bcrSiteId != null) && (!bcrSiteId.equals("ANY")) && (bcrSiteId.trim().length() > 0)) {
          List bcrSiteIds = getIdsForBcrSiteId(bcrSiteId)
          idSet.retainAll(bcrSiteIds)        
    	println "\n\n AFTER adding BcrSiteId::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((revision != null) && (!revision.equals("ANY")) && (revision.trim().length() > 0)) {
          List revisionIds = getIdsForRevision(revision)
          idSet.retainAll(revisionIds)        
    	println "\n\n AFTER adding Revision::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
        */
		
        if ((pretreatmentTherapy != null) && (!pretreatmentTherapy.equals("ANY"))) {
          List pretreatmentTherapyIds = getIdsForPretreatmentTherapy(pretreatmentTherapy)
          idSet.retainAll(pretreatmentTherapyIds)        
    	println "\n\n AFTER adding PretreatmentTherapy::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((radiationTherapy != null) && (!radiationTherapy.equals("ANY"))) {
          List radiationTherapyIds = getIdsForRadiationTherapy(radiationTherapy)
          idSet.retainAll(radiationTherapyIds)        
    	println "\n\n AFTER adding RadiationTherapy::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((initPathologicDxMethod != null) && (!initPathologicDxMethod.equals("ANY"))) {
          List initPathologicDxMethodIds = getIdsForInitPathologicDxMethod(initPathologicDxMethod)
          idSet.retainAll(initPathologicDxMethodIds)        
    	println "\n\n AFTER adding InitPathologicDxMethod::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
        
        if ((initPathologicDxDateBegin != null) && (!initPathologicDxDateEnd!= null)) {
          List initPathologicDxDateIds = getIdsForInitPathologicDxDate(initPathologicDxDateBegin, initPathologicDxDateEnd)
          idSet.retainAll(initPathologicDxDateIds)        
    	println "\n\n AFTER adding initPathologicDxDate::The ID SET has ${idSet.size()} elements!!! \n\n"
        }
		
        if ((chemotherapy != null) && (!chemotherapy.equals("ANY"))) {
          List chemotherapyIds = getIdsForChemotherapy(chemotherapy)
          idSet.retainAll(chemotherapyIds)        
        }
		
        if ((immunotherapy != null) && (!immunotherapy.equals("ANY"))) {
          List immunotherapyIds = getIdsForImmunotherapy(immunotherapy)
          idSet.retainAll(immunotherapyIds)        
        }
		
        if ((hormonalTherapy != null) && (!hormonalTherapy.equals("ANY"))) {
          List hormonalTherapyIds = getIdsForHormonalTherapy(hormonalTherapy)
          idSet.retainAll(hormonalTherapyIds)        
        }
		
        if ((targetedMolecularTherapy != null) && (!targetedMolecularTherapy.equals("ANY"))) {
          List targetedMolecularTherapyIds = getIdsForTargetedMolecularTherapy(targetedMolecularTherapy)
          idSet.retainAll(targetedMolecularTherapyIds)        
        }
        
        if ((tumorPrgrBegin != null) && (!tumorPrgrEnd!= null)) {
          List tumorPrgrIds = getIdsForTumorPrgr(tumorPrgrBegin, tumorPrgrEnd)
          idSet.retainAll(tumorPrgrIds)        
        }
        
        if ((tumorRecurBegin != null) && (!tumorRecurEnd!= null)) {
          List tumorRecurIds = getIdsForTumorRecur(tumorRecurBegin, tumorRecurEnd)
          idSet.retainAll(tumorRecurIds)        
        }
		
        if ((ethnicity != null) && (!ethnicity.equals("ANY"))) {
          List ethnicityIds = getIdsForEthnicity(ethnicity)
          idSet.retainAll(ethnicityIds)        
        }
		
        if ((additionalRadiationTherapy != null) && (!additionalRadiationTherapy.equals("ANY"))) {
          List artTherapyIds = getIdsForAdditionalRadiationTherapy(additionalRadiationTherapy)
          idSet.retainAll(artTherapyIds)        
        }
		
        if ((additionalChemotherapy != null) && (!additionalChemotherapy.equals("ANY"))) {
          List acIds = getIdsForAdditionalChemotherapy(additionalChemotherapy)
          idSet.retainAll(acIds)        
        }
		
        if ((additionalImmunotherapy != null) && (!additionalImmunotherapy.equals("ANY"))) {
          List aiIds = getIdsForAdditionalImmunotherapy(additionalImmunotherapy)
          idSet.retainAll(aiIds)        
        }
		
        if ((additionalHormoneTherapy != null) && (!additionalHormoneTherapy.equals("ANY"))) {
          List ahtIds = getIdsForAdditionalHormoneTherapy(additionalHormoneTherapy)
    	  logger.debug("getIdsForAdditionalHormoneTherapy returned numRows=${ahtIds.size()}")
          idSet.retainAll(ahtIds)        
        }
		
        if ((additionalDrugTherapy != null) && (!additionalDrugTherapy.equals("ANY"))) {
          List adtIds = getIdsForAdditionalDrugTherapy(additionalDrugTherapy)
          idSet.retainAll(adtIds)        
        }
		
        if ((anatomicOrganSubdivision != null) && (!anatomicOrganSubdivision.equals("ANY"))) {
          List aosIds = getIdsForAnatomicOrganSubdivision(anatomicOrganSubdivision)
          idSet.retainAll(aosIds)        
        }
		
        if ((personNeoplasmStatus != null) && (!personNeoplasmStatus.equals("ANY"))) {
          List pnsIds = getIdsForPersonNeoplasmStatus(personNeoplasmStatus)
          idSet.retainAll(pnsIds)        
        }
		
        if ((siteOfTumorFirstRecurrence != null) && (!siteOfTumorFirstRecurrence.equals("ANY"))) {
          List sotfrIds = getIdsForSiteOfTumorFirstRecurrence(siteOfTumorFirstRecurrence)
          idSet.retainAll(sotfrIds)        
        }
		
        if ((tumorStage != null) && (!tumorStage.equals("ANY"))) {
          List tsIds = getIdsForTumorStage(tumorStage)
          idSet.retainAll(tsIds)        
        }
		
        if ((tumorGrade != null) && (!tumorGrade.equals("ANY"))) {
          List tgIds = getIdsForTumorGrade(tumorGrade)
          idSet.retainAll(tgIds)        
        }
		
        if ((tumorResidualDisease != null) && (!tumorResidualDisease.equals("ANY"))) {
          List trdIds = getIdsForTumorResidualDisease(tumorResidualDisease)
          idSet.retainAll(trdIds)        
        }
		
        if ((primaryTherapyOutcomeSuccess != null) && (!primaryTherapyOutcomeSuccess.equals("ANY"))) {
          List ptosIds = getIdsForPrimaryTherapyOutcomeSuccess(primaryTherapyOutcomeSuccess)
          idSet.retainAll(ptosIds)        
        }
		
        if ((jewishOrigin != null) && (!jewishOrigin.equals("ANY"))) {
          List joIds = getIdsForJewishOrigin(jewishOrigin)
          idSet.retainAll(joIds)        
        }
		
		/*
        if ((surProcPrfmBegin != null) && (surProcPrfmEnd != null)) {
          List sppIds = getIdsForSurProcPrfm(surProcPrfmBegin, surProcPrfmEnd)
          idSet.retainAll(sppIds)        
        }
		
        if ((uncOsEvent != null) && (!uncOsEvent.equals("ANY")) && (uncOsEvent.trim().length() > 0)) {
          List uoeIds = getIdsForUncOsEvent(uncOsEvent)
          idSet.retainAll(uoeIds)        
        }
		
        if ((uncOsDuration != null) && (!uncOsDuration.equals("ANY")) && (uncOsDuration.trim().length() > 0)) {
          List uodIds = getIdsForUncOsDuration(uncOsDuration)
          idSet.retainAll(uodIds)        
        }
        */
        
        if ((dodMinusDopLower != null) && (dodMinusDopUpper != null)) {
          List dodIds = getIdsForDodMinusDop(dodMinusDopLower, dodMinusDopUpper)                   
          idSet.retainAll(dodIds)          
        }
        
        if ((dodfuMinusDopLower != null) && (dodfuMinusDopUpper != null)) {
          List dodfids = getIdsForDodfuMinusDop(dodfuMinusDopLower, dodfuMinusDopUpper)
          idSet.retainAll(dodfids)
        }
        
        if ((dodMinusDxLower != null) && (dodMinusDxUpper != null)) {
          List dodxIds = getIdsForDodMinusDx(dodMinusDxLower, dodMinusDxUpper)                   
          idSet.retainAll(dodxIds)          
        }
        
        if ((dodfuMinusDxLower != null) && (dodfuMinusDxUpper != null)) {
          List dodfxIds = getIdsForDodfuMinusDx(dodfuMinusDxLower, dodfuMinusDxUpper)
          idSet.retainAll(dodfxIds)
        }
    	
    	println "\n\nId Set has ${idSet.size()} rows!!!! \n\n"
								
    	println "\n========================================================\n"
    	
 		
        List lookupIds = new ArrayList(idSet)
                
        return getClinicalData(lookupIds) 
    }
    
    
    
    public TCGAOvarianClinicalReportBean getRptBean(ClinicalOvarian co) {
    
      TCGAOvarianClinicalReportBean rptBean = new TCGAOvarianClinicalReportBean()
      
      rptBean.setPatientId(co.patientId)
      rptBean.setTumorTissueSite(co.tumorTissueSite)
      //rptBean.setPriorGlioma(co.priorGlioma)
      rptBean.setVitalStatus(co.vitalStatus)
      rptBean.setDob(co.dob)
      rptBean.setDod(co.dod)
      rptBean.setLastFollowUp(co.lastFollowUp)
      rptBean.setRace(co.race)
      //rptBean.setSmokingHistory(co.smokingHistory)
      //rptBean.setAlcoholConsumption(co.alcoholConsumption)
      //rptBean.setExperimentalExposure(co.experimentalExposure)
      rptBean.setGender(co.gender)
      rptBean.setInformedConsentAcquired(co.informedConsentAcquired)
      rptBean.setHistologicalType(co.histologicalType)
      //rptBean.setBcrSiteId(co.bcrSiteId)
      //rptBean.setRevision(co.revision)
      rptBean.setPretreatmentTherapy(co.pretreatmentTherapy)
      rptBean.setRadiationTherapy(co.radiationTherapy)
      rptBean.setInitPathologicDxDate(co.initPathologicDxDate)
      rptBean.setInitPathologicDxMethod(co.initPathologicDxMethod)
      rptBean.setChemotherapy(co.chemotherapy)
      rptBean.setImmunotherapy(co.immunotherapy)
      rptBean.setHormonalTherapy(co.hormonalTherapy)
      rptBean.setTargetedMolecularTherapy(co.targetedMolecularTherapy)
      rptBean.setTumorPrgr(co.tumorPrgr)
      rptBean.setTumorRecur(co.tumorRecur)
      rptBean.setEthnicity(co.ethnicity)
      rptBean.setAdditionalChemotherapy(co.additionalChemotherapy)
      rptBean.setAdditionalImmunotherapy(co.additionalImmunotherapy)
      rptBean.setAdditionalHormoneTherapy(co.additionalHormoneTherapy)
      rptBean.setAdditionalDrugTherapy(co.additionalDrugTherapy)
      rptBean.setAnatomicOrganSubdivision(co.anatomicOrganSubdivision)
      rptBean.setPersonNeoplasmStatus(co.personNeoplasmStatus)
      rptBean.setSiteOfTumorFirstRecurrence(co.siteOfTumorFirstRecurrence)
      rptBean.setTumorStage(co.tumorStage)
      rptBean.setTumorGrade(co.tumorGrade)
      rptBean.setTumorResidualDisease(co.tumorResidualDisease)
      rptBean.setPrimaryTherapyOutcomeSuccess(co.primaryTherapyOutcomeSuccess)
      rptBean.setJewishOrigin(co.jewishOrigin)
      /*
      rptBean.setSurProcPrfm(co.surProcPrfm)
      rptBean.setUncOsEvent(co.uncOsEvent)
      rptBean.setUncOsDuration(co.uncOsDuration)   
      */  
      rptBean.setDodMinusDop(co.dodMinusDop)
      rptBean.setDodfuMinusDop(co.dodfuMinusDop)
      rptBean.setDodMinusDx(co.dodMinusDx)
      rptBean.setDodfuMinusDx(co.dodfuMinusDx)
      rptBean.setPtid(co.ptid)
      
      return rptBean  	
    }
    
    public List getClinicalData(List patientIds) { 
    	
    	if ((patientIds == null) || (patientIds.isEmpty())) {
    	  println("Warning empty list passed to getClinicalData")
    	  return Collections.emptyList()
    	}
    	
    	println(">>>>>>>>>")
    	println("getClinicalData()  Pulling data for ${patientIds.size()} patient ids")
     	println("<<<<<<<<<")
    	
    	Set idSet = new HashSet(patientIds)
    	String idStr = getIdString(idSet)
    	String cnQS = "From gov.nih.nci.cma.domain.tcgaovarian.ClinicalOvarian co where co.ptid in ${idStr}"
    	println("getClinicalData cnQS=${cnQS}")
    	List cl = ClinicalOvarian.findAll(cnQS)
    	
    	println(">>>>>>>>>")
    	println("getClinicalData()  Resulting clinical data has ${cl.size()} results")
     	println("<<<<<<<<<")
     	
    	
    	println(">>>>>>>>>")
    	List rptBeanList = new ArrayList()
    	cl.each { c ->
    		println("getClinicalData()  The report bean ${getRptBean(c)}.toString()")
    	  rptBeanList.add(getRptBean(c))    		
    	}
     	println("<<<<<<<<<")
    	
    	return rptBeanList
    }
    
    
    public List getClinicalDataForGroup(String groupName) {
       List idList = getIdsForSampleGroup(groupName)
       println("getClinicalDataForGroup groupName=${groupName} returned numIds=${idList.size()}")
       return getClinicalData(idList)
    }
   
}
