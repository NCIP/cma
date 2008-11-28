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

import org.springframework.web.context.request.RequestContextHolder

class DefaultListLoaderService {

    boolean transactional = false

    static scope = 'prototype'
    
    def serviceMethod() {

    }
    
    
    def loadDefaultLists() 	{ //session ->
 
    //could also get session by:?
    	// import org.springframework.beans.factory.InitializingBean  
    	// import org.springframework.web.context.request.RequestContextHolder
    	def webRequest= RequestContextHolder.currentRequestAttributes()  
    	def session = webRequest.session
    	PresentationTierCache presentationCacheManager = PresentationCacheManager.getInstance();

        UserListBean userListBean = (UserListBean) session.getAttribute(CacheConstants.USER_LISTS);
        if (userListBean == null) {
   	  		userListBean = new UserListBean();
 
  	   // load user lists 
   	   // we are loading using grails and then converting to 
   	   // list objects in application-commons so that we can reuse the list management 
   	   // functionality w/o rewriting it in grail (for now).                   
          def listObjs = gov.nih.nci.cma.domain.CmaList.list()
          
          java.util.List<UserList> userLists = new ArrayList<UserList>();
          UserList ul;
          List<ListItem> items;
          
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
          
          presentationCacheManager.addNonPersistableToSessionCache(session.getId(), 
       		   CacheConstants.USER_LISTS, userListBean);
           
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
    
    def getPatientLists = { sid, sortList ->
		UserListBeanHelper userListBeanHelper = new UserListBeanHelper(sid);
	    List<UserList> lists = userListBeanHelper.getLists(ListType.PatientDID);
	    ArrayList patientCollection = new ArrayList();
	    for(UserList l: lists){
	        patientCollection.add(l.getName());
	    }
	    if(sortList)	{
	    	Collections.sort(patientCollection);
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
