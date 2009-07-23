package grails.dwr

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.directwebremoting.Container
import org.directwebremoting.fluent.FluentConfigurator

class GrailsFluentConfigurator extends FluentConfigurator {

	def application
	def configBuilder = null
	
	void configure() {
		def foundBootstrap = false
		application.bootstrapClasses.each { bootstrap ->
		
		    println "  DWRPlugin: examining bootstrap ${bootstrap.fullName}"
		
			def instance = bootstrap.newInstance()
			try {
				def dwrConfigClosure = instance.dwrconfig
				
				if (configBuilder == null) {
					configBuilder = new DwrConfigBuilder(fluentConfigurator:this,application:application)
				}
				
				dwrConfigClosure.delegate = configBuilder
				dwrConfigClosure.call()
				foundBootstrap = true
			} catch (Throwable exc) {
				exc.printStackTrace()
			}
		}
		
		if(!foundBootstrap) {
			println "  DWRPlugin: no DWR config closures found in any of the bootstrap classes."
		}
	}

	void configure(Container container) {
		application = (GrailsApplication) container.getBean(GrailsApplication.APPLICATION_ID);
		super.configure(container)
	}
	
}