import grails.converters.*


class HeatmapLinksService {

    boolean transactional = false

    def serviceMethod() {

    }
    
    def fetchParseXml(String base) {
   //base should contain &project=TCGA%20GBM for example
   //parse this as json - dont copy over each prop, just take the whole feed
   
//    	def heatmap_datasets = new XmlSlurper().parse(base)
    	

        def url = new URL(base)
        def connection = url.openConnection()

        def resultsList = []
  
        return connection.content.text

   /*
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
        }
        else{
          println("failed");
        }      
*/
      }

}
