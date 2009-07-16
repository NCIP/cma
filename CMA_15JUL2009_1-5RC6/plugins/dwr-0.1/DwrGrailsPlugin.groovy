
import grails.dwr.*
import org.directwebremoting.spring.*

class DwrGrailsPlugin {
	def version = 0.1
	def dependsOn = [:]
	
	def author = "Alex Shneyderman"
	def authorEmail = "a.shneyderman@gmail.com"
	def title = "This plugin adds DWR capabilies to the services in a Grails application."
	def description = '''\
DWR plugin allows your Grails application to  expose its services thru the 
popular java library DWR. More about DWR can be found on its site at 
http://getahead.org/dwr   
'''
	def documentation = "http://www.grails.org/DWR+Plugin"
	
	def doWithSpring = {
		// just a dummy, has to be here.	
		"__dwrConfiguration"(SpringConfigurator) {
			creators       = [:] 
		}	
	}
	                 
	def doWithWebDescriptor = { webXml ->
		log.info "Configurring DWR v. 2.0.1"
	
		def grailsEnv = System.getProperty("grails.env")
		def servlets = webXml.'servlet'
	
		servlets[servlets.size()-1] + {
			'servlet' {
				 log.info 'DWR v. 2.0.1 > generating <servlet> for dwr-invoker'
				 
				'servlet-name'("dwr-invoker")
				'servlet-class'("org.directwebremoting.spring.DwrSpringServlet")
				
				if(grailsEnv == "development") {
					log.info 'DWR v. 2.0.1 > dwr-invoker debug is on'
					
					'init-param' {
						'param-name'("debug")
						'param-value'("true")  
					}
				}
				 
				'init-param' {
					'param-name'("customConfigurator")
					'param-value'("grails.dwr.GrailsFluentConfigurator")  
				}
				
				'init-param' {
					'param-name'("pollAndCometEnabled")
					'param-value'("true")  
				}
				
				'init-param' {
					'param-name'("allowGetForSafariButMakeForgeryEasier")
					'param-value'("true")  
				}
				
//				'init-param' {
//					'param-name'("allowImpossibleTests")
//					'param-value'("false")  
//				}
				
				'load-on-startup'("1")
			}			
		}
		
        def mappings = webXml.'servlet-mapping'
        mappings[mappings.size()-1] + {
			'servlet-mapping' {
				'servlet-name'("dwr-invoker")
				'url-pattern'("/dwr/*")
			}
		}
	}
	
}
