<content tag="tabs">&nbsp;</content>
<content tag="side">&nbsp;</content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<script type='text/javascript' src='../dwr/interface/DynamicListHelper.js'></script>
		<script type='text/javascript' src='../dwr/engine.js'></script>
		<script type='text/javascript' src='../dwr/util.js'></script>
		<g:javascript library="overlib_mini" />
		<g:javascript library="legacy/box/browserSniff" />
		
		<g:javascript library="legacy/a_saveSamples" />
		<g:javascript library="legacy/box/x_core" />
		<g:javascript library="legacy/box/x_event" />
		<g:javascript library="legacy/box/x_dom" />
		<g:javascript library="legacy/box/x_drag" />
		<g:javascript library="legacy/box/wz_jsgraphics" />
		<g:javascript library="legacy/box/dbox" />
		
		<script type="text/javascript">
			function togglePCAPlot(plot) {
				$$("li.current")[0].removeClassName("current");
				
				if(document.getElementById("pcaChart"))	{
					var chart = document.getElementById("pcaChart");
					var imgURL = chart.src.split("filename=");
					chart.src = (imgURL[0] + "filename=" + plot);
					chart.useMap = "#"+plot;
					
					//reset
					if(document.getElementsByName("graphTypeLinks"))	{
						var lnks = document.getElementsByName("graphTypeLinks");
						for(var i=0; i < lnks.length; i++)	{
							//lnks[i].style.backgroundColor = "#FFFAE1";
							//lnks[i].style.textDecoration = "underline";
						}
					}
					//highlight the default one
					var temp = plot+"_link";
			
					var defaultLink = document.getElementById(plot+"_link");
					$(plot+"_link").up().addClassName("current");
					
					//defaultLink.style.backgroundColor = "#F2f2f2";
					//defaultLink.style.textDecoration = "none";
					//call to the graphics part from lassoHelper.js
					setMain("pcaChart");
				}
			}
		</script>
		<style>
		#middle, #bottom, #superTop, #top {
			
			margin: 0px 5px 0px 5px;
			}
		</style>
	</head>
	<body>
		${sw}
		<h3>PCA</h3>
		
		<!-- start div#nav -->
		<div id="nav">
		<div id="modernbricksmenu2">
			<ul>
				<li class="current"> 
					<a href="javascript:togglePCAPlot('${PC1vsPC2 }');"  style="" name="graphTypeLinks" id="${PC1vsPC2 }_link">PC1vsPC2</a>
				</li>
				<li>
					<a href="javascript:togglePCAPlot('${PC1vsPC3 }');"  style="" name="graphTypeLinks" id="${PC1vsPC3 }_link">PC1vsPC3</a>
				</li>
				<li>
					<a href="javascript:togglePCAPlot('${PC2vsPC3 }');" style="" name="graphTypeLinks" id="${PC2vsPC3 }_link">PC2vsPC3</a>
				</li>
			</ul>
		</div>
		<br style="clear: left" />
		</div>
		<!--  end div#nav -->
		<!--  start table  -->
		<table>
			<tr>
				<td>
					<div id="main" style="font-family:arial; font-size:12px">
					<!-- div style="background-color:#ffffff;" -->
						<div style="border:0px solid #000;margin:5px;align-text:center">
							<div style="width:550px; overflow:auto; text-align:center; left:30 ">
								<img src="${defaultURL }" border="0" usemap="#${defaultFilename }" id="pcaChart">
							</div>
						</div>
					</div>
				</td>
				<td style="vertical-align:top">
				<div style="border:1px dashed silver;height:300px;width:100px; margin-left:10px; margin-top:30px; overflow:auto;" id="sample_list">
					<div style="background-color: #ffffff; width:100px; font-weight: bold; text-align:center;">Samples:</div><br/>
					<div style="font-size:9px; text-align:center;" id="sampleCount"></div><br/>
					<span id="pending_samples" style="font-size:11px"></span>
				</div>
				<br/>
				<div style="margin-left:10px; text-align:center">
					<input type="text" id="sampleGroupName" name="sampleGroupName" style="width:95px"/><br/>
					<input type="button" style="width:95px" value="save samples" onclick="javascript:A_saveSamples();" /><br/>			
				</div>
				<div style="margin-left:10px;font-size:5px;text-decoration:none; text-align:center;">
					<a href="#" onclick="javascript: if(confirm('clear samples?')) { clearPending();return false; } ">[clear samples]</a><br/>
				</div>
				</td>
			</tr>
		</table>
		<!--  end table -->
		
		<!--  translate samples to clinical report -->
		<form id="quickClinicalWrapper"></form>
		
		<script language="javascript">
		
			function processQuickClinical()	{
				var f = document.getElementById("quickClinicalWrapper");
				
				//quickly clear the node, so we dont get duplicate elements when the back button is used
				while(f.firstChild) f.removeChild(f.firstChild);
				
				if(!f)	{ return; }
				//set up the form
				f.setAttribute("method", "post");
				f.setAttribute("action", "quickClinical.do");
				f.setAttribute("name", "quickClinicalWrapper");
				
				for(var i=0; i<pendingSamples.length; i++)	{
					var hid = document.createElement("input");
					hid.setAttribute("type", "hidden");
					hid.setAttribute("name", "sampleList");
					hid.setAttribute("value", pendingSamples[i]);
					f.appendChild(hid);
				}
				
				f.submit();
			}
			
		</script>
		<g:javascript library="legacy/box/lassoHelper" />
	</body>
</html>