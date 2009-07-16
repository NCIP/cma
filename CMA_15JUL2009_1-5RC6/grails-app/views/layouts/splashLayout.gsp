<html>
    <head>
        <title><g:layoutTitle default="CMA" /></title>
        
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'touch.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'bricks.css')}" />
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'cma.css')}" />
            
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="application" />	
        <g:javascript library="prototype" />	
        <g:javascript library="scriptaculous" />	
        <g:javascript library="corners" />
        <script type="text/javascript">
        	var helpUrl = "${grailsApplication.config.gov.nih.nci.cma.helpUrl}";
        </script>
        <g:javascript library="Help" />
		<script type="text/javascript">
			 Event.observe(window, "load", function()	{
				new Effect.Corner($('superTop'), 'top');
				new Effect.Corner($('bottom'), 'bottom');
				
				new Effect.Corner($('ccontextDiv'), 'bl');
				$('ccontextDiv').setOpacity(0.7);
				$('contextOptions').setOpacity(0.7);
				
				$('contextDiv').observe('mouseover', function()	{$('ccontextDiv').setOpacity(0.9);} );
				$('contextDiv').observe('mouseout', function()	{$('ccontextDiv').setOpacity(0.7);} );
				
				$('contextDiv').observe('mouseover', function()	{$('contextOptions').setOpacity(0.9);} );
				$('contextDiv').observe('mouseout', function()	{$('contextOptions').setOpacity(0.7);} );

				$('contextDiv').observe('click', function()	{$('contextOptions').toggle();} );
				$('middle').observe('mouseover', function()	{ $('contextOptions').hide();}	);
				
				//insert the help, passing path to the images dir
				//Help.url = "${createLinkTo(dir:'')}/"+Help.url;
				Help.url = helpUrl+"/cmaPortal/index.html?single=false&context=cmaPortal&topic="; //declared above
				Help.guideUrl = helpUrl;
				Help.parsePage({pth: "${createLinkTo(dir:'images')}" });
			 });
		</script> 
        <g:layoutHead />

    </head>
    <body>
        <div id="overDiv" style="position:absolute; visibility:hidden; z-index:1000;"></div>
		<div id="superTop"></div>
		<div id="top">
			<div id="contextDiv">
				<div id="ccontextDiv">
					<span id="ccontextText" style="background-image:url(${createLinkTo(dir:'images',file:grailsApplication.config.cma.dataContext.toLowerCase()+'Context30.gif')});">
						${grailsApplication.config.cma.dataContexts[grailsApplication.config.cma.dataContext].displayName ?: "Rembrandt" }
					</span>
					<span id="ccontextArrow">
						V
					</span>
				</div>
				<!--  end ccontextDiv -->
				<div id="contextOptions" style="display:none;">
					<div>
						Select Context
					</div>					
					<g:each in="${grailsApplication.config.cma.dataContexts}">
						<div id="">
							<a id="${it.key.toLowerCase()}Context" href="/cma-${it.key.toLowerCase()}/"  style="display:block;background-image: url(${createLinkTo(dir:'images',file:it.key.toLowerCase()+'Context30.gif')});" >${it.value.displayName}</a>
						</div>
					</g:each>
				</div>
				<!--  end ccontextOptions -->
			</div>
			<!-- end contextDiv -->
		</div>
		
		<div id="middle">
			<!--  tabs  -->
			
			<g:fixScript name="none">
				<g:pageProperty name="page.tabs" default="${render(template:'/tabs', model:[location:'home'])}"/>
			<!--  sidebar -->
				<g:pageProperty name="page.side" default="${render(template:'/sideBar')}"/>
			</g:fixScript>
			<div id="content" style="padding:10px;">
	    	    <g:layoutBody />
	    	    <br clear="all"/>
	    	</div>
	    	<br clear="all"/>
	    </div>	

		<div id="bottom" align="center">
			<table align="center">
				<tr>
					<td><a href="http://www.cancer.gov/"><img width="63" height="31" border="0" alt="National Cancer Institute" src="${createLinkTo(dir:'images',file:'footer_nci.gif')}" /></a></td>
					<td><a href="http://www.dhhs.gov/"><img width="39" height="31" border="0" alt="Department of Health and Human Services" src="${createLinkTo(dir:'images',file:'footer_hhs.gif')}"/></a></td>
					<td><a href="http://www.nih.gov/"><img width="46" height="31" border="0" alt="National Institutes of Health" src="${createLinkTo(dir:'images',file:'footer_nih.gif')}"/></a></td>
					<td><a href="http://www.firstgov.gov/"><img width="91" height="31" border="0" alt="FirstGov.gov" src="${createLinkTo(dir:'images',file:'footer_firstgov.gif')}"/></a></td>
				</tr>
			</table>
		</div>	    	
    </body>	
</html>