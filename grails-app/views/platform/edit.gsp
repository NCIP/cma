
<%@ page import="gov.nih.nci.cma.domain.Platform" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Platform</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Platform List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Platform</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Platform</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${platform}">
            <div class="errors">
                <g:renderErrors bean="${platform}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${platform?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="annotationFileName">Annotation File Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'annotationFileName','errors')}">
                                    <input type="text" id="annotationFileName" name="annotationFileName" value="${fieldValue(bean:platform,field:'annotationFileName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="arrayDesign">Array Design:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'arrayDesign','errors')}">
                                    <input type="text" id="arrayDesign" name="arrayDesign" value="${fieldValue(bean:platform,field:'arrayDesign')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="center">Center:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'center','errors')}">
                                    <input type="text" id="center" name="center" value="${fieldValue(bean:platform,field:'center')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataType">Data Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'dataType','errors')}">
                                    <input type="text" id="dataType" name="dataType" value="${fieldValue(bean:platform,field:'dataType')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayString">Display String:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'displayString','errors')}">
                                    <input type="text" id="displayString" name="displayString" value="${fieldValue(bean:platform,field:'displayString')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fileName">File Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'fileName','errors')}">
                                    <input type="text" id="fileName" name="fileName" value="${fieldValue(bean:platform,field:'fileName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="platformId">Platform Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'platformId','errors')}">
                                    <input type="text" id="platformId" name="platformId" value="${fieldValue(bean:platform,field:'platformId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="platformName">Platform Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:platform,field:'platformName','errors')}">
                                    <input type="text" id="platformName" name="platformName" value="${fieldValue(bean:platform,field:'platformName')}"/>
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
