
<%@ page import="gov.nih.nci.cma.domain.IdMapping" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create IdMapping</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">IdMapping List</g:link></span>
        </div>
        <div class="body">
            <h1>Create IdMapping</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${idMapping}">
            <div class="errors">
                <g:renderErrors bean="${idMapping}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="aliquotbarcode">Aliquotbarcode:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'aliquotbarcode','errors')}">
                                    <input type="text" id="aliquotbarcode" name="aliquotbarcode" value="${fieldValue(bean:idMapping,field:'aliquotbarcode')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="analysisFileId">Analysis File Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'analysisFileId','errors')}">
                                    <input type="text" id="analysisFileId" name="analysisFileId" value="${fieldValue(bean:idMapping,field:'analysisFileId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="filename">Filename:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'filename','errors')}">
                                    <input type="text" id="filename" name="filename" value="${fieldValue(bean:idMapping,field:'filename')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mappingId">Mapping Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'mappingId','errors')}">
                                    <input type="text" id="mappingId" name="mappingId" value="${fieldValue(bean:idMapping,field:'mappingId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="platform">Platform:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'platform','errors')}">
                                    <input type="text" id="platform" name="platform" value="${fieldValue(bean:idMapping,field:'platform')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ptid">Ptid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'ptid','errors')}">
                                    <input type="text" id="ptid" name="ptid" value="${fieldValue(bean:idMapping,field:'ptid')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="sampleId">Sample Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'sampleId','errors')}">
                                    <input type="text" id="sampleId" name="sampleId" value="${fieldValue(bean:idMapping,field:'sampleId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tissue">Tissue:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:idMapping,field:'tissue','errors')}">
                                    <input type="text" id="tissue" name="tissue" value="${fieldValue(bean:idMapping,field:'tissue')}"/>
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
