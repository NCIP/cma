
<%@ page import="gov.nih.nci.cma.domain.ProbesetGeneAsso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ProbesetGeneAsso</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProbesetGeneAsso List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ProbesetGeneAsso</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${probesetGeneAsso}">
            <div class="errors">
                <g:renderErrors bean="${probesetGeneAsso}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
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
                    <span class="button"><input class="save" type="submit" value="Create" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
