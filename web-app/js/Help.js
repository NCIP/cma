var Help = {
//	url : "helpDocs/cmaPortal/index.html?single=false&context=cmaPortal&topic=",
//	url : "http://cma.nci.nih.gov/cma-help/cmaPortal/index.html?single=false&context=cmaPortal&topic=",
	url : "${grailsApplication.config.gov.nih.nci.cma.helpUrl}/cmaPortal/index.html?single=false&context=cmaPortal&topic=",
//	guideUrl : "http://cma.nci.nih.gov/cma-help/",
	guideUrl : "${grailsApplication.config.gov.nih.nci.cma.helpUrl}",
	popGuide: function()	{
		window.open(Help.guideUrl+"/cmap_user_guide.pdf", "Help", "status,scrollbars,resizable,width=800,height=500");
	},
	popHelp: function(topic) {
		window.open (Help.url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//use the below if you want the "always on top" feature, most dont like it
		//window.open (Help.url+topic, "Help", "alwaysRaised,dependent,status,scrollbars,resizable,width=800,height=500");  
	},
	insertHelp: function(opts)	{
		var ex = opts.ex ? opts.ex : "";
		var exst = opts.exst ? opts.exst : "";
		var pth = opts.pth ? opts.pth : "images";
		var topic = opts.topic ? opts.topic.replace('helptag_','') : "welcome";
		var htm = "<img "+ ex + " style=\"cursor:pointer;border:0px;"+ exst + "\" src=\""+pth+"/help.gif\" alt=\"help\" id=\"helpIcon\" name=\"helpIcon\" onclick=\"Help.popHelp(\'"+topic+"\');\" />";
		return htm;
	},
	parsePage: function(opts)	{
		$$('div.help').each( function(e)	{
			e.update(Help.insertHelp({pth: opts.pth,topic:e.id,exst:'float:right;'}));
		});
	},
	popHelpMain: function(topic) {
		var _url = "helpDocs/cmaPortal/Welcome.1.1.html";
		window.open (_url+topic, "Help", "status,scrollbars,resizable,width=800,height=500");  
		//use the below if you want the "always on top" feature, most dont like it		
		//window.open (_url+topic, "Help", "alwaysRaised,dependent,status,scrollbars,resizable,width=800,height=500");  
	}
	
};