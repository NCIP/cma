import gov.nih.nci.caintegrator.application.lists.UserList
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListItem
import gov.nih.nci.caintegrator.application.lists.ListType
import org.springframework.web.context.request.RequestContextHolder

abstract class AbstractClinicalService {

    boolean transactional = false
   
    /**
     * Return a list of sample ids for the specified sample groups
     */
    public List getIdsForSampleGroups(List sampleGroups) {
  	  Set idSet = new HashSet()
        Set groupNames = new HashSet(sampleGroups)
        
        if ((sampleGroups != null) && (sampleGroups.size() > 0)) {
      	  
      	  def webRequest= RequestContextHolder.currentRequestAttributes()        	  
            def session = webRequest.session    	 

            UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session);
    	      List<UserList> lists = userListBeanHelper.getLists(ListType.PatientDID);
            java.util.List listItems = null
    	      lists.each { ul ->
    	         logger.debug("Checking listName=${ul.getName()}")
    	         println("Checking listName=${ul.getName()}")
    	         if (groupNames.contains(ul.getName())) {
    	           listItems = ul.getListItems()
    	           println("getIdsForSampleGroups: list=${ul.getName()} numItems=${listItems.size()}")
    	           listItems.each { li -> 
    	        	  idSet.add(li.getName())
    	           }
    	         }
    	      }    	      	  
        }
  	  return new ArrayList(idSet);    	     	     	
  }
    
    
  /**
   * Return the list of sample ids for a sample group
   */
   public List getIdsForSampleGroup(String sampleGroup) {
     List groupList = new ArrayList();
     groupList.add(sampleGroup)
     return getIdsForSampleGroups(groupList)	   
   }

    /**
     * Get a comma delimited string containing all of the ids in the input idSet
     */
    public String getIdString(Set idSet) {
    	int ind = 0;
    	int numIds  = idSet.size()
    	StringBuffer idSB = new StringBuffer()
    	
    	idSB.append("(")
    			
    	idSet.each { id -> 
    	         
    	         if (ind == (numIds-1))  {
    	        	 idSB.append("'${id}'")    	    	        	 
    	         }
    	         else {
    	        	idSB.append("'${id}',")    	    	        	
    	         }
                 ind++
    	}
    	idSB.append(")")
    	return idSB.toString()
    }
    
    def serviceMethod() {

    }
}
