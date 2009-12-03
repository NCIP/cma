<content tag="tabs"><g:render template="/tabs" model="[location:'']"/></content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		
		 <g:javascript library="overlib_mini"/>
         <script type='text/javascript' src='../dwr/engine.js'> </script>
         <script type='text/javascript' src='../dwr/util.js'> </script>
         <script type='text/javascript' src='../dwr/interface/DynamicListHelper.js'> </script>
         
         <script type='text/javascript' src='../dwr/interface/UserListHelper.js'></script>
		 <g:javascript library="legacy/ManageListHelper" />
		 
		 <script type="text/javascript">
			 Event.observe(window, 'load', function() {
			  	ManageListHelper.generic_cb();
			  	//dom timing issue in IE6, so defer via prototype
			  	ManageListsRoundTops.defer();
			});
			
			var ManageListsRoundTops =  function()	{
				$$('div.sectionHeader').each(function(s) {
					new Effect.Corner(s, 'top');
				})
			};
		</script>
		 <style>
		 	.listItemsDiv li	{
		 		padding:5px;
		 	}
		 </style>
	</head>
	<body>
		<div id="helptag_manage_lists_overview" class="help"></div>
		<h3>Manage Lists</h3>
	
		<span id="info">&nbsp;</span>

		<div style="text-align:center">
			<g:each var="ltype" in="${lts}">
				<a href="#${ltype.toString()}Lists">${ltype.toString()} Lists</a> |  
			</g:each>
			<g:isLoggedIn>
				<a href="#addList">Add List</a>
			</g:isLoggedIn>
		</div>
		<br/><br/>
	
		<g:each var="lt" in="${lts}">
			<a name="${lt.toString()}Lists"></a>
			<fieldset class="groupList" id="${lt.toString()}ListsFS">
				<div class="sectionHeader" onclick="new Effect.toggle('${lt.toString()}Container')">
					<h5>${lt.toString()} Lists</h5>
				</div>
				<div id="${lt.toString()}Container" class="sectionBody" style="border:1px solid #E0E0E0;">
					<br/>
					<div id="${lt.toString()}ListDiv"></div>	
					
					<div id="${lt.toString()}UniteDiv">
						New List Name:<input type="text" id="${lt.toString()}GroupName"/>
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'join')" value="Join Selected"/></b>	
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'intersect')" value="Intersect Selected"/></b>	
						<b><input type="button" onclick="ManageListHelper.groupSelectedLists('${lt.toString()}','${lt.toString()}ListsFS', $('${lt.toString()}GroupName').value,'difference')" value="Difference"/></b>					
						<span class="status" id="${lt.toString()}GroupStatus"></span>
					</div>
					<br clear="all"/>
				</div>
				<br clear="all"/>
			</fieldset>
			<div style="text-align:right; margin:10px;">
				<a href="#" onclick="javascript:scroll(0,0);return false;">[top]</a>
			</div>
			<br clear="all"/>
		</g:each>
		
		<!--  -------------- -->	
		<g:isLoggedIn>
			<g:uploadForm name="cmaListUploadForm" controller="manageLists" action="upload"> 
				<a name="addList"></a>
				<div class="sectionHeader" onclick="new Effect.toggle('UploadContainer')">
					<h5>Upload New List</h5>
				</div>
				<div id="UploadContainer" class="sectionBody" style="border:1px solid #E0E0E0;">
				    	
					<g:if test="${flash.message}">
				    	<div class="message">${flash.message}</div><br/>
				    </g:if>
				        	         	        
					<table>
						<tr>
							<td>Choose the list type:</td>	
							<td>
								<g:select name="listTypes" style="width: 100px; overflow: none;" from="${lts}"></g:select>
							</td>
						</tr>
						<tr>
							<td valign="middle">Upload File*:</td>
							<td valign="middle">
								<input type="file" name="cmaListUpload" />
							</td>
						</tr>
						<tr>
							<td class="label">List Name*:</td>			
							<td>						
								<input type="text" id="listName" size="25" name="listName"/>
							</td>
						</tr>
						<tr>
							<td class="label">List Description*:</td>			
							<td>						
								<input type="text" id="listDesc" size="50" name="listDesc"/> &nbsp;&nbsp;&nbsp;<input type="submit" value="Add List"/>
							</td>
						</tr>
					</table>
				</div>		
				<div style="text-align:right; margin:10px;">
					<a href="#" onclick="javascript:scroll(0,0);return false;">[top]</a>
				</div>
				<br clear="all"/>
			</g:uploadForm>		
		</g:isLoggedIn>
		<!--  -------------- -->
	</body>
</html>