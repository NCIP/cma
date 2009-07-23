
<%@ page import="gov.nih.nci.cma.domain.AccessCode" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>AccessCode List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New AccessCode</g:link></span>
        </div>
        <div class="body">
            <h1>AccessCode List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="accessCode" title="Access Code" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${accessCodeList}" status="i" var="accessCode">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${accessCode.id}">${fieldValue(bean:accessCode, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:accessCode, field:'accessCode')}</td>
                        
                            <td>${fieldValue(bean:accessCode, field:'description')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${AccessCode.count()}" />
            </div>
        </div>
    </body>
</html>
