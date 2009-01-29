var Help = {
	url : "helpDocs/TCGA_Analysis_Online_Help/index.html?single=false&context=TCGA_Analysis_Online_Help&topic=",
	popHelp: function(topic) {
		//alert("coming soon");
		window.open("helpDocs/cmap_user_guide.pdf", "Help", "status,scrollbars,resizable,width=800,height=500");
		
		//window.open (Help.url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//use the below if you want the "always on top" feature, most dont like it
		//window.open (Help.url+topic, "Help", "alwaysRaised,dependent,status,scrollbars,resizable,width=800,height=500");  
		//alert(Help.url+topic);
	},
	insertHelp: function(topic)	{
		var ex = arguments[1] ? arguments[1] : "";
		var exst = arguments[2] ? arguments[2] : "";
		var pth = arguments[3] ? arguments[3] : "";
		var htm = "<img "+ ex + " style=\"cursor:pointer;border:0px;"+ exst + "\" src=\""+pth+"images/help.png\" alt=\"help\" id=\"helpIcon\" name=\"helpIcon\" onclick=\"Help.popHelp(\'"+topic+"\');\" />";
		document.write(htm);
	},
	popHelpMain: function(topic) {
		var _url = "helpDocs/TCGA_Analysis_Online_Help/Welcome.1.1.html";
		//alert("coming soon");
		window.open("helpDocs/cmap_user_guide.pdf", "Help", "status,scrollbars,resizable,width=800,height=500");
		
		//window.open (_url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//use the below if you want the "always on top" feature, most dont like it		
		//window.open (_url+topic, "Help", "alwaysRaised,dependent,status,scrollbars,resizable,width=800,height=500");  
		//alert(Help.url+topic);
	},
	convertHelp: function(topic)	{
		var ex = arguments[1] ? arguments[1] : "";
		var exst = arguments[2] ? arguments[2] : "";
		var ta = "";
		switch(topic)	{
			case "clinicalquery":
				ta = "Clinical_query_analysis";
				break;
			case "correlationscatter":
				ta = "Correlation_scatter_analysis";
				break;				
			case "categoricalcorrelation":
				ta = "Explanatory_data_analysis";
				break;
			case "ihclevelquery":
				ta = "IHC_LOE_query_analysis";
				break;
			case "ihclossquery":
				ta = "IHC_Loss_query_analysis";
				break;
			case "classcomparison":
				ta = "Class_comparison";
				break;
			case "principalcomponent":
				ta = "PCA_analysis";
				break;
			case "hierarchicalclustering":
				ta = "HCA_analysis";
				break;			
			default:
				break;
		
		}
		Help.insertHelp(ta, ex, exst);
	
	}
}