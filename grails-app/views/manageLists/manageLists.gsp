<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		<content tag="tabs"><g:render template="/tabs" model="[location:'']"/></content>
		 <g:javascript library="overlib_mini"/>
         <script type='text/javascript' src='../dwr/engine.js'> </script>
         <script type='text/javascript' src='../dwr/util.js'> </script>
         <script type='text/javascript' src='../dwr/interface/DynamicListHelper.js'> </script>
         
         <script type='text/javascript' src='../dwr/interface/UserListHelper.js'></script>
		 <g:javascript library="legacy/ManageListHelper" />
		 
		 <script type="text/javascript">
			 Event.observe(window, 'load', function() {
			  	ManageListHelper.generic_cb();
			});
		 </script>
	</head>
	<body>
		<h3>Manage Lists</h3>
	
		<span id="info">&nbsp;</span>

		<div style="text-align:center">
			<g:each var="ltype" in="${lts}">
				<a href="#${ltype.toString()}Lists">${ltype.toString()} Lists</a> | 
			</g:each>
			<a href="#addList">Add List</a>
		</div>
		<br/><br/>
	
		<g:each var="lt" in="${lts}">
			<a name="${lt.toString()}Lists"></a>
			<fieldset class="groupList" id="${lt.toString()}ListsFS">
				<legend onclick="new Effect.toggle('${lt.toString()}Container')">
					${lt.toString()} Lists
				</legend>
				<div id="${lt.toString()}Container">
					<br/>
					<div id="${lt.toString()}ListDiv"></div>	
					
					<div id="${lt.toString()}UniteDiv" />
						New List Name:<input type="text" id="${lt.toString()}GroupName"/>
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'join')" value="Join Selected"/></b>	
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'intersect')" value="Intersect Selected"/></b>	
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'difference')" value="Difference"/></b>					
						<span class="status" id="${lt.toString()}GroupStatus"></span>
					</div>
					
				</div>
			</fieldset>
			<div style="text-align:right; margin:10px;">
				<a href="#" onclick="javascript:scroll(0,0);return false;">[top]</a>
			</div>
		</g:each>
		
    <script type="text/javascript">
    	//this is just for testing - must have Firebug or another JS console (this will cause IE to bomb fyi)
       DynamicListHelper.getAllLists(function(txt) { 
       //	console.log(txt);
      	console.log(txt.isJSON());
      	var l = txt.evalJSON();
      	console.log(l.length); //3, 1 for each type
      	console.log(l);
      	l.each(function(t)	{
      		console.log("-------------- List Type: " + t.listType + "----------------------");
      		
      		t.listItems.each( function(tt)	{
      			console.log(tt.listName);
      		});
      	}); 	
      });
    </script>
	</body>
</html>