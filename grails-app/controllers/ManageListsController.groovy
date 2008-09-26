import gov.nih.nci.caintegrator.application.lists.ListType
import gov.nih.nci.cma.list.ProjectListFilter

class ManageListsController {

    def index = { 
    		ListType[] lts = ProjectListFilter.values();
    		render(view:'manageLists', model:[lts:lts]) 
    		
    }
}
