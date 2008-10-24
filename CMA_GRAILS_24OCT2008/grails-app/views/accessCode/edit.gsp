
<%@ page import="gov.nih.nci.cma.domain.AccessCode" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit AccessCode</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">AccessCode List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New AccessCode</g:link></span>
        </div>
        <div class="body">
            <h1>Edit AccessCode</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${accessCode}">
            <div class="errors">
                <g:renderErrors bean="${accessCode}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${accessCode?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accessCode">Access Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accessCode,field:'accessCode','errors')}">
                                    <input type="text" id="accessCode" name="accessCode" value="${fieldValue(bean:accessCode,field:'accessCode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description">Description:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:accessCode,field:'description','errors')}">
                                    <input type="text" id="description" name="description" value="${fieldValue(bean:accessCode,field:'description')}"/>
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
