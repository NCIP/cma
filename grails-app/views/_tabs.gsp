<div id="nav">
	<div id="modernbricksmenu2">
		<ul>
			<li id="home_tab" style="margin-left: 1px"><a href="${createLinkTo(dir:'')}" title="Home">Home</a></li>
			<li id="gene_tab"><a href="${createLink(controller:'geneView')}" title="">Gene View</a></li>
			<li id="genome_tab"><a href="${createLink(controller:'genomeView')}" title="">Genome View</a></li>
			<li id="clinical_tab"><a href="${createLink(controller:'clinical')}" title="">Clinical View</a></li>
			<li id="analysis_tab"><a href="${createLink(controller:'analysisTools')}" title="">Analysis Tools</a></li>
			<li id="study_tab"><a href="${createLink(controller:'studyResults')}" title="">Study Results</a></li>
			<g:if test="${session.userId != null }">
				<li><a style="background-color:#fff;color:#000;" href="${createLink(controller:'applicationUser', action:'logout')}" title="">Welcome, ${session.userId} - Logout</a></li>
				
			</g:if>	
		</ul>
	</div>	
	<br style="clear: left" />
</div>

<g:javascript>
try	{
	$("${location}_tab").addClassName("current");
} catch(e){

}
</g:javascript>