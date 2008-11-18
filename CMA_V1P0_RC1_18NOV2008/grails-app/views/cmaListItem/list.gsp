
<%@ page import="gov.nih.nci.cma.domain.CmaListItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>CmaListItem List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaListItem</g:link></span>
        </div>
        <div class="body">
            <h1>CmaListItem List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <th>Cma List</th>
                   	    
                   	        <g:sortableColumn property="itemDescription" title="Item Description" />
                        
                   	        <g:sortableColumn property="itemId" title="Item Id" />
                        
                   	        <g:sortableColumn property="itemName" title="Item Name" />
                        
                   	        <g:sortableColumn property="listName" title="List Name" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${cmaListItemList}" status="i" var="cmaListItem">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${cmaListItem.id}">${fieldValue(bean:cmaListItem, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:cmaListItem, field:'cmaList')}</td>
                        
                            <td>${fieldValue(bean:cmaListItem, field:'itemDescription')}</td>
                        
                            <td>${fieldValue(bean:cmaListItem, field:'itemId')}</td>
                        
                            <td>${fieldValue(bean:cmaListItem, field:'itemName')}</td>
                        
                            <td>${fieldValue(bean:cmaListItem, field:'listName')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${CmaListItem.count()}" />
            </div>
        </div>
    </body>
</html>
