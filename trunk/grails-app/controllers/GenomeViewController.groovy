import grails.converters.*

class GenomeViewController {

    def index = {render(view:'main') }
    
    def heatmapLinksService
 
    //TODO:  externalize this property once dev is complete
//  def xmlLink = "http://lpgws.nci.nih.gov/perl/heatmap?xml_dump=1" //original link, all projects
//	def xmlLink = "http://lpgws.nci.nih.gov/perl/heatmap?xml_dump=1&project=TCGA%20GBM" //TCGA only
//  def xmlLink = "https://cgwb.nci.nih.gov/cgi-bin/heatmap?xml_dump=1&project=Rembrandt%20GBM" //RBT only, SSL
//  def xmlLink = "http://lpgws.nci.nih.gov/perl/heatmap?xml_dump=1&project=Rembrandt%20GBM" //RBT only
		
	//def xmlLink = System.properties["gov.nih.nci.cma.links.heatmap_url"]
    
    
    def links = {
    	def xmlLink = "${grailsApplication.config.gov.nih.nci.cma.links.heatmap_url}"	
	    def result = heatmapLinksService.fetchParseXml(xmlLink)
	    render result as JSON
	}  

}
