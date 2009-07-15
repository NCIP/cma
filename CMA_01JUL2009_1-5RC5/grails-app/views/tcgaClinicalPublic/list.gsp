
<%@ page import="gov.nih.nci.cma.domain.TcgaClinicalPublic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>TcgaClinicalPublic List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New TcgaClinicalPublic</g:link></span>
        </div>
        <div class="body">
            <h1>TcgaClinicalPublic List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="patientId" title="Patient Id" />
                        
                   	        <g:sortableColumn property="ptid" title="Ptid" />
                        
                   	        <g:sortableColumn property="tumorTissueSite" title="Tumor Tissue Site" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tcgaClinicalPublicList}" status="i" var="tcgaClinicalPublic">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tcgaClinicalPublic.id}">${fieldValue(bean:tcgaClinicalPublic, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:tcgaClinicalPublic, field:'patientId')}</td>
                        
                            <td>${fieldValue(bean:tcgaClinicalPublic, field:'ptid')}</td>
                        
                            <td>${fieldValue(bean:tcgaClinicalPublic, field:'tumorTissueSite')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${TcgaClinicalPublic.count()}" />
            </div>
        </div>
    </body>
</html>
