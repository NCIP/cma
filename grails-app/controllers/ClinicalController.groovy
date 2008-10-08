class ClinicalController {
	
	def defaultListLoaderService
	def beforeInterceptor = {
		if(!defaultListLoaderService.areListsLoaded())	{
			defaultListLoaderService.loadDefaultLists()
		}
	}
	
    def index = { 
		def patLists = defaultListLoaderService.getPatientLists(session.id, false);
    	render(view:'main', model:[patLists:patLists])	
    }
}
