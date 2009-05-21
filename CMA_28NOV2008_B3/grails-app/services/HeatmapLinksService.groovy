import grails.converters.*


class HeatmapLinksService {

    boolean transactional = false

    def serviceMethod() {

    }
    
    def fetchParseXml(String base) {
//        def base = "http://lpgws.nci.nih.gov/perl/heatmap?xml_dump=1"
        def url = new URL(base)
        def connection = url.openConnection()

        def resultsList = []
       
        if(connection.responseCode == 200){
          def xml = connection.content.text
          def heatmap_datasets = new XmlSlurper().parseText(xml)
          def datasets = heatmap_datasets.dataset;
         datasets.each(
		 {
			 def result = [:]
			 result.dataset_id = it.dataset_id as String 
			 result.dataset_type = it.dataset_type as String
			 result.title = it.title as String
			 result.launch_url = it.launch_url as String
			 //may want to perform a context check here and search for only those w/ proper projects
			 result.project = it.project as String
			 resultsList << result
		 }
        )
          return resultsList
          
//          result.name = geonames.geoname.name as String 
//          result.lat = geonames.geoname.lat as String
//          result.lng = geonames.geoname.lng as String
//          result.state = geonames.geoname.adminCode1 as String
//          result.country = geonames.geoname.countryCode as String
        }
        else{
          println("failed");
        }      
//        return result
      }

}