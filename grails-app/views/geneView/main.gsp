<content tag="tabs"><g:render template="/tabs" model="[location:'gene']"/></content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				try	{
					new Effect.Corner($('geneSearchHeader'), 'top');
					new Effect.Corner($('pathwaySearchHeader'), 'top');
				} catch(e){ }
			});
		
			var showGeneInfo = function()	{
				var gurl = "http://cgap.nci.nih.gov/Genes/RunUniGeneQuery?PAGE=1&SYM=&PATH=&ORG=Hs&TERM=";
				if($F("geneSymbol")!="")
					window.open(gurl + ($F("geneSymbol")));
				return false;
			}
			
			//handles the clicking of a radio	
			function onRadio(formElement, i){
				var element = formElement.name;
		
			  	if(i==0)	{
			  		//document.getElementById("sampleGroupSelect").innerHTML = multiple;
			  		$('sampleGroupNameId').multiple = "multiple";
			  		$('sampleGroupNameId').size = 5;
			  		resetFields(false, 0);
			  	}
			  	else if (i == 1 || i == 2 ){
			  		//document.getElementById("sampleGroupSelect").innerHTML = single;
			  		$('sampleGroupNameId').multiple = false;
			  		$('sampleGroupNameId').size = 1;
					$('sampleGroupNameId').selectedIndex = 0;
			  		resetFields(false, 2);
			  	}
			  	else if (i == 3){
			  		//document.getElementById("sampleGroupSelect").innerHTML = single;
			  		$('sampleGroupNameId').multiple = false;
			  		$('sampleGroupNameId').size = 1;
			  		$('sampleGroupNameId').selectedIndex = 0;
			  		resetFields(true, 3);
			  	}
			  	else if (i == 4){
			  		resetFields(true, 4);
			  	}
			}
			
			function resetFields(ok, i){
				if ( i == 4){
		  			document.getElementById("geneSymbol").disabled = true;
		  		}
		  		else {
		  			document.getElementById("geneSymbol").disabled = false;
		  		}
		  		document.getElementById("sampleGroupNameId").disabled = ok;
		  		document.getElementById("geArrayPlatformId").disabled = ok;
			}

			function popPathway(pathwayName)	{
				var link = "" + pathwayName; //need to get this
				alert("need to read link from props");
				//window.open( link, 'page2', 'status,resizable,scrollbars,width=965px,height=800px,screenX=100,screenY=100');
				return false;
			}
			
		</script>
	</head>
	<body>
		<h2>Gene View</h2>
		
		<form method="post" action="${createLink(action:'geneBasedView')}" id="geneViewForm">
		
		<fieldset>
			<g:if test="${flash.message}">
	        	<div class="message">${flash.message}</div><br/>
	        </g:if>
            
		 <div id="geneSearchHeader" style="padding:3px;background-color:#2B2D51; color:#fff;">
			<h5>
				Gene based views&nbsp;&nbsp;&nbsp;&nbsp;
			</h5>
		</div>
		<div id="mainc" style="border:1px solid #E0E0E0; padding:5px;">
			View Type:<br/>
			<input type="radio" name="plot" value="geneExpPlot" checked="checked" onclick="javascript:onRadio(this,0);" class="radio">
	        Gene Expression plot&nbsp;<br />
			<input type="radio" name="plot" value="GE_KM_PLOT" onclick="javascript:onRadio(this,1);" class="radio">
	        Kaplan-Meier survival plot for Gene Expression Data&nbsp;<br />
			<input type="radio" name="plot" value="genomeWorkbench" onclick="javascript:onRadio(this,3);" class="radio">
	        View mutations and copy number changes&nbsp;<br/>
			<br/>
			
			Gene Symbol (HUGO):
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="text" name="geneSymbol" size="20" value="" id="geneSymbol" style="vertical-align:middle">&nbsp;
			<a href="#" onclick="showGeneInfo(); return false;" id="geneInfoLink">[show gene info]</a>

			<div style="display: table;">
				<table>
					<tr>
						<td style="vertical-align: middle">
							Restrict to sample group:
						</td>
						<td>
							<div id="sampleGroupSelect" style="vertical-align: middle; display: table-cell;">
								<g:select name="sampleGroupNameMultiple" multiple="multiple" size="5" id="sampleGroupNameId" 
								style="width: 200px; overflow: none;" 
								from="${patLists}"></g:select>
							</div>

						</td>
					</tr>
				</table>
			</div>
			<br />
			
			Select Array Platform: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!-- sets the hidden field to the platformName - do not change -->
			<g:select onchange="\$('platformName').value = this.options[this.selectedIndex].text;" name="geArrayPlatform" id="geArrayPlatformId" noSelection="['': 'choose platform']" from="${gov.nih.nci.cma.domain.Platform.list()}" optionValue="platformName" optionKey="fileName"></g:select>
			<input type="hidden" id="platformName" name="platformName" value=""/>
			
			<div style="text-align:center; padding-top:10px;">
	        	<input type="reset" value="clear" id="clearButton" class="">&nbsp;&nbsp;
		        <input type="submit" name="method" value="Go" id="submittalButton" class="subButton">
	        </div>
		<!-- close mainc -->
		</div>
		
		<br/><br/>
		<div id="pathwaySearchHeader" style="padding:5px;background-color:#2B2D51; color:#fff;">
			<h5>Pathway Visualization</h5>
		</div>
		<div style="border:1px solid #E0E0E0; padding:5px;">
	        
	        Pathway Visualization&nbsp;
			<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select a Pathway&nbsp;<br>

	        <select name="pathwayName" onchange="needGVal = false;" id="pathwayName" style="margin-left:20px;width:350px;">
			</select>
		</div>
		<br><br>
        <div style="text-align:center">
        	<input type="reset" value="clear" id="clearButton" class="">&nbsp;&nbsp;
	        <input type="button" class="subButton" value="Go" onclick="popPathway($F('pathwayName'));" />
        </div>
        
		</fieldset>
		</form>
	</body>
</html>