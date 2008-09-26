import grails.converters.*

class GenomeViewController {

    def index = {render(view:'main') }
    
    def heatmapLinksService
    
    def links = {
	    def result = heatmapLinksService.fetchParseXml()
	    render result as JSON
	}  

}
