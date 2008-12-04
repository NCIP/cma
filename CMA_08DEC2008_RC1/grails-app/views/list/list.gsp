
<%@ page import="gov.nih.nci.cma.domain.List" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>List List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New List</g:link></span>
        </div>
        <div class="body">
            <h1>List List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="author" title="Author" />
                        
                   	        <g:sortableColumn property="category" title="Category" />
                        
                   	        <g:sortableColumn property="creationDate" title="Creation Date" />
                        
                   	        <g:sortableColumn property="description" title="Description" />
                        
                   	        <g:sortableColumn property="institution" title="Institution" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${listList}" status="i" var="list">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${list.id}">${fieldValue(bean:list, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:list, field:'author')}</td>
                        
                            <td>${fieldValue(bean:list, field:'category')}</td>
                        
                            <td>${fieldValue(bean:list, field:'creationDate')}</td>
                        
                            <td>${fieldValue(bean:list, field:'description')}</td>
                        
                            <td>${fieldValue(bean:list, field:'institution')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${List.count()}" />
            </div>
        </div>
    </body>
</html>
