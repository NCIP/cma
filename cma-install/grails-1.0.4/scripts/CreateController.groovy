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
 * Gant script that creates a new Grails controller
 * 
 * @author Graeme Rocher
 *
 * @since 0.4
 */

import org.codehaus.groovy.grails.commons.GrailsClassUtils as GCU

Ant.property(environment:"env")                             
grailsHome = Ant.antProject.properties."env.GRAILS_HOME"    

includeTargets << grailsScript ( "Init" )  
includeTargets << grailsScript ( "CreateIntegrationTest" )

target ('default': "Creates a new controller") {
    depends(checkVersion)

	typeName = "Controller" 
	artifactName = "Controller" 	
	artifactPath = "grails-app/controllers"
	createArtifact()

    def viewsDir = "${basedir}/grails-app/views/${propertyName}"
    Ant.mkdir(dir:viewsDir)
	event("CreatedFile", [viewsDir])

	createTestSuite()
}