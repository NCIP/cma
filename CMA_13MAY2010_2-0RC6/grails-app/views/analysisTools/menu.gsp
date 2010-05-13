<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				new Effect.Corner($$('.sectionHeader')[0], 'top');
			});
		</script>
	</head>
	<body>
		<div id="helptag_analysis_tools" class="help"></div>
		<h2>Analysis Tools</h2>
		<fieldset>
		<div class="sectionHeader">
			<h5>Select an Analysis Tool</h5>
		</div>
		<div class="sectionBody" style="border:1px solid #E0E0E0;">
		<ul>
			<li><a href="${createLink(controller: 'analysisTools', action:'pcaSetup') }">Principal Component Analysis (PCA)</a></li>
			<li><a href="${createLink(controller: 'analysisTools', action:'genePatternSetup') }">Gene Pattern Analysis</a></li>
			<li><a href="${gpHomeURL}" target="_blank">Gene Pattern Home</a></li>
			<li><a href="${createLink(controller: 'genomeView')}">Integrated Heatmap Viewer</a></li>
			<!-- Below commented until RBT functionality is provided -->
			<!-- <li><a href="#">Cancer Genome Workbench (Genomic View)</a></li> -->
		</ul>
		</div>
		<br clear="both"/>
		</fieldset>
	</body>
</html>