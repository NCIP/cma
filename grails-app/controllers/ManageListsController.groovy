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
	    
	    if ( errors ) {
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
		
    	// Bind the appropriate params to the CMA List domain class so that
    	// grails validation is possible
		theList = new gov.nih.nci.cma.domain.CmaList()				
		theList.name = params["listName"]
		theList.type = params["listTypes"]
		theList.description = params["listDesc"]
		theList.author = session.userId
		theList.origin = "Default"
		theList.creationDate = Calendar.getInstance().getTime()
		
		if ( theList.validate() ) {
			// Determine the type parameter
			ListType lt;
			if ( theList.type == "PatientDID" )				
				lt = ListType.PatientDID
			else if ( theList.type == "Gene" )
				lt = ListType.Gene
			else
				lt = ListType.Reporter
				
			// Create the list items
		    try {
			    BufferedReader inStream = new BufferedReader(new FileReader(file))
			    while ((sampleData = inStream.readLine()) != null) {
					theListItem = new ListItem()
					theListItem.name = sampleData
					theListItem.listName = theList.name
					listItems.add(theListItem)				  					
			    }
			    inStream.close()
			} catch (IOException e) {
		    }
		    		
			// Convert list application-commons list type
			UserList ul = new UserList(theList.name, lt, listItems, listItems)
		    
		    // Save the list data to the session
            UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId())      
		    userListBeanHelper.addList(ul)		
	    }
	}

     
    def export = {

		// Retrieve the selected list from session by name
	    File outFile 
		String listName = params["list"]
       	UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId())      
		UserList ul = userListBeanHelper.getUserList(listName)	
		
	    try {
	    	outFile = new File(listName + ".txt")
	        BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
	        
	        ul.listItems.each {
	        	out.write(it.name);  
	        	if ( it.rank != null || it.rank != "" ) {
	        		out.write(" rank:" + it.rank);  
	        	}      	
	        	if ( it.notes != null || it.notes != "" ) {
	        		out.write(" notes:" + it.notes);  
	        	} 
	        	out.newLine()     	
	        }
	        
	        out.close();
	    } catch (IOException e) {
	    }
	    
        ServletOutputStream op = response.getOutputStream()
        String mimetype = servletContext.getMimeType(outFile.getName())
	    int length = 0
	    
        //  Set the response and go!
        response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
        response.setContentLength( (int)outFile.length() );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + outFile.getName() + "\"" );

        //
        //  Stream to the requester.
        //
        byte[] bbuf = new byte[1024]
        DataInputStream inStream = new DataInputStream(new FileInputStream(outFile))

        while ((inStream != null) && ((length = inStream.read(bbuf)) != -1))
        {
            op.write(bbuf,0,length);
        }

        inStream.close();
        op.flush();
        op.close();
			
    	ListType[] lts = ProjectListFilter.values();
		render(view:'manageLists', model:[theList:theList, lts:lts]) 
    }
    
}
