
<%@ page import="gov.nih.nci.cma.domain.ProbesetGeneAsso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ProbesetGeneAsso</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProbesetGeneAsso List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ProbesetGeneAsso</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ProbesetGeneAsso</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${probesetGeneAsso}">
            <div class="errors">
                <g:renderErrors bean="${probesetGeneAsso}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${probesetGeneAsso?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="geneDimId">Gene Dim Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetGeneAsso,field:'geneDimId','errors')}">
                                    <input type="text" id="geneDimId" name="geneDimId" value="${fieldValue(bean:probesetGeneAsso,field:'geneDimId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="reporterId">Reporter Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetGeneAsso,field:'reporterId','errors')}">
                                    <input type="text" id="reporterId" name="reporterId" value="${fieldValue(bean:probesetGeneAsso,field:'reporterId')}" />
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
