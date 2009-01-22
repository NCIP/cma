//dataSource {
//	pooled = false
//
//	username = "sa"
//	password = ""
//}

dataSource {
  pooled = true
  driverClassName = "oracle.jdbc.driver.OracleDriver"
  //UNCOMMENT FOR TCGA
  //username = "cmadev"
  //password = "cma1234"
  
  //UNCOMMENT FOR REMB
  //username="rembdev"
  //password = "dev!234"
  
  //UNCOMMENT FOR TARGET
  //username="tardev"
  //password = "go!tardev"
  
  dialect = "org.hibernate.dialect.Oracle9Dialect"
  logSql = false
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='org.hibernate.cache.EhCacheProvider'
}
// environment specific settings
   environments {
	development {
		dataSource {
			pooled = true
			dbCreate = "update" // one of 'create', 'create-drop','update'
			//url = "jdbc:hsqldb:mem:devDB"
             // url = "jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:TARDEV"
			//url = "jdbc:oracle:thin:@cbiodb540.nci.nih.gov:1550:INTM1DEV"
			url="jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:TARDEV"
		}
	}
	test {
		dataSource {
			pooled = true
			dbCreate = "update"
			//url = "jdbc:hsqldb:mem:testDb"
            //url = "jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:TARDEV"
			url = "jdbc:oracle:thin:@cbiodb540.nci.nih.gov:1550:INTM1DEV"
		}
	}
	production {
		dataSource {
			pooled = true
			dbCreate = "update"
                        //jndiName = "java:comp/env/pmrp"
            //jndiName = "java:cma"
            //jndiName = "java:rembrandt"
			//url = "jdbc:hsqldb:file:prodDb;shutdown=true"
                      //url = "jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:TARDEV"					
			  url="jdbc:oracle:thin:@cbiodb2-d.nci.nih.gov:1521:TARDEV"
	        }
       }
}
