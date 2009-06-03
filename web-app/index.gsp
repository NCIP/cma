<content tag="tabs">&nbsp;</content>
<content tag="side">&nbsp;</content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<script type="text/javascript">
			var process = function(sect)	{
				//process when the use clicks a link on the spashpage
				//alert("go to " + sect + " page");
				var go="${createLinkTo(dir:'')}";
				switch(sect)	{
					case "analysis":
					case "tools":
						go = "${createLink(controller:'analysisTools')}";
					break;
					

					case "gene":
						go = "${createLink(controller:'geneView')}";
					break;
					case "genome":
						go = "${createLink(controller:'genomeView')}";
					break;
					case "clinical":
						go = "${createLink(controller:'clinical')}";
					break;
					default:
					break;
				}
				location.href=go;
			}		

			Event.observe(window, "load", function()	{
				$('splashMain').update($('geneContent').innerHTML);
				$$(".splashMenu").each( function(el)	{
					el.observe("click", function() { process(el.title); } );
					el.observe("mouseover", function() { nav(el); });
				});
				
			});
			var nav = function(el)	{
				//switches content onmouseover for the splashpage
				//rem curr class
				$$(".splashCurrent")[0].removeClassName("splashCurrent");
				//add current class to el
				el.addClassName("splashCurrent");
				//change the content
				$('splashMain').update($(el.title + "Content").innerHTML);
			}
		</script>
		
		<style>
			#splashContainer table td	{
				vertical-align:top;
				cursor:pointer;
			}
			.splashBoxHeader	{
				background-color:#2B2D51;
				padding:3px; color:#fff;
				font-size:14px;
				font-weight: bold;
			}
			.splashBoxContent {
				border:2px solid #E0E0E0;
				padding:5px;
				background-image: url(${createLinkTo(dir:'images',file:'globeBG.gif')});
				background-position: -350 bottom;
				background-repeat: no-repeat;
			}
			.splashMenu	{
				width:200px;
				border-left: 0px solid #E0E0E0;
				color: silver;
				height:50px;
			}
			h5.splashMenu	{
				padding:5px;
				padding-top:20px;
				padding-bottom:20px;
				font-size:18px;
				font-family:tahoma;
			}
			.splashCurrent {
				background-color:#2B2D51;
				padding:3px; 
				color:#fff;
			}
			#splashMain	{
				height:300px;
				overflow:auto;
				background-color:#F0F0F0;
				background-image: url(${createLinkTo(dir:'images',file:'globeBG.gif')});
				background-position: -350 bottom;
				background-repeat: no-repeat;
			}
            .message {
                color: red;
            }
		</style>
    </head>
    <body>

		<div id="splashContainer">
			<div style="width: 99%">
				<table border="0" cellspacing="0" cellpadding="0" style="width: 100%">
					<tbody>
						<tr>
							<td title="gene">
								<h5 class="splashMenu splashCurrent" title="gene">Gene View</h5>
								<h5 class="splashMenu" title="genome">Genome View</h5>
								<h5 class="splashMenu" title="clinical">Clinical View</h5>
								<h5 class="splashMenu" title="tools">Analysis Tools</h5>
							</td>
							<td rowspan="3" id="splashMain" style="border: 5px solid #2B2D51;">
							</td>
							<td rowspan="3">
								<div style="width: 250px;">
									<div style="padding: 20px">
									<g:contextAware mode="hideFromOnly" context="TCGA">
										<div class="splashBoxHeader">
											Data Access:
										</div>
										<div class="splashBoxContent">
											<br/>
											The CMA Application serves public data from the context.
											<br/><br/><br/>  
										</div>
										<br/><br/>
									</g:contextAware>
									<g:contextAware mode="showOnlyTo" context="TCGA, TCGAOvarian">
										<span style="color: DimGray; font-size: .9em">
											* Login	is not required for public data access
										</span>
										<br />
										<br />
										<div class="splashBoxHeader">
											Existing Users:
										</div>
										<div class="splashBoxContent">
										<br />
                                            <g:if test="${flash.message}">
                                                <div class="message">${flash.message}</div>
                                            </g:if>
                                            <g:if test="${session.userId == null }">
                                                <g:form name="loginForm" id="loginForm" controller="applicationUser" action="login" method="post" >
                                                    <table>
                                                        <tr>
                                                            <td valign="middle">
                                                                username:
                                                            </td>
                                                            <td>
                                                                <input type="text" name="userId" value="" id="username" style="width:110px;">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td valign="middle">
                                                                password:
                                                            </td>
                                                            <td>
                                                                <input type="password" name="password" value="" id="password" style="width:110px;">
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                &nbsp;
                                                            </td>
                                                            <td style="text-align: center; padding: 3px">
                                                                <input type="submit" value="login" />
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                        	<td colspan="2" align="center">
                                                            	<a style="font-size;10px;font-weight:normal;" href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank">trouble logging in?</a>
                                                        	</td>
                                                        </tr>
                                                    </table>
                                                </g:form>
                                            </g:if>
                                            <g:else>
                                                <br/>
                                                Welcome, ${session.userId}<br/>
                                                <a style="background-color:#fff;color:#000;" href="${createLink(controller:'applicationUser', action:'logout')}" title="">Logout</a>
                                                <br/><br/>
                                            </g:else>
										</div>
										<br />
										<br />
										</g:contextAware>
										
										<div class="splashBoxHeader">
											Application Help:
										</div>
										<div class="splashBoxContent">
										<div id="helptag_welcome" class="help"></div>
											<a href="#" onclick="Help.popHelp('welcome');return false;">Application Help</a><br/>
											<a href="#" onclick="Help.popGuide();return false;" target="_blank">User Guide</a>
										</div>
										<br/><br/>
										<div class="splashBoxHeader">
											Additional Information:
										</div>
										<div class="splashBoxContent">
											<ul>
											<li>CMA Version <g:meta name="app.version"/></li>
											
											<g:contextAware mode="showOnlyTo" context="TCGA, TCGAOvarian">
												<li>
													<!-- <g:link controller="registration">Register</g:link> -->
													<a href="http://cancergenome.nih.gov/dataportal/data/access" target="_blank">Register</a>
												</li>
											</g:contextAware>
												<li>
													<a href="http://ncicb.nci.nih.gov/NCICB/support" target="_blank">Provide your feedback</a>
												</li>
											</ul>
											<br/>
										</div>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<td> </td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!--  hold content here -->

		<div id="contentHolder" style="display:none;">
		
			<div id="studyResultsContent">
				<div class="splashBoxHeader">Study Results</div>
				<div class="" style="padding:5px;" title="results">
					Publication reference: <br/><br/> 
					Author, Author, Author. Comprehensive genomic characterization defines novel genes and core pathways in human glioblastoma. Nature 2008 Oct 3; 475(7501):553-63.
					<br/><br/>
					<div align="center">
						<img src="${createLinkTo(dir:'images',file:'pathwayMap_thumb.png')}" align="center"/>
					</div>
				</div>
			</div>
		
			<div id="geneContent">
				<div class="splashBoxHeader">Gene View</div>
				<div class="" style="padding:5px;" title="geneView">
					Visualize gene expression, copy number, SNP, and pathway data on a gene by gene basis.  Generate detailed study related reports for a given gene.
					<br/><br/>
					Available resources include:  Gene Expression Plots, KM Survival Plots, Genome Browser, and Pathway Visualizations.
					<br/><br/>
					<div align="center" style="height:217px;background-image: url(${createLinkTo(dir:'images',file:'geneViewCollage.png')}); ">
					</div>
				</div>
			</div>
			<div id="genomeContent">
				<div class="splashBoxHeader">Genome View</div>
				<div class="" style="padding:5px;" title="genomeView">
					Explore all of the study data in one genome level visualization.  Investigate chromosomal regions of amplification, deletion and over expression.   Zoom in on a chromosomal region of interest for a more detailed view. 
					<br/><br/>
					Available resources include: Integrated Heatmap Viewer for Genomic Data.
					<br/><br/>
					<div align="center">
						<img src="${createLinkTo(dir:'images',file:'heatmap.gif')}" align="center"/>
					</div>
				</div>
			</div>
			<div id="clinicalContent">
				<div class="splashBoxHeader">Clinical View</div>
				<div class="" style="padding:5px;" title="clinicalView">
					Investigate the study clinical data.  Explore the relationships between clinical and molecular study data. 
					<br/><br/>
					Available resources include: Clinical Query with Reports and KM Sample Plots
					<br/><br/>
					<div align="center" style="height:217px;background-image: url(${createLinkTo(dir:'images',file:'clinicalViewCollage.png')}); ">
					</div>
				</div>
			</div>
			<div id="toolsContent">
				<div class="splashBoxHeader">Analysis Tools</div>
				<div class="" style="padding:5px;" title="analysisTools">
					Analyze the study data using analysis tools such as Genepattern, Principal Component Analysis, and the Cancer Genome Workbench
					<br/><br/>
					Available resources include: PCA Analysis and Gene Pattern Integration
					<br/><br/>
					<div align="center" style="height:217px;background-image: url(${createLinkTo(dir:'images',file:'analysisToolsCollage.png')}); ">
					</div>
				</div>
			</div>
		</div>
		<!--  end content holder -->
		
		
	</body>
</html>