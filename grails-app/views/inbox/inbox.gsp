<content tag="tabs"><g:render template="/tabs" model="[location:'']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<script type='text/javascript' src='../dwrspring/interface/Inbox.js'></script>
  		<script type='text/javascript' src='../dwrspring/engine.js'></script>
  		<script type='text/javascript' src='../dwrspring/util.js'></script>
  		<g:javascript library="overlib_mini" />
  		<script type="text/javascript">
			function spawn(url, winw, winh) {
			    var w = window.open(url, "_blank", "screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=no,width=" + winw + ",height=" + winh + ",scrollbars=yes,resizable=yes");
			}
		</script>
	</head>
	<body>        
    <fieldset>
     	<legend>Report Results</legend>
     	<br/>
       	<div id="loadingMsg" style="color:red;font-weight:bold;">&nbsp;</div>
       	<ul>
     			
			<g:if test="${tasks.size() > 0}">
				<g:javascript library="a_functions" />	
				<script type="text/javascript">	
					var customError = function(message)	{};
					DWREngine.setWarningHandler(customError);
					DWREngine.setErrorHandler(customError);
					setTimeout("A_checkAllFindingStatus('${sessionId}')", 0200);
					var vr_checker = setInterval("A_checkAllFindingStatus('${sessionId}')", 5000);
				</script>
				
				
				<g:each in="${tasks}" var="task">		
				
					<g:set var="qname" value="${task.getId()}"/>
					<g:set var="cleanQName" value="${task.getId()}"/>
					<g:set var="comments" value=""/>
					
					<g:set var="currentStatus" value="running"/>
					<g:set var="onclick" value=""/>
					
					<span style='color:red; float:right'>
					<g:if test="${task.getStatus() == gov.nih.nci.caintegrator.enumeration.FindingStatus.Completed}">					
						<b id="${qname}_status">completed</b>  
						<img src='images/check.png' alt='complete' id="${qname}_image" />
					</g:if>
					<g:elseif test="${task.getStatus() == gov.nih.nci.caintegrator.enumeration.FindingStatus.Running}">
						<g:set var="onclick" value="javascript:alert('Analysis Not yet complete');return false;"/>
						<b id="${qname}_status" >running</b> 
						<img src='images/circle.gif' alt='running' id="${qname}_image" />
					</g:elseif>
					<g:elseif test="${task.getStatus() == gov.nih.nci.caintegrator.enumeration.FindingStatus.Error}">
						<b id="${qname}_status" >
						<script language="javascript">
							document.write(showErrorHelp('${task.getStatus().getComment()}','error'));
						</script>
						</b> 
						<img src='images/error.png' alt='error' id="${qname}_image" />
					</g:elseif>
					</span>
					
					<g:if test="${task.getQueryDTO() instanceof gov.nih.nci.cma.query.dto.ProjectClinicalQueryDTO}">					
						<li><a id="${qname}_link" 
						href="javascript:spawnx('clinicalReport.do?method=runReport&taskId=' + encodeURIComponent('${qname}') + '&cacheId=${task.getCacheId()}', 750, 500,'clinical_report');\" 
						onclick="${onclick}">${qname}</a> <i>(Clinical)</i>
					</g:if>
					<g:elseif test="${task.getQueryDTO() instanceof gov.nih.nci.cma.query.dto.ProjectPCAQueryDTO}">	
						<g:if test="${task.getStatus() == gov.nih.nci.caintegrator.enumeration.FindingStatus.Error}">
							<li> <span id="${cleanQName}_name" >${cleanQName} (PCA)</span> 
						</g:if>
						<g:else>
							<li><span id="${cleanQName}_name" ></span>
							<a id="${cleanQName}_link" 
							href="javascript:spawn('${createLink(controller:'analysisTools', action: 'pcaPlot')}?taskId=' + encodeURIComponent('${qname}') + '&cacheId=${task.getCacheId()}', 750, 700, 'pca_report');" 
							onclick="${onclick}">${cleanQName}</a> <i>(PCA)</i> 
						</g:else>
					</g:elseif>
					
					<span style="font-size:10px">(elapsed time: <span id="${cleanQName}_time" >${task.getElapsedTime()}</span>ms) </span> 
					</li>
					<br clear="all" />
					<br clear="all" />
				</g:each>
			</g:if>
			<g:else>
				<strong>No Report Results at this time.</strong><br/><br/>
			</g:else>
	     </ul>
	     <br/><br/>
	     <div style="font-size:9px;text-align:center;">
	     (PCA) Principal Component Analysis
	     </div>
	</fieldset>
	<br /><br />
	</body>
</html>
     