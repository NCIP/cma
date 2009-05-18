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
 
    	//should really control these programatically...
    	//?xml_dump=1&project=TCGA%20GBM
    	
    	//just a check for backwards compatability for old props file
    	xmlLink = xmlLink.replace("xml_dump", "json_dump");
       	System.out.println("******************** Connecting to: " + xmlLink)
    	def result = heatmapLinksService.fetchParseXml(xmlLink)
	    //render result //as JSON
	    render JSON.parse(result.toString()) as JSON
	}  

}
