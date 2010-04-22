class ContentFixScriptTagLib {
	def fixScript = { attrs, body ->
	     def fix = body().replaceFirst("script", "<script"); 
	     
	      out << fix
	}
}
