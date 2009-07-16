<content tag="tabs"><g:render template="/tabs" model="[location:'clinical']"/></content>
<content tag="side">&nbsp;</content>

<html>
    <head>
		<meta name="layout" content="splashLayout" />
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'gtable.css')}" />
    </head>
    <body>
		<h2>Clinical Report</h2>
        <g:if test="${flash.message}">
        	<div class="message">${flash.message}</div>
        </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        	<g:each in="${cols}">
                   	        	<th>${it}</th>
                   	        </g:each>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${rows}" status="i" var="crow">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        	<g:each in="${crow}" var="ccell">
 								<td>${ccell}</td>
							</g:each>                       	
                         </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
            <div class="paginateButtons">
                <g:paginate total="${rows.count()}" />
            </div>
    </body>
</html>
