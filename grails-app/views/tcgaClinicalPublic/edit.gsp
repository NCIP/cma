
<%@ page import="gov.nih.nci.cma.domain.TcgaClinicalPublic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit TcgaClinicalPublic</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TcgaClinicalPublic List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New TcgaClinicalPublic</g:link></span>
        </div>
        <div class="body">
            <h1>Edit TcgaClinicalPublic</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tcgaClinicalPublic}">
            <div class="errors">
                <g:renderErrors bean="${tcgaClinicalPublic}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${tcgaClinicalPublic?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="patientId">Patient Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalPublic,field:'patientId','errors')}">
                                    <input type="text" id="patientId" name="patientId" value="${fieldValue(bean:tcgaClinicalPublic,field:'patientId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ptid">Ptid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalPublic,field:'ptid','errors')}">
                                    <input type="text" id="ptid" name="ptid" value="${fieldValue(bean:tcgaClinicalPublic,field:'ptid')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tumorTissueSite">Tumor Tissue Site:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalPublic,field:'tumorTissueSite','errors')}">
                                    <input type="text" id="tumorTissueSite" name="tumorTissueSite" value="${fieldValue(bean:tcgaClinicalPublic,field:'tumorTissueSite')}"/>
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
