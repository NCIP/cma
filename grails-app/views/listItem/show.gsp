
<%@ page import="gov.nih.nci.cma.domain.ListItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ListItem</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ListItem List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ListItem</g:link></span>
        </div>
        <div class="body">
            <h1>Show ListItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Item Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'itemDescription')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Item Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'itemId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Item Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'itemName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">List Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'listId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">List Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'listName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Rank:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:listItem, field:'rank')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${listItem?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
