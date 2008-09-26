<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<content tag="tabs"><g:render template="/tabs" model="[location:'gene']"/></content> 
		<g:javascript library="overlib_mini"/>
		<script type='text/javascript' src='../dwr/engine.js'> </script>
        <script type='text/javascript' src='../dwr/util.js'> </script>
        <script type='text/javascript' src='../dwr/interface/DynamicListHelper.js'> </script>
         
		<script type="text/javascript">
			Event.observe(window, "load", function()	{
				try	{
				
				} catch(e){ }
			});
			
			function popCoin(gene,key)	{
				//alert(gene + " : " + key + " : " + alg);
				var url = "popCoinGraph.do?method=popCoinGraph&geneSymbol="+encodeURIComponent(gene)+"&reporter="+encodeURIComponent(key);
				window.open( url, 'page2', 'status,resizable,scrollbars,width=700px,height=730px,screenX=100,screenY=100');
			}

			 function toggleGenePlot(a, b)	{
				if($("geneChart"))	{
					var chart = $("geneChart");
					//alert(chart.src + " = " + a);
					var imgURL = chart.src.split("filename=");
					if(arguments[1])	{
						if(imgURL[1] == a)	{
							chart.src = (imgURL[0] + "filename=" + b);
							if($('graphLink'))
								$('graphLink').href = (imgURL[0] + "filename=" + b);
							chart.useMap = "#"+b;
						}
						else if(imgURL[1] == b)	{
							chart.src = (imgURL[0] + "filename=" + a);
							if($('graphLink'))
								$('graphLink').href = (imgURL[0] + "filename=" + a);
							chart.useMap = "#"+a;
						}
						else	{
							chart.src = (imgURL[0] + "filename=" + a);
							if($('graphLink'))
								$('graphLink').href = (imgURL[0] + "filename=" + a);
							chart.useMap = "#"+a;
						}
					}
					else	{
						chart.src = (imgURL[0] + "filename=" + a);
						if($('graphLink'))
							$('graphLink').href = (imgURL[0] + "filename=" + a);
						chart.useMap = "#"+a;
					}
					//reset
					if(document.getElementsByName("graphTypeLinks"))	{
						var lnks = document.getElementsByName("graphTypeLinks");
						for(var i=0; i<lnks.length; i++)	{
							lnks[i].style.color = "";
							lnks[i].style.textDecoration = "underline";
						}
					}
					//highlight the default one
					if($(a+"_link"))	{
						$(a+"_link").style.color = "black";
						$(a+"_link").style.textDecoration = "none";
					}
					else	{
						setTimeout(function()	{
							if($(a+"_link"))	{
								$(a+"_link").style.color = "black";
								$(a+"_link").style.textDecoration = "none";
							}
						}, 100);
					}
				} 
			}
			
			var w;
			function spawnAnnot(reporterId){
				try	{
					var url = "annotationViewer.jsp?";
					var winw = 800;
					var winh = 550;
					w = window.open("", "_blank",
						"screenX=0,screenY=0,status=yes,toolbar=no,menubar=no,location=yes,width=" + winw + ",height=" + winh + 
						",scrollbars=yes,resizable=yes");
						
						DynamicListHelper.processRepoterAnnotation(reporterId, spawnAnnot_cb);
					}
					catch(err)	{
						alert("Annotation link is not currently available.");
					}
				}
			function spawnAnnot_cb(txt)	{
				try	{
					var page = escape(txt);
					//alert(page);
					//w.location.replace('annotationViewer.jsp?p='+page);
					w.location.href = txt;
					//w.location.href = "http://cnn.com";
				}
				catch(err){	}
			}
		</script>
	</head>
	<body>
	${sw?.toString()}

		<h3>Gene View</h3>
%{--
		<g:each in="${params}">
		     key: ${it.key} <br/>
		     value: ${it.value}
		     	<br/><br/>
		</g:each>
		<hr/>
		<br/>
--}%

<div style="border:0px solid #000;margin:5px;">
		<div>
				Graph Type:
				<a href="javascript:toggleGenePlot('${gmfilename}');" name="graphTypeLinks"  style="text-decoration:none" id="${gmfilename}_link">Geometric Mean</a> | 
				<a href="javascript:toggleGenePlot('${log2filename}');" name="graphTypeLinks" id="${log2filename}_link">Log2 Intensity</a> |
				<a href="javascript:toggleGenePlot('${bwFilename}');" name="graphTypeLinks"  id="${bwFilename}_link">Box and Whisker Log2 Intensity</a><br/>
		</div>
		<br/>
		<a class="message" style="text-decoration:underline" id="graphLink" 
			href="${defaultURL}" target="_blank">Click here to open plot in a new window</a>
		<br/><br/>

		<h4>Gene Expression Plot (${geneSymbol?.toUpperCase()})</h4>
		<div style="">
			<img src="${defaultURL}" border=0 usemap="#${defaultFilename}" id="geneChart" />
		</div>
		
		<div style="">
			<br/>
				<fieldset style="border:1px solid gray; text-align:left; padding:5px;">
					<legend class="red" style="font-size:1.0em">Legend: Probesets</legend><br/>
						${legendHtml}
				</fieldset>
		</div>
</div>

	</body>
</html>