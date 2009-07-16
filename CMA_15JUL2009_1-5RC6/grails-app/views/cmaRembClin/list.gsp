
<%@ page import="gov.nih.nci.cma.domain.CmaRembClin" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>CmaRembClin List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaRembClin</g:link></span>
        </div>
        <div class="body">
            <h1>CmaRembClin List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="parm" title="Parm" />
                        
                   	        <g:sortableColumn property="parmCharValue" title="Parm Char Value" />
                        
                   	        <g:sortableColumn property="parmNumValue" title="Parm Num Value" />
                        
                   	        <g:sortableColumn property="repNum" title="Rep Num" />
                        
                   	        <g:sortableColumn property="sampleId" title="Sample Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cmaRembClinList}" status="i" var="cmaRembClin">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cmaRembClin.id}">${fieldValue(bean:cmaRembClin, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:cmaRembClin, field:'parm')}</td>
                        
                            <td>${fieldValue(bean:cmaRembClin, field:'parmCharValue')}</td>
                        
                            <td>${fieldValue(bean:cmaRembClin, field:'parmNumValue')}</td>
                        
                            <td>${fieldValue(bean:cmaRembClin, field:'repNum')}</td>
                        
                            <td>${fieldValue(bean:cmaRembClin, field:'sampleId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${CmaRembClin.count()}" />
            </div>
        </div>
    </body>
</html>
