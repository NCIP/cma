
<%@ page import="gov.nih.nci.cma.domain.ClinicalColsNewDev" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ClinicalColsNewDev List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ClinicalColsNewDev</g:link></span>
        </div>
        <div class="body">
            <h1>ClinicalColsNewDev List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="accessCode" title="Access Code" />
                        
                   	        <g:sortableColumn property="colName" title="Col Name" />
                        
                   	        <g:sortableColumn property="colNameId" title="Col Name Id" />
                        
                   	        <g:sortableColumn property="colType" title="Col Type" />
                        
                   	        <g:sortableColumn property="dataSource" title="Data Source" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${clinicalColsNewDevList}" status="i" var="clinicalColsNewDev">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${clinicalColsNewDev.id}">${fieldValue(bean:clinicalColsNewDev, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:clinicalColsNewDev, field:'accessCode')}</td>
                        
                            <td>${fieldValue(bean:clinicalColsNewDev, field:'colName')}</td>
                        
                            <td>${fieldValue(bean:clinicalColsNewDev, field:'colNameId')}</td>
                        
                            <td>${fieldValue(bean:clinicalColsNewDev, field:'colType')}</td>
                        
                            <td>${fieldValue(bean:clinicalColsNewDev, field:'dataSource')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ClinicalColsNewDev.count()}" />
            </div>
        </div>
    </body>
</html>
