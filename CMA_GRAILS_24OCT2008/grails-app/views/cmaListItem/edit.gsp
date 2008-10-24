
<%@ page import="gov.nih.nci.cma.domain.CmaListItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit CmaListItem</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CmaListItem List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaListItem</g:link></span>
        </div>
        <div class="body">
            <h1>Edit CmaListItem</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmaListItem}">
            <div class="errors">
                <g:renderErrors bean="${cmaListItem}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${cmaListItem?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cmaList">Cma List:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'cmaList','errors')}">
                                    <g:select optionKey="id" from="${gov.nih.nci.cma.domain.CmaList.list()}" name="cmaList.id" value="${cmaListItem?.cmaList?.id}" ></g:select>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemDescription">Item Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'itemDescription','errors')}">
                                    <input type="text" id="itemDescription" name="itemDescription" value="${fieldValue(bean:cmaListItem,field:'itemDescription')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemId">Item Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'itemId','errors')}">
                                    <input type="text" id="itemId" name="itemId" value="${fieldValue(bean:cmaListItem,field:'itemId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="itemName">Item Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'itemName','errors')}">
                                    <input type="text" id="itemName" name="itemName" value="${fieldValue(bean:cmaListItem,field:'itemName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="listName">List Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'listName','errors')}">
                                    <input type="text" id="listName" name="listName" value="${fieldValue(bean:cmaListItem,field:'listName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="rank">Rank:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaListItem,field:'rank','errors')}">
                                    <input type="text" id="rank" name="rank" value="${fieldValue(bean:cmaListItem,field:'rank')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
