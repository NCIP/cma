

class TCGAClinicalService {

    boolean transactional = false

    def serviceMethod() {

    }
    
    public List getColumnNames() {
       return Collections.emptyList()
    }
    
    public List<String> getPermissibleValues(String paramName) {
       return Collections.emptyList()    	    	
    }
    
    def getClinicalData = { clinicalForm -> 
	    clinicalForm.getParameterNames().each	{
	    	System.out.println(it + ": " + clinicalForm.getParameter(it))
	    }
    
    }
    
    public List getClinicalDataForGroup(String groupName) {
      return Collections.emptyList()
    }
}
