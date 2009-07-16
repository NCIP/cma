<content tag="tabs"><g:render template="/tabs" model="[location:'genome']"/></content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<script type="text/javascript">
		
			var sectionTemplate = new Template("<div id='#{sectionId}'>" +
				"<div class='sectionHeader'><h5>#{sectionTitle}</h5></div>" +
				"<div class='sectionBody' style='padding-left:40px;'>" +
					"<div id='#{sectionContainerId}'></div><br/>" +
				"</div>" +
			"</div>" +
			"<br clear='all' />" +
			"<br clear='all' />");
		
			Event.observe(window, "load", function()	{
			
				$$(".sectionHeader").each(function(e){
					new Effect.Corner(e, 'top');
				} );
				
				new Ajax.Request("${createLink(controller:'genomeView', action:'links')}", {
				  onSuccess: function(transport) {
				   //console.log(transport);
				  if(transport.responseJSON == null)
				  	return;
				  	
				  var rJSON = transport.responseJSON;
				  
				  var sortDataType = function(a,b)	{
				  	return a.dataset_type > b.dataset_type
				  }
				  var sortExtent = function(a,b)	{
				  	return a.extent > b.extent
				  }
				  rJSON.heatmap_datasets.sort(sortExtent);
				  
				  
				  //key is extent + ": " + datatype
				  var makeKey = function(o)	{
				  	return o.extent+": "+o.dataset_type;
				  }
				 
				  var dataTypeHeaders =  Array();
				  //run thru once to build arrays of extent, datatype, submitter
				    rJSON.heatmap_datasets.each( function(el)	{
				    	if(dataTypeHeaders.indexOf(makeKey(el))==-1)	{
				    		dataTypeHeaders.push(makeKey(el));
				    	}
				    });
				    
				  //console.log(dataTypeHeaders);
				  
				  //create html containers
				  	dataTypeHeaders.each(function(e)	{
				  		var sect = {sectionId: e, 
				  		sectionTitle: e.replace(":", " based view of") + " data", 
				  		sectionContainerId: e+"Container" };
				  		var htm = sectionTemplate.evaluate(sect);
				  		$('sectionContainer').insert(htm);
					});		
					$$(".sectionHeader").each(function(e){
						new Effect.Corner(e, 'top');
					} );		  
				  //run thru again to inject into container
				   
				    rJSON.heatmap_datasets.each( function(el)	{
				    
			    		var eKey = makeKey(el);
			    		makeLink(el, eKey+"Container");
				    
				     /*	
				    	//console.log(el.dataset_type);
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
				    */
				    
				    
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
		
		<div id="helptag_genome_view" class="help"></div>
		
		<br/> 
		<span style="font-size:1.2em">&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://cgwb.nci.nih.gov/cgi-bin/heatmap" target="_blank"><u>Easy access to genome heatmaps</u></a></span>
		<br/><br/><br/>
		
		<fieldset id="sectionContainer">
		
		</fieldset>
<!-- 		
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
-->
<b>Heatmap links require the Java Plugin</b><br/><br/>

	
	</body>
</html>