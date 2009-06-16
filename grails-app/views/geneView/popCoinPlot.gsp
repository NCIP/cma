<html>
    <head>
		<title>CMA Box and Whisker Log2 Intensity Coin Plots</title>
		
		<g:javascript library="prototype" />
		<g:javascript library="Help" />

        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'touch.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'bricks.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'cma.css')}" />
            
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
		<style>
			body {
				background-color:#fff;
			}	
		</style>
	</head>
	<body>
		${sw?.toString()}
		<div id="helptag_coin_plot" class="help"></div>
		<h3>CMA Box and Whisker Log2 Intensity Coin Plots</h3>
		<div style="text-align:center;" >
			<br/><a class="message" style="text-decoration:underline" id="graphLink" 
				href="${bwgraphURL}" target="_blank">Click here to open plot in a new window</a>
			<br/><br/>
			<div style="width:700px; overflow:auto; text-align:center;">
				<h4>Box and Whisker Log2 Intensity Coin Plots ( ${geneSymbol?.toUpperCase()} )</h4>
				<h4>${reporterName} </h4>
				<img src="${bwgraphURL}" border="0" id="geneChart" />
			</div>
		</div>
		<script type="text/javascript">
        	var helpUrl = "${grailsApplication.config.gov.nih.nci.cma.helpUrl}";
			//Help.url = Help.url;
			Help.url = helpUrl+"/cmaPortal/index.html?single=false&context=cmaPortal&topic=";
			Help.guideUrl = helpUrl;
			Help.parsePage({pth: "${createLinkTo(dir:'images')}" });
		</script>
	</body>
</html>