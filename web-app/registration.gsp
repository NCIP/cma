
<!--
<script type='text/javascript' src='../dwr/util.js'> </script>
-->

<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/RegHelper.js'> </script>

<g:javascript src="prototype/prototype.js" />
<g:javascript src="prototype/effects.js" />
<g:javascript src="Reg.js" />
<g:javascript src="fat.js" />
<g:javascript src="Help.js" />


<style>
	#regTable input	{
		width:150px;
	}
	#regTable div	{
		padding-bottom:10px;
	}
	#regTable label	{
		float: left;
		width: 120px;
	}
	#regTable fieldset	{
		height:600px;
		border:0px;
		border-top:1px solid #42659C;
		-moz-border-radius: 0px;
	}
	input#userName, input#password	{
		width:140px;
	}
	#regTable div.r, #regTable div.rb	{
		border-left: 2px solid #42659C;
		border-right: 2px solid #42659C;
		padding:5px;
	}
	#regTable div.rb	{
		border-bottom: 2px solid #42659C;
		margin-bottom:10px;
	}
	#regTable div.h, legend {
		color: #C6C7C6;
		background-color:#42659C; 
		font-weight:bold;
		padding:5px; 
	}
</style>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
			
		<content tag="tabs">&nbsp;</content>
		<content tag="side">&nbsp;</content> 
	</head>

<body>

<table id="regTable">
	<tr>
		<td>
		
	<script type="text/javascript">Help.insertHelp("Registering", "align='right'", "padding:_8px;");</script>
	<br clear="both"/>
	<form id="regForm">
	<fieldset>
		<legend>New Users</legend>
		<br clear="both"/>
		<b>Register for an account to gain instant access to public data</b><br/>
		<br/><div id="regErr" class="mmsg"></div><br/>
		<div><label>&nbsp;</label>* required field</div>
		<div class="h">Name:</div>
		<div class="r"><label>First name*:</label>  <input type="text" id="firstName"/></div>
		<div class="r"><label>Last name*:</label>  <input type="text" id="lastName"/></div>
		<div class="h">Contact Information:</div>
		<div class="r"><label>Email*:</label>  <input type="text" id="email"/></div>
		<div class="r"><label>-<br/></label>Your account information will be sent to this address</div>
		<div class="r"><label>Phone*:</label><input type="text" id="phone"/></div>
		<div class="r"><label>Institution*:</label> <input type="text" id="institution"/></div>
		<div class="r"><label>Department:</label><input type="text" id="dept"/></div>
		<div class="h">Verification:</div>

		<div class="r"><label>-<br/></label>Please type the text that is displayed in the image below</div>
		<div class="rb"><label>&nbsp;</label><input type="text" id="cap"/></div>

		<div>&nbsp;</div>
		<div style="text-align:center" id="regButtons">
			<input type="reset" value="Reset"/>
			<input type="button" value="Register" onclick="Reg.pReg();"/>
		</div>
		
		<div>&nbsp;</div>
		<div id="captchaDiv"><label>Image*:</label><img src="Captcha.jpg"/></div>
		<div>&nbsp;</div>
		
	</fieldset>
	</form>

		<div id="regStatus" style="text-align:center;display:none;">
			Processing...<br/><img src="images/ajax-bar-loader.gif"/>
		</div>
		
	

<%
// out.println(session.getAttribute(
// nl.captcha.servlet.Constants.SIMPLE_CAPCHA_SESSION_KEY));
%>

		</td>
	</tr>
	
<%-- Begin conditional (if registration successful) display of login elements 
     (currently disabled)
--%>

	<tr>
		<td id="loginMsg" </td>
	</tr>

<%--  Enable below if login is desired after registration:	
	<tr>
		<td>
		
	<div id="loginElements">
		<form name="loginForm" action="login.do" method="GET">
		<fieldset>
			<legend>Login</legend>
			<br/><div id="loginMsg" class="mmsg"</div><br/>
			<div class="h">Login:</div>
			<div class="r"><label>Username:</label> <input type="text" name="username" id="username"/></div>
			<div class="r"><label>Password:</label> <input type="password" name="password" id="password"/></div>
			<div class="rb" style="text-align:center">
				<input style="" type="submit" id="submitButton" value="login"/>
			</div>
			</form>
			<br/>
		</fieldset>
		</form>
	</div>
	
		</td>
	</tr>
--%>
	
</table>

<%-- Enable if login above is enabled:
<script type="text/javascript">
// Hide login section with prototype
$('loginElements').hide(); 
</script>
--%>

</body>

