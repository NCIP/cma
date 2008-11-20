
<%@ page import="gov.nih.nci.cma.domain.IdMapping" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show IdMapping</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">IdMapping List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New IdMapping</g:link></span>
        </div>
        <div class="body">
            <h1>Show IdMapping</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Aliquotbarcode:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'aliquotbarcode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Analysis File Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'analysisFileId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Filename:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'filename')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Mapping Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'mappingId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Platform:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'platform')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ptid:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'ptid')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Sample Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'sampleId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tissue:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:idMapping, field:'tissue')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${idMapping?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
