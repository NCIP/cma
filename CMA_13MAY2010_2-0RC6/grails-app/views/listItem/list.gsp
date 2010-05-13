
<%@ page import="gov.nih.nci.cma.domain.ListItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ListItem List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ListItem</g:link></span>
        </div>
        <div class="body">
            <h1>ListItem List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="itemDescription" title="Item Description" />
                        
                   	        <g:sortableColumn property="itemId" title="Item Id" />
                        
                   	        <g:sortableColumn property="itemName" title="Item Name" />
                        
                   	        <th>List</th>
                   	    
                   	        <g:sortableColumn property="listName" title="List Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${listItemList}" status="i" var="listItem">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${listItem.id}">${fieldValue(bean:listItem, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:listItem, field:'itemDescription')}</td>
                        
                            <td>${fieldValue(bean:listItem, field:'itemId')}</td>
                        
                            <td>${fieldValue(bean:listItem, field:'itemName')}</td>
                        
                            <td>${fieldValue(bean:listItem, field:'list')}</td>
                        
                            <td>${fieldValue(bean:listItem, field:'listName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ListItem.count()}" />
            </div>
        </div>
    </body>
</html>
