<content tag="tabs"><g:render template="/tabs" model="[location:'analysis']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
	</head>
	<body>
		<h3>High Order Analysis</h3>
		
		<ul>
			<li><a href="${createLink(controller: 'analysisTools', action:'pcaSetup') }">Principle Component Analysis (PCA)</a></li>
			<li><a href="${createLink(controller: 'analysisTools', action:'genePatternSetup') }">Gene Pattern Analysis</a></li>
			<li><a href="${gpHomeURL}" target="_blank">Gene Pattern Home</a></li>
			<li><a href="${createLink(controller: 'genomeView')}">Integrated Heatmap Viewer</a></li>
			<!-- Below commented until RBT functionality is provided -->
			<!-- <li><a href="#">Cancer Genome Workbench (Genomic View)</a></li> -->
		</ul>
	</body>
</html>