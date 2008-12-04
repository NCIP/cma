
<%@ page import="gov.nih.nci.cma.domain.ProbesetGeneAsso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProbesetGeneAsso List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProbesetGeneAsso</g:link></span>
        </div>
        <div class="body">
            <h1>ProbesetGeneAsso List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="geneDimId" title="Gene Dim Id" />
                        
                   	        <g:sortableColumn property="reporterId" title="Reporter Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${probesetGeneAssoList}" status="i" var="probesetGeneAsso">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${probesetGeneAsso.id}">${fieldValue(bean:probesetGeneAsso, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:probesetGeneAsso, field:'geneDimId')}</td>
                        
                            <td>${fieldValue(bean:probesetGeneAsso, field:'reporterId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProbesetGeneAsso.count()}" />
            </div>
        </div>
    </body>
</html>
