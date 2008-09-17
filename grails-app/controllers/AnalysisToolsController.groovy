class AnalysisToolsController {

    def index = { redirect(action:menu)  }
    
    def menu =	{
    	render(view:'menu')
    }
}
