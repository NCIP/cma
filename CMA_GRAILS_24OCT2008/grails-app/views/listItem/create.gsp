
<%@ page import="gov.nih.nci.cma.domain.ListItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ListItem</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ListItem List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ListItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${listItem}">
            <div class="errors">
                <g:renderErrors bean="${listItem}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemDescription">Item Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'itemDescription','errors')}">
                                    <input type="text" id="itemDescription" name="itemDescription" value="${fieldValue(bean:listItem,field:'itemDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemId">Item Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'itemId','errors')}">
                                    <input type="text" id="itemId" name="itemId" value="${fieldValue(bean:listItem,field:'itemId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemName">Item Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'itemName','errors')}">
                                    <input type="text" id="itemName" name="itemName" value="${fieldValue(bean:listItem,field:'itemName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="list">List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'list','errors')}">
                                    <g:select optionKey="id" from="${gov.nih.nci.cma.domain.List.list()}" name="list.id" value="${listItem?.list?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="listName">List Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'listName','errors')}">
                                    <input type="text" id="listName" name="listName" value="${fieldValue(bean:listItem,field:'listName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rank">Rank:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:listItem,field:'rank','errors')}">
                                    <input type="text" id="rank" name="rank" value="${fieldValue(bean:listItem,field:'rank')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
