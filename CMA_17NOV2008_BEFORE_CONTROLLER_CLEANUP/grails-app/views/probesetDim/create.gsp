
<%@ page import="gov.nih.nci.cma.domain.ProbesetDim" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ProbesetDim</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ProbesetDim List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ProbesetDim</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${probesetDim}">
            <div class="errors">
                <g:renderErrors bean="${probesetDim}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="annotationDate">Annotation Date:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetDim,field:'annotationDate','errors')}">
                                    <input type="text" id="annotationDate" name="annotationDate" value="${fieldValue(bean:probesetDim,field:'annotationDate')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="genechipArray">Genechip Array:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetDim,field:'genechipArray','errors')}">
                                    <input type="text" id="genechipArray" name="genechipArray" value="${fieldValue(bean:probesetDim,field:'genechipArray')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="probeSetId">Probe Set Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetDim,field:'probeSetId','errors')}">
                                    <input type="text" id="probeSetId" name="probeSetId" value="${fieldValue(bean:probesetDim,field:'probeSetId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="reporterId">Reporter Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:probesetDim,field:'reporterId','errors')}">
                                    <input type="text" id="reporterId" name="reporterId" value="${fieldValue(bean:probesetDim,field:'reporterId')}" />
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
