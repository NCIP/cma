class UserContextTagLib {

	def userContextService
	def isLoggedIn = { attrs, body ->
	    
	    if(userContextService.isLoggedIn())	{
	    	out << body()
	    }
	}
	
	def contextAware = { attrs, body ->
		//params show and hide take a string, TODO: make it a list
		def ds = grailsApplication.config.cma.dataContext ?: ""
		def mode = attrs["mode"] ?: "showOnlyTo"
		def context = attrs["context"] ?: "Rembrandt"
		//TODO: support more modes, but this is all we need now
		
		
		//show this content if the context equals the one specified
		if(mode == "showOnlyTo")	{
			if(ds == context)	{
				out << body()
			}
		}
		else if(mode == "hideFromOnly")	{
			//show this content to all other contexts but hide it from the one specified
			if(ds != context)	{
				out << body()
			}
		}
		
	}
	
}
