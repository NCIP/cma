
<%@ page import="gov.nih.nci.cma.domain.IdMapping" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>IdMapping List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New IdMapping</g:link></span>
        </div>
        <div class="body">
            <h1>IdMapping List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="aliquotbarcode" title="Aliquotbarcode" />
                        
                   	        <g:sortableColumn property="analysisFileId" title="Analysis File Id" />
                        
                   	        <g:sortableColumn property="filename" title="Filename" />
                        
                   	        <g:sortableColumn property="mappingId" title="Mapping Id" />
                        
                   	        <g:sortableColumn property="platform" title="Platform" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${idMappingList}" status="i" var="idMapping">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${idMapping.id}">${fieldValue(bean:idMapping, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:idMapping, field:'aliquotbarcode')}</td>
                        
                            <td>${fieldValue(bean:idMapping, field:'analysisFileId')}</td>
                        
                            <td>${fieldValue(bean:idMapping, field:'filename')}</td>
                        
                            <td>${fieldValue(bean:idMapping, field:'mappingId')}</td>
                        
                            <td>${fieldValue(bean:idMapping, field:'platform')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${IdMapping.count()}" />
            </div>
        </div>
    </body>
</html>
