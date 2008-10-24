
<%@ page import="gov.nih.nci.cma.domain.ProbesetGeneAsso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ProbesetGeneAsso</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProbesetGeneAsso List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProbesetGeneAsso</g:link></span>
        </div>
        <div class="body">
            <h1>Show ProbesetGeneAsso</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetGeneAsso, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Gene Dim Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetGeneAsso, field:'geneDimId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Reporter Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:probesetGeneAsso, field:'reporterId')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${probesetGeneAsso?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
