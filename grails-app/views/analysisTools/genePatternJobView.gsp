<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
			
 		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/interface/GenePatternHelper.js'> </script>
		
		<g:javascript src="a_genePattern.js" /> 	
		 
	</head>
	
	<body>

<script type="text/javascript">Help.insertHelp("genepattern_job_result", " align='right'", "padding:2px;");</script>
<br/>       
<div id="helptag_gene_pattern_analysis" class="help"></div>
     <fieldset>
     	<legend>Gene Pattern Job Result</legend>
     	<br/>
       	<div id="loadingMsg" style="color:red;font-weight:bold;">&nbsp;</div>
       		<table border="0" cellpadding="3" cellspacing="3">
       			<tr>
       				<td> Your request has been sent to GenePattern for processing, and  
       				your job id is :  <span style="color:red;font-weight:bold">${jobId}</span>.
       				When your task is complete, your data will be ready 
       				for analysis in GenePattern.  Your available tasks will appear in the right 
       				sidebar of the GenePattern when they are ready.  The approximate 
       				processing time is 2-3 minutes.<br><br>
       				
				<!-- to check if the gene pattern job is completed -->
				<% String jobId = (String)request.getAttribute("jobId"); 
				   //System.out.println("genePatternJobView.gsp - jobId: " + jobId);
				%>
				
				<%-- --%>
				<script type="text/javascript">	
					//testMap("testingtesting");
					var customError = function(message)	{};
					DWREngine.setWarningHandler(customError);
					DWREngine.setErrorHandler(customError);
					
					setTimeout("A_checkGenePatternStatus('<%= jobId %>')", 0200);
					var vr_checker = setInterval("A_checkGenePatternStatus('<%= jobId %>')", 5000);	
				</script>
				<%-- --%>
				
				<%
					//Check completion status
					String currentStatus = (String)request.getAttribute("gpStatus");
					//System.out.println("genePatternJobView.gsp - gpStatus:  " + currentStatus);
					String gpUrl = (String)request.getAttribute("genePatternURL");
					//System.out.println("genePatternJobView.gsp - genePatternURL: " + gpUrl);
					
					if (currentStatus.equals("completed"))
						currentStatus = "<b id=\"" + jobId + "_status\">completed</b> <img src=\"${createLinkTo(dir:'images',file:'check.png')}\" alt='complete' id=\"" + jobId + "_image\"/>";
					else if (currentStatus.equals("running"))
						currentStatus = "<b id=\"" + jobId + "_status\" >running</b> <img src=\"${createLinkTo(dir:'images',file:'circle.gif')}\" alt='running' id=\"" + jobId + "_image\" />";
					else if (currentStatus.equals("error"))  {
						String comments = "An error occured during sending your job request to GenePattern or during GenePattern processing";
						currentStatus = "<b id=\"" + jobId + "_status\" ><script language=\"javascript\">document.write(showErrorHelp('"+comments+"','error'));</script></b> <img src=\"${createLinkTo(dir:'images',file:'error.png')}\" alt='error' id=\"" + jobId + "_image\" />";
					}
					
					out.println("<span style='color:red; float:right'>" + currentStatus + "</span> ");
					
					String onclick="";	
					if (!currentStatus.equals("completed"))	{
						onclick = "javascript:alert('Gene Pattern Processing Not yet complete');return false;";
					}
					out.println("<a id=\"" + jobId + "_link\" href=\"" + gpUrl + "\" onclick=\"" + onclick + "\" target=\"new\">GenePattern Job " + jobId +"</a>");
					
					out.println("<br clear=\"all\" />");
					out.println("<br clear=\"all\" />");
				%>
       				Please click the above link to launch GenePattern.  If your task does not appear in 
       				the sidebar, please wait a minute and refresh the GenePattern page to try again.
     			</tr>
     		</table>
		</fieldset>
<br /><br />

	</body>
</html>

     