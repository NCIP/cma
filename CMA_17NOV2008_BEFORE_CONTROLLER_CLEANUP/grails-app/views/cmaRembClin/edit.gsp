
<%@ page import="gov.nih.nci.cma.domain.CmaRembClin" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit CmaRembClin</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">CmaRembClin List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New CmaRembClin</g:link></span>
        </div>
        <div class="body">
            <h1>Edit CmaRembClin</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${cmaRembClin}">
            <div class="errors">
                <g:renderErrors bean="${cmaRembClin}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${cmaRembClin?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parm">Parm:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaRembClin,field:'parm','errors')}">
                                    <input type="text" id="parm" name="parm" value="${fieldValue(bean:cmaRembClin,field:'parm')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parmCharValue">Parm Char Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaRembClin,field:'parmCharValue','errors')}">
                                    <input type="text" id="parmCharValue" name="parmCharValue" value="${fieldValue(bean:cmaRembClin,field:'parmCharValue')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="parmNumValue">Parm Num Value:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaRembClin,field:'parmNumValue','errors')}">
                                    <input type="text" id="parmNumValue" name="parmNumValue" value="${fieldValue(bean:cmaRembClin,field:'parmNumValue')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="repNum">Rep Num:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaRembClin,field:'repNum','errors')}">
                                    <input type="text" id="repNum" name="repNum" value="${fieldValue(bean:cmaRembClin,field:'repNum')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sampleId">Sample Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:cmaRembClin,field:'sampleId','errors')}">
                                    <input type="text" id="sampleId" name="sampleId" value="${fieldValue(bean:cmaRembClin,field:'sampleId')}"/>
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
