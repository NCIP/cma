
<%@ page import="gov.nih.nci.cma.domain.Platform" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Platform List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New Platform</g:link></span>
        </div>
        <div class="body">
            <h1>Platform List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="annotationFileName" title="Annotation File Name" />
                        
                   	        <g:sortableColumn property="arrayDesign" title="Array Design" />
                        
                   	        <g:sortableColumn property="center" title="Center" />
                        
                   	        <g:sortableColumn property="dataType" title="Data Type" />
                        
                   	        <g:sortableColumn property="displayString" title="Display String" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${platformList}" status="i" var="platform">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${platform.id}">${fieldValue(bean:platform, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:platform, field:'annotationFileName')}</td>
                        
                            <td>${fieldValue(bean:platform, field:'arrayDesign')}</td>
                        
                            <td>${fieldValue(bean:platform, field:'center')}</td>
                        
                            <td>${fieldValue(bean:platform, field:'dataType')}</td>
                        
                            <td>${fieldValue(bean:platform, field:'displayString')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${Platform.count()}" />
            </div>
        </div>
    </body>
</html>
