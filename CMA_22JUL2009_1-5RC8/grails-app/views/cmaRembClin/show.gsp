
<%@ page import="gov.nih.nci.cma.domain.CmaRembClin" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show CmaRembClin</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CmaRembClin List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaRembClin</g:link></span>
        </div>
        <div class="body">
            <h1>Show CmaRembClin</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Parm:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'parm')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Parm Char Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'parmCharValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Parm Num Value:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'parmNumValue')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Rep Num:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'repNum')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Sample Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:cmaRembClin, field:'sampleId')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${cmaRembClin?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
