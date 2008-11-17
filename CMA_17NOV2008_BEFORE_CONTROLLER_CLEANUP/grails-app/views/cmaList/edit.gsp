
<%@ page import="gov.nih.nci.cma.domain.CmaList" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit CmaList</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CmaList List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaList</g:link></span>
        </div>
        <div class="body">
            <h1>Edit CmaList</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmaList}">
            <div class="errors">
                <g:renderErrors bean="${cmaList}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${cmaList?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="author">Author:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'author','errors')}">
                                    <input type="text" id="author" name="author" value="${fieldValue(bean:cmaList,field:'author')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="category">Category:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'category','errors')}">
                                    <input type="text" id="category" name="category" value="${fieldValue(bean:cmaList,field:'category')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="creationDate">Creation Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'creationDate','errors')}">
                                    <g:datePicker name="creationDate" value="${cmaList?.creationDate}" ></g:datePicker>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:cmaList,field:'description')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="institution">Institution:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'institution','errors')}">
                                    <input type="text" id="institution" name="institution" value="${fieldValue(bean:cmaList,field:'institution')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="listItems">List Items:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'listItems','errors')}">
                                    
<ul>
<g:each var="l" in="${cmaList?.listItems?}">
    <li><g:link controller="cmaListItem" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
</ul>
<g:link controller="cmaListItem" params="['cmaList.id':cmaList?.id]" action="create">Add CmaListItem</g:link>

                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name">Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'name','errors')}">
                                    <input type="text" id="name" name="name" value="${fieldValue(bean:cmaList,field:'name')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="origin">Origin:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'origin','errors')}">
                                    <input type="text" id="origin" name="origin" value="${fieldValue(bean:cmaList,field:'origin')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subtype">Subtype:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'subtype','errors')}">
                                    <input type="text" id="subtype" name="subtype" value="${fieldValue(bean:cmaList,field:'subtype')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="type">Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaList,field:'type','errors')}">
                                    <input type="text" id="type" name="type" value="${fieldValue(bean:cmaList,field:'type')}"/>
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
