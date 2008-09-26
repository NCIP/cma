<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<content tag="tabs"><g:render template="/tabs" model="[location:'gene']"/></content> 
		
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				try	{
				
				} catch(e){ }
			});
		
			
			
		</script>
	</head>
	<body>
		<h3>Gene View</h3>
		<g:each in="${params}">
		     key: ${it.key} <br/>
		     value: ${it.value}
		     	<br/><br/>
		</g:each>
<hr/>
Plotting: ${geneExpPlot}
<br/>
Plotting: ${params.plot }
<br/>

	</body>
</html>