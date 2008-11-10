class UserContextTagLib {

	def userContextService
	def isLoggedIn = { attrs, body ->
	    
	    if(userContextService.isLoggedIn())	{
	    	out << body()
	    }
	}
}
