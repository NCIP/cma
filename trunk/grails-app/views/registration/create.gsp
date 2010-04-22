<content tag="tabs">&nbsp;</content>
<content tag="side">&nbsp;</content> 

<html>
    <head>
		<meta name="layout" content="splashLayout" />
			
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/RegHelper.js'> </script>
		
		<!-- These are already defined in the SplashLayout...Can we remove these? -->
		<g:javascript src="Reg.js" />
		<g:javascript src="fat.js" />
		
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
				border-top:1px solid #2B2D51;
				-moz-border-radius: 0px;
			}
			input#userName, input#password	{
				width:140px;
			}
			#regTable div.r, #regTable div.rb	{
				border-left: 2px solid #2B2D51;
				border-right: 2px solid #2B2D51;
				padding:5px;
			}
			#regTable div.rb	{
				border-bottom: 2px solid #2B2D51;
				margin-bottom:10px;
			}
			#regTable div.h, legend {
				color: #FFF;
				background-color:#2B2D51; 
				font-weight:bold;
				padding:5px; 
			}
		</style>
	</head>
	<body>
	
    	<h1>Create Registration</h1>

        <g:form action="register" method="post" >        
			<fieldset>
				<legend>New Users</legend>
		    	
		        <g:if test="${flash.message}">
		            <div class="message">${flash.message}</div>
		        </g:if>
		        
		        <g:hasErrors bean="${registration}">
		            <div class="errors">
		                <g:renderErrors bean="${registration}" as="list" />
		            </div>
		        </g:hasErrors>
		        
				<br clear="both"/>
				<b>Register for an account to gain instant access to public data</b><br/>
				
				<table cols="4">
					<tbody>
                            <tr class="h">
                                <td valign="top">
                                    <br/><h3>Name:</h3><br/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="firstName">First Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'firstName','errors')}">
                                    <input type="text" id="firstName" name="firstName" value="${fieldValue(bean:registration,field:'firstName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="r">
                                <td valign="top">
                                    <label for="lastName">Last Name*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'lastName','errors')}">
                                    <input type="text" id="lastName" name="lastName" value="${fieldValue(bean:registration,field:'lastName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="h">
                                <td valign="top">
                                    <br/><h3>Contexts:</h3><br/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="context">Contexts*:</label>
                                </td>
								<td valign="top" class="value ${hasErrors(bean:registration,field:'contexts','errors')}">
									<div class="value ${hasErrors(bean:registration,field:'contexts','errors')}">
				  						<g:if test="${registration == null}" >
											<g:each in="${grailsApplication.config.cma.dataContexts}" var="context">
												<g:checkBox name="contexts" value="${context.key}" checked="false"/>${context.key}&nbsp;&nbsp;&nbsp;&nbsp;
											</g:each>
				  						</g:if>
				  						<g:elseif test="${registration.contexts == null}" >
											<g:each in="${grailsApplication.config.cma.dataContexts}" var="context">
												<g:checkBox name="contexts" value="${context.key}" checked="false"/>${context.key}&nbsp;&nbsp;&nbsp;&nbsp;
											</g:each>
				  						</g:elseif>
										<g:else>
											<g:each in="${grailsApplication.config.cma.dataContexts}" var="context">
												<g:set var="isSelected" value="${false}"/>
												<g:each in="${selectedContextList}" var="listItem">
													<g:if test="${listItem.trim() == context.key.trim()}">
														<g:set var="isSelected" value="${true}"/>
													</g:if>
												</g:each>
									  			<g:if test="${isSelected}">				
													<g:checkBox name="contexts" value="${context}" checked="true"/>${context.key}&nbsp;&nbsp;&nbsp;&nbsp;
												</g:if>
												<g:else>
													<g:checkBox name="contexts" value="${context}" checked="false"/>${context.key}&nbsp;&nbsp;&nbsp;&nbsp;
												</g:else>
											</g:each>
										</g:else>
									</div>		
								</td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label>-</label>
                                </td>
                                <td valign="top" colspan="2">
                                    Select ALL contexts for which you are requesting access<br/>
                                </td>
                            </tr> 
                        
                            <tr class="h">
                                <td valign="top">
                                    <br/><h3>Contact Information:</h3><br/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="email">Email*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'email','errors')}">
                                    <input type="text" id="email" name="email" value="${fieldValue(bean:registration,field:'email')}"/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label>-</label>
                                </td>
                                <td valign="top" colspan="2">
                                    Your account information will be sent to this address<br/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="phone">Phone*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'phone','errors')}">
                                    <input type="text" id="phone" name="phone" value="${fieldValue(bean:registration,field:'phone')}"/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="institution">Institution*:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'institution','errors')}">
                                    <input type="text" id="institution" name="institution" value="${fieldValue(bean:registration,field:'institution')}"/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label for="department">Department:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'department','errors')}">
                                    <input type="text" id="department" name="department" value="${fieldValue(bean:registration,field:'department')}"/>
                                </td>
                            </tr> 
                        
                        
                            <tr class="h">
                                <td valign="top">
                                    <br/><h3>Verification:</h3><br/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label>-</label>
                                </td>
                                <td valign="top" colspan="2">
                                    Please type the text that is displayed in the image below
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:registration,field:'verificationCode','errors')}">
                                    <input type="text" id="verificationCode" name="verificationCode" value="${fieldValue(bean:registration,field:'verificationCode')}"/>
                                </td>
                            </tr> 
                            <tr class="r">
                                <td valign="top">
                                    <label>Image*:</label>
                                </td>
                                <td valign="top">
                                    <img id="capImg" src="Captcha.jpg"/>
                                    <a href="#" onclick="$('capImg').src = 'Captcha.jpg?'+Math.floor(Math.random()*9999)+'c';return false;">[refresh image]</a>
                                </td>
                            </tr> 
					</tbody>
				</table>
				
			
				<div>&nbsp;</div>
				<div style="text-align:center" id="regButtons">
					<input type="reset" value="Reset"/>
					<input type="submit" value="Register" />
					<!--<input type="submit" value="Cancel" />-->
				</div>			
			</fieldset>
        </g:form>
		 
	</body>
</html>	
