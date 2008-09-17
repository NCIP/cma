<div id="nav">
	<div id="modernbricksmenu2">
		<ul>
			<li id="home_tab" style="margin-left: 1px"><a href="index.jsp" title="Home">Home</a></li>
			<li id="gene_tab"><a href="graph.do?method=setup" title="">Gene View</a></li>
			<li id="genome_tab"><a href="heatmap.do" title="">Genome View</a></li>
			<li id="clinical_tab"><a href="clinicalPrefInit.do?method=setup" title="">Clinical View</a></li>
			<li id="analysis_tab"><a href="analysisHome.do" title="">Analysis Tools</a></li>
			<li id="study_tab"><a href="studyResults.do" title="">Study Results</a></li>	
		</ul>
	</div>	
	<br style="clear: left" />
</div>

<script type="text/javascript">
try	{
	$("${location}_tab").addClassName("current");
} catch(){}
</script>