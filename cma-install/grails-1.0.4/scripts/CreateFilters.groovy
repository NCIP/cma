/*
 * Copyright 2004-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Gant script that creates a Grails filters class
 * 
 * @author Jeff Brown
 *
 * @since 1.0.4
 */

import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    

includeTargets << grailsScript ( "Init" )
includeTargets << grailsScript ( "CreateIntegrationTest" )

target ('default': "Creates a new filters class") {
    depends(checkVersion)

	typeName = "Filters"
	artifactName = "Filters" 	
	artifactPath = "grails-app/conf"
	
	Ant.mkdir(dir:"${basedir}/grails-app/conf")
	
	createArtifact()
	createTestSuite() 
}