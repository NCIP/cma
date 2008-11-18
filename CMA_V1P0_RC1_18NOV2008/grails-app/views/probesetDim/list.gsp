
<%@ page import="gov.nih.nci.cma.domain.ProbesetDim" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ProbesetDim List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ProbesetDim</g:link></span>
        </div>
        <div class="body">
            <h1>ProbesetDim List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="annotationDate" title="Annotation Date" />
                        
                   	        <g:sortableColumn property="genechipArray" title="Genechip Array" />
                        
                   	        <g:sortableColumn property="probeSetId" title="Probe Set Id" />
                        
                   	        <g:sortableColumn property="reporterId" title="Reporter Id" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${probesetDimList}" status="i" var="probesetDim">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${probesetDim.id}">${fieldValue(bean:probesetDim, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:probesetDim, field:'annotationDate')}</td>
                        
                            <td>${fieldValue(bean:probesetDim, field:'genechipArray')}</td>
                        
                            <td>${fieldValue(bean:probesetDim, field:'probeSetId')}</td>
                        
                            <td>${fieldValue(bean:probesetDim, field:'reporterId')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ProbesetDim.count()}" />
            </div>
        </div>
    </body>
</html>
