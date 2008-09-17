
<%@ page import="gov.nih.nci.cma.domain.List" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create List</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">List List</g:link></span>
        </div>
        <div class="body">
            <h1>Create List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${list}">
            <div class="errors">
                <g:renderErrors bean="${list}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
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
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
