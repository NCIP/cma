
<%@ page import="gov.nih.nci.cma.domain.Platform" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show Platform</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Platform List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Platform</g:link></span>
        </div>
        <div class="body">
            <h1>Show Platform</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Annotation File Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'annotationFileName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Array Design:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'arrayDesign')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Center:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'center')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Data Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'dataType')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display String:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'displayString')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">File Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'fileName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Platform Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'platformId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Platform Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:platform, field:'platformName')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${platform?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
