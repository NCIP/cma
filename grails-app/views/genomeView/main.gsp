<content tag="tabs"><g:render template="/tabs" model="[location:'genome']"/></content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
			
				$$(".sectionHeader").each(function(e){
					new Effect.Corner(e, 'top');
				} );
				new Ajax.Request("${createLink(controller:'genomeView', action:'links')}", {
				  onSuccess: function(transport) {
				   // console.log(transport.responseJSON.length);
				   if(transport.responseJSON == null)
				   	return;
				   	
				    transport.responseJSON.each( function(el)	{
				    	//quick - context double check
				    	if(el.project!="TCGA GBM")	{
				    		if($('mutfs'))
					    		$('mutfs').remove();
				    	}
				    		
				    	switch(el.dataset_type)	{
				    		//quick adaptor for legacy DIV Id to dataset_type from XML
					    	case "copy_number":
					    	case "copy number\n":  //RBT feed uses this instead of copy_number 
					    	case "copy number":
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
		<h2>Genome View</h2>
		<fieldset>
		<div id="cnfs">
			<div class="sectionHeader"><h5>Genomic View of Copy Number Data</h5></div>
			<div class="sectionBody" style="padding-left:40px;">
				<div id="genomeBasedCN"></div>
			</div>
		</div>
		<br clear="all" />
		<br clear="all" />
		
		<div id="expfs">
			<div class="sectionHeader"><h5>Genomic View of Gene Expression Data</h5></div>
			<div class="sectionBody" style="padding-left:40px;">
				<div id="genomeBased"></div>
			</div>
		</div>
		<br clear="all" />
		<br clear="all" />
		<div id="metfs">
			<div class="sectionHeader"><h5>Genomic View of Methylation Data</h5></div>
			<div class="sectionBody" style="padding-left:40px;">
				<div id="genomeBasedME"></div>
			</div>
		</div>
		<br clear="all" />
		<br clear="all" />
		<div id="mutfs">
			<div class="sectionHeader"><h5>Genomic View of Mutation Data</h5></div>
			<div class="sectionBody" style="padding-left:40px;">
				<a href="http://cgwb.nci.nih.gov/cgi-bin/fwd?hint=tcga&hint2=genomic" target="_blank">Cancer Genome Workbench (Genomic View)</a>
			</div>
		</div>
		</fieldset>
<b>Requires Java Plugin</b><br/><br/>

	
	</body>
</html>