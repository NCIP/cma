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
		<g:javascript library="legacy/boxV2/api" />
		<g:javascript library="legacy/boxV2/lassoHelperV2" />
		
		
	<!--
		<g:javascript library="legacy/box/x_core" />
		<g:javascript library="legacy/box/x_event" />
		<g:javascript library="legacy/box/x_dom" />
		<g:javascript library="legacy/box/x_drag" />
		<g:javascript library="legacy/box/wz_jsgraphics" />
		<g:javascript library="legacy/box/dbox" />
	-->	
		<script type="text/javascript">
			function togglePCAPlot(plot) {
				if($$(".currentPlot")[0].id == plot)	{
					return; //clicked one we already are showing
				}
				//alert($$(".currentPlot").length);
			
				$$("li.current")[0].removeClassName("current");
				//hide the currentPCA
				$$(".currentPlot")[0].hide();
				$$(".currentPlot")[0].removeClassName("currentPlot");
				
				//show the requested one
				$(plot+"_img").addClassName("currentPlot");
				$(plot+"_img").show();


				//var imgURL = $("pcaChart").src.split("filename=");
				//$("pcaChart").src = (imgURL[0] + "filename=" + plot);
				//alert(chart.src);
				//$("pcaChart").useMap = "#"+plot;
				
				
				//reset
				if(document.getElementsByName("graphTypeLinks"))	{
					var lnks = document.getElementsByName("graphTypeLinks");
				}
				//highlight the default one
				var temp = plot+"_link";
		
				var defaultLink = $(plot+"_link");
				$(plot+"_link").up().addClassName("current");
				//call to the graphics part from lassoHelper.js
				
				init(plot+"_img");
				initMarkerPoints(plot);
			}
			
			Event.observe(window, "load", function()	{
				init("${PC1vsPC2}_img");
				initMarkerPoints("${PC1vsPC2}");
			});
			
		</script>
		<style>
			#middle, #bottom, #superTop, #top {
				margin: 0px 5px 0px 5px;
				width:725px;
			}
			#drag { 
				border:1px solid red;
				background-color:#eee;
				-moz-opacity:.4;
				-khtml-opacity:.4;
				filter: alpha(opacity=40);
				opacity:.4;
			}
		</style>
	</head>
	<body>
		${sw}
		<div id="helptag_pca_plot" class="help"></div>
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
							<div id="pcaImageContainer" style="width:550px; overflow:auto; text-align:center; left:30 ">
							<!-- 
								<img src="${defaultURL }" border="0" usemap="#${defaultFilename}" id="pcaChart">
							-->	
								<img src="${PC1vsPC2URL}" border="0" usemap="#${PC1vsPC2}" id="${PC1vsPC2}_img" class="currentPlot"/>
								<img src="${PC1vsPC3URL}" border="0" usemap="#${PC1vsPC3}" id="${PC1vsPC3}_img" style="display:none;"/>
								<img src="${PC2vsPC3URL}" border="0" usemap="#${PC2vsPC3}" id="${PC2vsPC3}_img" style="display:none;"/>
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
				<div style="margin-left:10px;font-size:10px;text-decoration:none; text-align:center;">
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
		<!--  
		<g:javascript library="legacy/box/lassoHelper" />
		-->
	</body>
</html>