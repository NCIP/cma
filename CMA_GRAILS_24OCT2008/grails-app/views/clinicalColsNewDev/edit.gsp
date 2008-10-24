
<%@ page import="gov.nih.nci.cma.domain.ClinicalColsNewDev" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit ClinicalColsNewDev</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ClinicalColsNewDev List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ClinicalColsNewDev</g:link></span>
        </div>
        <div class="body">
            <h1>Edit ClinicalColsNewDev</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${clinicalColsNewDev}">
            <div class="errors">
                <g:renderErrors bean="${clinicalColsNewDev}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${clinicalColsNewDev?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="accessCode">Access Code:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'accessCode','errors')}">
                                    <input type="text" id="accessCode" name="accessCode" value="${fieldValue(bean:clinicalColsNewDev,field:'accessCode')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="colName">Col Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'colName','errors')}">
                                    <input type="text" id="colName" name="colName" value="${fieldValue(bean:clinicalColsNewDev,field:'colName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="colNameId">Col Name Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'colNameId','errors')}">
                                    <input type="text" id="colNameId" name="colNameId" value="${fieldValue(bean:clinicalColsNewDev,field:'colNameId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="colType">Col Type:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'colType','errors')}">
                                    <input type="text" id="colType" name="colType" value="${fieldValue(bean:clinicalColsNewDev,field:'colType')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dataSource">Data Source:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'dataSource','errors')}">
                                    <input type="text" id="dataSource" name="dataSource" value="${fieldValue(bean:clinicalColsNewDev,field:'dataSource')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="displayName">Display Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'displayName','errors')}">
                                    <input type="text" id="displayName" name="displayName" value="${fieldValue(bean:clinicalColsNewDev,field:'displayName')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tableName">Table Name:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalColsNewDev,field:'tableName','errors')}">
                                    <input type="text" id="tableName" name="tableName" value="${fieldValue(bean:clinicalColsNewDev,field:'tableName')}"/>
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
