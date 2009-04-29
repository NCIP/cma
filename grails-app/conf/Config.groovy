// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

//The CURRENT Context - MUST match a key from cma.dataContexts map below -> TARGET | Rembrandt | TCGA | TCGAOvarian
//EDIT THIS line to configure your builds context
cma.dataContext="TCGA"
cma.deployedTo = "local"; //local, dev, ect  (really only cares about 'local')

//All Available Contexts
cma.dataContexts =  [
	'Rembrandt' : [
		displayName:'Rembrandt',
		authenticationManagerContext:'rembrandt',
		propertiesFile:'cma-rembrandt.properties'
	],
	'TCGA' : [
		displayName:'TCGA GBM',
		authenticationManagerContext:'cma',
		propertiesFile:'cma-tcga.properties'
	],
	'TCGAOvarian' : [
		displayName:'TCGA Ovarian',
		authenticationManagerContext:'cma',
		propertiesFile:'cma-tcga.properties'
	],
	'TARGET' : [
		displayName:'Target',
		authenticationManagerContext:'target',
		propertiesFile:'cma-target.properties'
	]
]


//Probably wont ever need to edit below this line ----------------------
propertiesFilePath = cma.deployedTo=='local' ? "C:\\local\\content\\cma\\config\\"  : "/local/content/cma/config/";
cma.appPropertiesFile = propertiesFilePath + cma.dataContexts[cma.dataContext].propertiesFile
//cma.appPropertiesFile= propertiesFileUrl.propertiesFilesPath + cma.dataContexts[cma.dataContext].propertiesFile
cma.authenticationManagerContext=cma.dataContexts[cma.dataContext].authenticationManagerContext

// CONTEXTS:  // 1 | 2 | 3
//cma.authenticationManagerContext="cma"  //target  | rembrandt | cma  | tcgaovarian?
//cma.availableContexts = ["Rembrandt", "TCGA_GBM", "TCGA_Ovarian", "TARGET"]  //add/remove for links
//FOR LOCAL DEV:
//cma.appPropertiesFile="C:\\local\\content\\cma\\config\\cma-tcga.properties"  //cma-target | cma-rembrandt | cma-tcga 
//FOR SERVER DEPLOYMENT:
//cma.appPropertiesFile="/local/content/cma/config/cma-tcga.properties" //cma-target | cma-rembrandt | cma-tcga 

grails.config.locations = ["file:${cma.appPropertiesFile}"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text-plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
grails.converters.encoding="UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true



// log4j configuration
log4j {
    appender.stdout = "org.apache.log4j.ConsoleAppender"
    appender.'stdout.layout'="org.apache.log4j.PatternLayout"
    appender.'stdout.layout.ConversionPattern'='[%r] %c{2} %m%n'
    appender.errors = "org.apache.log4j.FileAppender"
    appender.'errors.layout'="org.apache.log4j.PatternLayout"
    appender.'errors.layout.ConversionPattern'='[%r] %c{2} %m%n'
    appender.'errors.File'="stacktrace.log"
    rootLogger="error,stdout"
    logger {
        grails="error"
        StackTrace="error,errors"
        org {
            codehaus.groovy.grails.web.servlet="error"  //  controllers
            codehaus.groovy.grails.web.pages="error" //  GSP
            codehaus.groovy.grails.web.sitemesh="error" //  layouts
            codehaus.groovy.grails."web.mapping.filter"="error" // URL mapping
            codehaus.groovy.grails."web.mapping"="error" // URL mapping
            codehaus.groovy.grails.commons="info" // core / classloading
            codehaus.groovy.grails.plugins="error" // plugins
            codehaus.groovy.grails.orm.hibernate="error" // hibernate integration
            springframework="off"
            hibernate="off"
        }
        gov {
        	nih.nci.caintegrator="debug"   
        	nih.nci.cma="debug"
        }
      
    }
    additivity.StackTrace=true
}


