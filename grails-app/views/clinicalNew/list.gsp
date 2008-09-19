
<%@ page import="gov.nih.nci.cma.domain.ClinicalNew" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>ClinicalNew List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New ClinicalNew</g:link></span>
        </div>
        <div class="body">
            <h1>ClinicalNew List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="cfId" title="Cf Id" />
                        
                   	        <g:sortableColumn property="dob" title="Dob" />
                        
                   	        <g:sortableColumn property="dod" title="Dod" />
                        
                   	        <g:sortableColumn property="dodMinusDop" title="Dod Minus Dop" />
                        
                   	        <g:sortableColumn property="dodfuMinusDop" title="Dodfu Minus Dop" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${clinicalNewList}" status="i" var="clinicalNew">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${clinicalNew.id}">${fieldValue(bean:clinicalNew, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:clinicalNew, field:'cfId')}</td>
                        
                            <td>${fieldValue(bean:clinicalNew, field:'dob')}</td>
                        
                            <td>${fieldValue(bean:clinicalNew, field:'dod')}</td>
                        
                            <td>${fieldValue(bean:clinicalNew, field:'dodMinusDop')}</td>
                        
                            <td>${fieldValue(bean:clinicalNew, field:'dodfuMinusDop')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${ClinicalNew.count()}" />
            </div>
        </div>
    </body>
</html>
