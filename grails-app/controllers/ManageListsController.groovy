  import java.io.*
  
  import java.util.Calendar;
  
  import javax.servlet.ServletOutputStream;

  import gov.nih.nci.caintegrator.application.cache.*
  import gov.nih.nci.caintegrator.application.lists.*

  import gov.nih.nci.cma.domain.ApplicationUser
  import gov.nih.nci.cma.domain.CmaList
  import gov.nih.nci.cma.domain.CmaListItem
  
  import gov.nih.nci.cma.list.ProjectListFilter

class ManageListsController {
    	
	// Create a new CMA List
	def theList		

	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
    def index = { 
    		ListType[] lts = ProjectListFilter.values();
    		render(view:'manageLists', model:[lts:lts])  		
    }
    
    def upload = {
    	def errors = "false"
		ListType[] lts = ProjectListFilter.values();
   	
    	if ( params["cmaListUpload"] == null || params["cmaListUpload"] == "") {
            flash.message = "Please specify a list to be uploaded."
            errors = "true" 
	    }
	    if ( params["listName"] == null || params["listName"] == "") {
            flash.message = "Please provide a name to be associated with the uploaded list."
            errors = "true" 
	    }
		if ( params["listDesc"] == null || params["listDesc"] == "") {
            flash.message = "Please provide a description to be associated with the uploaded list."
            errors = "true" 
	    }
	    
	    if ( errors == "true" ) {
			render(view:'manageLists', model:[lts:lts]) 
		} else {
	    	def theListFile = new File("theListFile.txt")
	    	params["cmaListUpload"].transferTo(theListFile)
	    	
			process(theListFile);	    	
			
			render(view:'manageLists', model:[theList:theList, lts:lts]) 
		} 		
		
	}
    
    private void process(File file) {

		String sampleData
		ListItem theListItem
		List<ListItem> listItems = new ArrayList<ListItem>()

		// Determine the type parameter
		ListType lt;
		if ( params["listTypes"] == "PatientDID")				
			lt = ListType.PatientDID
		else if (params["listTypes"] == "Gene")
			lt = ListType.Gene
		else
			lt = ListType.Reporter
				
		// Create the list items
		try {
			BufferedReader inStream = new BufferedReader(new FileReader(file))
		    while ((sampleData = inStream.readLine()) != null) {
				theListItem = new ListItem()
				theListItem.name = sampleData
				theListItem.listName = params["listName"]
				listItems.add(theListItem)				  					
		    }
		    inStream.close()
		} catch (IOException e) {
	    }
		    		
		// Convert list application-commons list type
		UserList ul = new UserList(params["listName"], lt, listItems, listItems)
		    
		// Save the list data to the session
        UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId())      
		userListBeanHelper.addList(ul)
		    
	}

     
    def export = {
		// Retrieve the selected list from session by name
		String listName = params["list"]
		String outFileName = listName + ".txt"
       	UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId())      
		UserList ul = userListBeanHelper.getUserList(listName)	
        ServletOutputStream op = response.getOutputStream()
        String mimetype = servletContext.getMimeType(outFileName)
        StringBuffer sb = new StringBuffer()
		
	    ul.listItems.each {
	    	sb.append(it.name);  
	        if ( it.rank != null && it.rank != "" ) {
	        	sb.append(" rank:" + it.rank);  
	        }      	
	        if ( it.notes != null && it.notes != "" ) {
	        	sb.append(" notes:" + it.notes);  
	        } 
	        sb.append("\r\n")
	    }
	    
        //  Set the response and go!
        response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
        response.setContentLength( sb.length() );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + outFileName + "\"" );

        //
        //  Stream to the requester.
        //
        try {
        	op.println(sb.toString())
        } catch (Exception e) {
        }

        op.flush();
        op.close();
			
    	ListType[] lts = ProjectListFilter.values();
		render(view:'manageLists', model:[theList:theList, lts:lts]) 
    }
    
}
