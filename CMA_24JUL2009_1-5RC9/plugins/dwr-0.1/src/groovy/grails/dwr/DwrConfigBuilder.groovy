package grails.dwr

import grails.spring.BeanBuilder
import org.directwebremoting.spring.*
import org.directwebremoting.*
import org.springframework.web.servlet.handler.*
import groovy.inspect.*

class DwrConfigBuilder {

	def fluentConfigurator
	def application
	
	Object invokeMethod(String name, Object arg) {
		def handler = handlersMap[name]
		if (handler) {
			return handler.call(arg)
		}
	}

	private serviceHandler = { Object[] args ->
	    if (args[0] instanceof Map) {
	    	def argsMap = args[0]
	    	
			def key = argsMap.'name'
			if (!key) {
				throw new IllegalArgumentException("name is a required argument for the service element.")
			}
	    	
	    	println "  DWRPlugin: Configuring service '${key}'"
			
			def scriptName = argsMap.'javascript'
			if (!scriptName) {
				scriptName = key
			}
			
	    	println "  DWRPlugin: Setting script name for service '${key}' as '${scriptName}'"
	    	
	    	fluentConfigurator.withCreator("spring",scriptName)
			fluentConfigurator.addParam("beanName", key)
	    } else {
	    	throw new IllegalArgumentException("name is a required argument for the service element.")
	    }
	    
	    if (args.length > 1 && args[1] instanceof Closure) {
    		def innerClosure = (Closure) args[1]
	    	innerClosure.delegate = this
	    	innerClosure.call()
	    }
	}
	
	/*
	<!--
	Allow the creation of a class, and give it a name in javascript land.
	A reference to a creator is required as are some parameters specific to each
	creator that define the objects it allows creation of.
	It would be nice to make the creator and IDREF rather than a CDATA, since it
	refers to an element defined elsewhere, however we allow multiple dwr.xml
	files and we might refer to one in another file.
	-->
	<!ELEMENT create (
	    (param | include | exclude | auth | filter)*
	)>
	<!--
	@attr creator The id of the creator to use
	@attr javascript The name of the object to export to the browser
	@attr scope The scope of the created variable. The default is page.
	-->
	<!ATTLIST create
	    creator CDATA #REQUIRED
	    javascript CDATA #REQUIRED
	    scope (application | session | script | request | page) #IMPLIED
	>
	*/
	private creatorHandler = { Object[] args ->
		println "  DWRPlugin: Configuring creator ...."
		def creator = null
		def javascript = null
	    if (args[0] instanceof Map) {
			def argsMap = args[0]

			creator = argsMap.'creator'       // required
			javascript = argsMap.'javascript' // required
			def scope = argsMap.'scope'
			
			if (!creator) { throw new IllegalArgumentException("creator is a required parameter.") }
			if (!javascript) { throw new IllegalArgumentException("javascript is a required parameter.") }
			
	    	println "  DWRPlugin: creator named '${creator}'"
			
			fluentConfigurator.withCreator(creator,javascript)
			if (scope) {
				fluentConfigurator.addParam('scope', scope)
			}
		} else {
    		throw new IllegalArgumentException("Parameters are not specified.")
    	}

    	if (args.length > 1 && args[1] instanceof Closure) {
    		println "  DWRPlugin: Calling closure provided with creator '${creator}/${javascript}'"
	    	def innerClosure = (Closure) args[1]
	    	innerClosure.delegate = this
	    	innerClosure.call()
	    }
	}

	/*
	<!ELEMENT param (#PCDATA)>
	<!--
	@attr name The name of the parameter to this creator
	@attr value The value to set to the names parameter
	-->
	<!ATTLIST param
	    name CDATA #REQUIRED
	    value CDATA #IMPLIED
	>
    */
	private paramHandler = { Object[] args ->
	    def name = null
	    def value = null
    	if (args[0] instanceof Map) {
			def argsMap = args[0]
			name  = argsMap.'name'  // required
			value = argsMap.'value' // implied
    	} else {
    		throw new IllegalArgumentException("Parameters are not specified.")
    	}
    	
    	if (args.length > 1 && args[1] instanceof Closure) {
	    	def innerClosure = (Closure) args[1]
	    	value = innerClosure.call()
	    }

    	if (!name) { throw new IllegalArgumentException("name is a required parameter.") }
    	if (!value) { throw new IllegalArgumentException("value could not be determined.") }
    	fluentConfigurator.addParam(name, value)
	}
	
	/*
	<!--
	Allow conversion of a class between Java and Javascript.
	A convert element uses a previously defined converter and gives a class match
	pattern (which can end with *) to define the classes it allows conversion of
	It would be nice to make the converter and IDREF rather than a CDATA, since it
	refers to an element defined elsewhere, however we allow multiple dwr.xml
	files and we might refer to one in another file.
	-->
	<!ELEMENT convert (
	    (param)*
	)>
	<!--
	@attr converter The id of the converter to use
	@attr match A class name to match for conversion
	@attr javascript The optional classname for the parameter
	-->
	<!ATTLIST convert
	    converter CDATA #REQUIRED
	    match CDATA #REQUIRED
	    javascript CDATA #IMPLIED
	>
	*/
	private convertHandler = { Object[] args ->
		def converter = null
    	def match = null
    	
    	println "  DWRPlugin: Configuring converter ...."
		if (args[0] instanceof Map) {
			def argsMap = args[0]
			converter  = argsMap.'converter'
			match = argsMap.'match' 
			def javascript  = argsMap.'javascript'
			
			if (!converter) { throw new IllegalArgumentException("converter is a required parameter.") }
			if (!match) { throw new IllegalArgumentException("match is a required parameter.") }
			
			println "  DWRPlugin: converter named '${converter}' matching '${match}'"	
			
			fluentConfigurator.withConverter(converter, match)
			if (javascript) {
				fluentConfigurator.addParam('javascript',javascript)
			}
    	} else {
    		throw new IllegalArgumentException("Parameters are not specified.")
    	}
		
		if (args.length > 1 && args[1] instanceof Closure) {
    		println "  DWRPlugin: Calling closure provided with convert '${converter}/${match}'"
	    	def innerClosure = (Closure) args[1]
	    	innerClosure.delegate = this
	    	innerClosure.call()
	    }	
	
	}
	
	/*
	<!--
	A creator can allow and disallow access to the methods of the class that it
	contains. A Creator should specify EITHER a list of include elements (which
	implies that the default policy is denial) OR a list of exclude elements
	(which implies that the default policy is to allow access)
	-->
	<!ELEMENT include EMPTY>
	<!--
	@attr method The method to include
	-->
	<!ATTLIST include
	    method CDATA #IMPLIED
	>
	*/
	def includeHandler = { Object [] args ->
		if (args.length != 1) {
			throw new IllegalArgumentException("Include element takes excatly one parameter.")
		}
		
		def methods = null
		
		if (args[0] instanceof String) {
			methods = args[0].tokenize(',')
		} else if (args[0] instanceof List) {
			methods = args [0]
		}

		if (methods == null) {
			throw new IllegalArgumentException("Argument to include could be a List or a String (comma separated names - optionally), the argument provided is neither.")
		}
		
		println "  DWRPlugin: including methods ${methods}"
		
		methods.each { method ->
			fluentConfigurator.include(method)
		}
	}
	
	/*
	<!--
	See the include element
	-->
	<!ELEMENT exclude EMPTY>
	<!--
	@attr method The method to exclude
	-->
	<!ATTLIST exclude
	    method CDATA #IMPLIED
	>
	*/
	def excludeHandler = { Object [] args ->
		if (args.length != 1) {
			throw new IllegalArgumentException("Exclude element takes excatly one parameter.")
		}
	
		def methods = null
	
		if (args[0] instanceof String) {
			methods = args[0].tokenize(',')
		} else if (args[0] instanceof List) {
			methods = args [0]
		}

		if (methods == null) {
			throw new IllegalArgumentException("Argument to exclude could be a List or a String (comma separated names - optionally), the argument provided is neither.")
		}
	
		println "  DWRPlugin: excluding methods ${methods}"
		methods.each { method ->
			fluentConfigurator.exclude(method)
		}
	}
	
	/*
	<!--
	The auth element allows you to specify that the user of a given method must be
	authenticated using J2EE security and authorized under a certain role.
	-->
	<!ELEMENT auth EMPTY>
	<!--
	@attr method The method to add role requirements to
	@attr role The role required to execute the given method
	-->
	<!ATTLIST auth
	    method CDATA #REQUIRED
	    role CDATA #REQUIRED
	>
	*/
	def authHandler = { Object [] args ->
		if (args.length != 1) {
			throw new IllegalArgumentException("Auth element takes excatly one parameter a map containing two keys methods and role")
		}
		
		if (args[0] instanceof Map) {
			def argsMap = args[0]
			def methods = null
			if (argsMap.'methods') {
				methods = argsMap.'methods'.tokenize(',')
			} 
			
			def role = argsMap.'role'
			if (!methods) {
				throw new IllegalArgumentException("First argument to auth could be a List of methods(strings) or a String (comma separated method names - optionally), the argument provided is neither.")
			}
			
			if (!role) {
				throw new IllegalArgumentException("Second argument to auth has to String - the role name - argument provided is not.")
			}
			
			methods.each { method ->
				fluentConfigurator.withAuth(method,role)
			}
    	} else {
    		throw new IllegalArgumentException("Parameters are not specified.")
    	}
		
	}
	
	/*
	<!--
	If we are marshalling to collections, we need to be able to specify extra
	type information to converters that are unable to tell from reflection what to
	do. This element contains some Java method definitions
	-->
	<!ELEMENT signatures (#PCDATA)>
	*/
	def signaturesHandler = { Object [] args ->
		if (args.length != 1) {
			throw new IllegalArgumentException("signatures element takes excatly one parameter a multi/single line string")
		}
	
		if (args[0] instanceof String) {
			fluentConfigurator.withSignature()
			args[0].tokenize("\n").each { line ->
				if (line) {
					fluentConfigurator.addLine(line)
				}
			}
		} else {
    		throw new IllegalArgumentException("Parameters are not of the right type.")
    	}
	}
	
	private handlersMap = ['service'  : serviceHandler,
	                       'create'   : creatorHandler,
	                       'param'    : paramHandler,
	                       'convert'  : convertHandler,
	                       'include'  : includeHandler,
	                       'exclude'  : excludeHandler,
	                       'auth'     : authHandler,
	                       'signatures': signaturesHandler]
                  
}