
<%@ page import="gov.nih.nci.cma.domain.ClinicalColsNewDev" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show ClinicalColsNewDev</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ClinicalColsNewDev List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New ClinicalColsNewDev</g:link></span>
        </div>
        <div class="body">
            <h1>Show ClinicalColsNewDev</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Access Code:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'accessCode')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Col Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'colName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Col Name Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'colNameId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Col Type:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'colType')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Data Source:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'dataSource')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Display Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'displayName')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Table Name:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:clinicalColsNewDev, field:'tableName')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${clinicalColsNewDev?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
