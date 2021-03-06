import gov.nih.nci.caintegrator.application.lists.UserListBean;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.cma.CacheConstants;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.ListOrigin;
import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.cache.PresentationCacheManager;
import gov.nih.nci.caintegrator.application.cache.PresentationTierCache;

import org.codehaus.groovy.grails.commons.*

import org.springframework.web.context.request.RequestContextHolder

class DefaultListLoaderService {

    boolean transactional = false

    static scope = 'prototype'
    
    def serviceMethod() {}
       
    def loadDefaultLists() 	{ 
    
        def webRequest= RequestContextHolder.currentRequestAttributes()  
    	def session = webRequest.session
    	PresentationTierCache presentationCacheManager = PresentationCacheManager.getInstance();

        UserListBean userListBean = (UserListBean) session.getAttribute(CacheConstants.USER_LISTS);
        if (userListBean == null) {
   	  		userListBean = new UserListBean();
 
  	   	// Load user lists 
   	   	// We are loading using grails and then converting to list objects in application-commons 
   	   	// so that we can reuse the list management functionality w/o rewriting it in grail (for now).                   
        def listObjs = gov.nih.nci.cma.domain.CmaList.list()
          
        java.util.List<UserList> userLists = new ArrayList<UserList>();
                
        def idMapping
          
        switch(ConfigurationHolder.config.cma.dataContext)	{
        	case "Rembrandt":
        	  	idMapping = gov.nih.nci.cma.domain.rembrandt.PatientData.executeQuery( "select distinct sampleId from gov.nih.nci.cma.domain.rembrandt.PatientData where institutionId='8'" );
        		break;
          	default:
          		idMapping = CmaStudyParticipant.executeQuery( "select distinct a.participantDid from CmaStudyParticipant a" );
        	break;
        }
        
        UserList ul;
        List<ListItem> items;
          
        items = new ArrayList<ListItem>();
        idMapping.each { m ->
        	ListItem i = new ListItem(m) 
          	items.add(i)
        }
        ul = new UserList(CacheConstants.ALL_USER_LISTS, ListType.PatientDID, items, Collections.emptyList())
        userLists.add(ul)

          
        listObjs.each{ gl ->
          
        	def gItems = gl.listItems
            items = new ArrayList<ListItem>();
            gItems.each{ gi ->      
            	ListItem i = new ListItem(gi.itemName, gi.listName)
           	  	i.setId(gi.id);
           	  	i.setRank(gi.rank)
           	  	i.setNotes(gi.itemDescription)                    	                      	 
           	  	items.add(i)
            }
                                   
            ListType lt = ListType.valueOf(gl.type)
	       	ul = new UserList(gl.name, lt, items, Collections.emptyList())
	       	ul.setAuthor(gl.author)
	       	ul.setCategory(gl.category)
	       	ul.setInstitute(gl.institution)
	       	ul.setDateCreated(gl.creationDate)
	       	  
	       	if (gl.subtype != null) {
	       		ListSubType lst = ListSubType.valueOf(gl.subtype)
	       		ul.setListSubType(lst)
	       	}
	       	  
	       	ul.setId(gl.id)
	       	  
	       	if (gl.origin != null) {
	       		ListOrigin lo = ListOrigin.valueOf(gl.origin)
	       		ul.setListOrigin(lo)
	       	}
	       	  
	       	userLists.add(ul)                	                   	  
        }
          
        presentationCacheManager.addNonPersistableToSessionCache(session.getId(), CacheConstants.USER_LISTS, userListBean);
           
        UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId());
        userListBeanHelper.addBean(session.getId(),CacheConstants.USER_LISTS,userListBean);            
        for(UserList legacyul: userLists){
            userListBeanHelper.addList(legacyul);
        }
      }
    }
    
    def areListsLoaded()	{
    	def webRequest= RequestContextHolder.currentRequestAttributes()  
    	def session = webRequest.session
    	UserListBeanHelper helper = new UserListBeanHelper(session);
    	if(helper == null || helper.getAllLists().isEmpty())	{
    		return false;
    	}
    	else	{
    		return true;
    	}
    }
    
    def nsListCollection
    
    def getPatientLists = { sid, sortList ->
		UserListBeanHelper userListBeanHelper = new UserListBeanHelper(sid);
	    List<UserList> lists = userListBeanHelper.getLists(ListType.PatientDID);
	    ArrayList patientCollection = new ArrayList();
	    nsListCollection = new ArrayList();
	    for(UserList l: lists){
	    	print "Evaluating list: " + l.getName();
	        patientCollection.add(l.getName());
	        
	        if ( l.getName().equals(CacheConstants.ALL_USER_LISTS) || 
	        	 l.getListSubType() != ListSubType.GENE_PLOT ) {
	        	nsListCollection.add(l.getName());
	    		println "Added list: " + l.getName();
	        }

	    }
	    if(sortList)	{
	    	Collections.sort(patientCollection);
	    	Collections.sort(nsListCollection);
	    }
	    
	    return patientCollection;
    }
    
    def getGeneLists = { sid, sortList ->
		UserListBeanHelper userListBeanHelper = new UserListBeanHelper(sid);
	    List<UserList> lists = userListBeanHelper.getLists(ListType.Gene);
	    ArrayList listCollection = new ArrayList();
	    for(UserList l: lists){
	    	listCollection.add(l.getName());
	    }
	    if(sortList)	{
	    	Collections.sort(listCollection);
	    }
	    return listCollection;
	}
}
