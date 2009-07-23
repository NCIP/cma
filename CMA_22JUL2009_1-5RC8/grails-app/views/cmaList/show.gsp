
<%@ page import="gov.nih.nci.cma.domain.CmaList" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show CmaList</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CmaList List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaList</g:link></span>
        </div>
        <div class="body">
            <h1>Show CmaList</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Author:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'author')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Category:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'category')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Creation Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'creationDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Description:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'description')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Institution:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'institution')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">List Items:</td>
                            
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="l" in="${cmaList.listItems}">
                                    <li><g:link controller="cmaListItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Origin:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'origin')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Subtype:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'subtype')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaList, field:'type')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${cmaList?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
