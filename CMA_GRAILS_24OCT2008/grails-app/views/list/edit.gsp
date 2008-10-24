
<%@ page import="gov.nih.nci.cma.domain.List" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">List List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New List</g:link></span>
        </div>
        <div class="body">
            <h1>Edit List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${list}">
            <div class="errors">
                <g:renderErrors bean="${list}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${list?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="author">Author:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'author','errors')}">
                                    <input type="text" id="author" name="author" value="${fieldValue(bean:list,field:'author')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="category">Category:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'category','errors')}">
                                    <input type="text" id="category" name="category" value="${fieldValue(bean:list,field:'category')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="creationDate">Creation Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'creationDate','errors')}">
                                    <g:datePicker name="creationDate" value="${list?.creationDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:list,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="institution">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'institution','errors')}">
                                    <input type="text" id="institution" name="institution" value="${fieldValue(bean:list,field:'institution')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="listItems">List Items:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'listItems','errors')}">
                                    
<ul>
<g:each var="l" in="${list?.listItems?}">
    <li><g:link controller="listItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="listItem" params="['list.id':list?.id]" action="create">Add ListItem</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:list,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="origin">Origin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'origin','errors')}">
                                    <input type="text" id="origin" name="origin" value="${fieldValue(bean:list,field:'origin')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subtype">Subtype:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'subtype','errors')}">
                                    <input type="text" id="subtype" name="subtype" value="${fieldValue(bean:list,field:'subtype')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:list,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:list,field:'type')}"/>
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
