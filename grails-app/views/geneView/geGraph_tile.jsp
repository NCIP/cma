<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "gov.nih.nci.cma.web.graphing.*" %>

<%
	String geneSymbol = (String) request.getAttribute("geneSymbol");
	
	String bwFilename = new String("");
	String log2filename = new String("");
	String gmfilename = new String("");
	String legendHtml = new String("");
	String gmgraphURL = new String("");
	String log2graphURL = new String("");
	String bwgraphURL = new String("");
	String defaultURL = new String("");
	String defaultFilename = new String("");
	
	if (geneSymbol == null)
		geneSymbol = "";

		GEPlot gePlot = (GEPlot)request.getSession().getAttribute("gePlot");
		if(gePlot != null)	{
			bwFilename = gePlot.generateBWLog2IntensityChart("Groups", 
						"Log2 Expression Intensity", request.getSession(), new PrintWriter(out), false);
			log2filename = gePlot.generateLog2Chart("Disease Type", 
						"Log2 Expression Intensity", request.getSession(), new PrintWriter(out));
			
			gmfilename = gePlot.generateGeometricMeanIntensityChart("Group", 
						"Mean Expression Intensity", request.getSession(), new PrintWriter(out));
				
			defaultFilename = gmfilename; //log2filename; //bwFilename;
			legendHtml = LegendCreator.buildLegend(gePlot.getLegendItemCollection(), "Probesets");
				
			//String size = (String) charts.get("size");
				
			gmgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + gmfilename;
			log2graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + log2filename;
			bwgraphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + bwFilename;
			defaultURL = gmgraphURL;  //log2graphURL; //bwgraphURL;
		}
	%>
	
<br/>

<div style="border:0px solid #000;margin:5px;">
	<div>
		<fieldset class="gray" style="">
				<legend class="red" style="">
					Graph Type:
				</legend>
				<br/>
				<a href="javascript:toggleGenePlot('<%= gmfilename %>');" name="graphTypeLinks"  style="text-decoration:none" id="<%= gmfilename %>_link">Geometric Mean</a> | 
				<a href="javascript:toggleGenePlot('<%= log2filename %>');" name="graphTypeLinks" id="<%= log2filename %>_link">Log2 Intensity</a> |
				<a href="javascript:toggleGenePlot('<%= bwFilename %>');" name="graphTypeLinks"  id="<%= bwFilename %>_link">Box and Whisker Log2 Intensity</a><br/>
		</fieldset>
	</div>
		
		<a class="message" style="text-decoration:underline" id="graphLink" 
			href="<%= defaultURL %>" target="_blank">Click here to open plot in a new window</a>
		<br/><br/>

		<h4>Gene Expression Plot (<%= geneSymbol.toUpperCase() %>)</h4>
		<div style="">
			<img src="<%= defaultURL %>" border=0 usemap="#<%= defaultFilename %>" id="geneChart" />
		</div>
		
		<div style="">
			<br/>
				<fieldset style="border:1px solid gray; text-align:left; padding:5px;">
					<legend class="red" style="font-size:1.0em">Legend: Probesets</legend><br/>
						<%= legendHtml %>
				</fieldset>
		</div>
</div>
