<content tag="tabs"><g:render template="/tabs" model="[location:'genome']"/></content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				new Ajax.Request("${createLink(controller:'genomeView', action:'links')}", {
				  onSuccess: function(transport) {
				   // console.log(transport.responseJSON.length);
				   if(transport.responseJSON == null)
				   	return;
				   	
				    transport.responseJSON.each( function(el)	{
				    	//quick - context double check
				    	if(el.project!="TCGA GBM")
				    		return;
				    		
				    	switch(el.dataset_type)	{
				    		//quick adaptor for legacy DIV Id to dataset_type from XML
					    	case "copy_number":
					    		makeLink(el, "genomeBasedCN");
					    		break;
					    	case "gene_expression":
					    		makeLink(el, "genomeBased");
					    		break;	
					    		case "gene_methylation":
					    		makeLink(el, "genomeBasedME");
					    		break;	
					    	default:
					    	break;
				    	}
				    });
				  },
				  onFailure: function(t)	{
				  	alert("Could not contact CGWB at this time.  Please try back later.");
				  }
				});
			});
			
			var makeLink = function(e, sect)	{
				//generate the html link based on JSON (launch_url and title atts reqd)
				if($(sect))	{
					var l = new Element("a", {'href': e.launch_url, 'target' : '_blank'}).update(e.title);
					$(sect).insert(l);
					$(sect).insert(new Element("br"));
				}
			}
		</script>
	</head>
	<body>
		<h3>Genome View</h3>
		
		<fieldset>
			<legend>Genomic View of Copy Number Data</legend>
			<div style="padding:5px;margin-left:40px;">
				<div id="genomeBasedCN"></div>
			</div>
		</fieldset>
		<fieldset>
			<legend>Genomic View of Gene Expression Data</legend>
			<div style="padding:5px;margin-left:40px;">
				<div id="genomeBased"></div>
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Genomic View of Methylation Data</legend>
			<div style="padding:5px;margin-left:40px;">
				<div id="genomeBasedME"></div>
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Genomic View of Mutation Data</legend>
			<div style="padding:5px;margin-left:40px;">
				<a href="http://cgwb.nci.nih.gov/cgi-bin/fwd?hint=tcga&hint2=genomic" target="_blank">Cancer Genome Workbench (Genomic View)</a>
			</div>
		</fieldset>

<b>Requires Java Plugin</b><br/><br/>

	
	</body>
</html>