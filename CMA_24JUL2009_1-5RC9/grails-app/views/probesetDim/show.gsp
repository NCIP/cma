
<%@ page import="gov.nih.nci.cma.domain.ProbesetDim" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProbesetDim</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProbesetDim List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProbesetDim</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProbesetDim</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetDim, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Annotation Date:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetDim, field:'annotationDate')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Genechip Array:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetDim, field:'genechipArray')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Probe Set Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetDim, field:'probeSetId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Reporter Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetDim, field:'reporterId')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${probesetDim?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
