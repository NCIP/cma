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
			 Event.observe(window, "load", function()	{
				new Effect.Corner($('superTop'), 'top');
				new Effect.Corner($('bottom'), 'bottom');
			 });
		</script> 
		
        <g:layoutHead />

    </head>
    <body>
        <div id="overDiv" style="position:absolute; visibility:hidden; z-index:1000;"></div>
		<div id="superTop"></div>
		<div id="top">
			<div style="padding:5px;color:#fff;text-align:right">
				Context:
				<form name="contextSwitchForm" id="contextSwitchForm" action="#" method="POST">
					<select name="dataContext" id="dataContextSelect" onchange="">
						<option value="tcgaDataContext">TCGA</option>
						<option value="rembrandtDataContext">Rembrandt</option>
					</select>
			 	</form>
			</div>
		</div>
		
		<div id="middle">
			<!--  tabs  -->
			<div id="myMenu">
			<g:fixScript name="none">
				<g:pageProperty name="page.tabs" default="${render(template:'/tabs', model:[location:'home'])}"/></div>
			</g:fixScript>
			
			<!--  sidebar -->
			<div id="content" style="padding:10px;">
	    	    <g:layoutBody />
	    	    <br clear="all"/>
	    	</div>
	    	<br clear="all"/>
	    </div>	

		<div  id="bottom" align="center">
			<table>
				<tr>
					<td><a href="http://www.cancer.gov/"><img width="63" height="31" border="0" alt="National Cancer Institute" src="${createLinkTo(dir:'images',file:'footer_nci.gif')}" /></a></td>
					<td><a href="http://www.dhhs.gov/"><img width="39" height="31" border="0" alt="Department of Health and Human Services" src="${createLinkTo(dir:'images',file:'footer_hhs.gif')}"/></a></td>
					<td><a href="http://www.nih.gov/"><img width="46" height="31" border="0" alt="National Institutes of Health" src="${createLinkTo(dir:'images',file:'footer_nih.gif')}"/></a></td>
					<td><a href="http://www.firstgov.gov/"><img width="91" height="31" border="0" alt="FirstGov.gov" src="${createLinkTo(dir:'images',file:'footer_firstgov.gif')}"/></a></td>
					<td valign="center" style="color:LightSlateGray">&nbsp;&nbsp;<b>|</b></td>
					<td valign="center"><a style="color:DarkGray;font-size:.8em" href="helpDocs/cmap_user_guide.pdf" target="_blank">User Guide</a></td>
				</tr>
			</table>
		</div>	    	
    </body>	
</html>