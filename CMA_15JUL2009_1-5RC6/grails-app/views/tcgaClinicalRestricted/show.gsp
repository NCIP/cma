
<%@ page import="gov.nih.nci.cma.domain.TcgaClinicalRestricted" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Show TcgaClinicalRestricted</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TcgaClinicalRestricted List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New TcgaClinicalRestricted</g:link></span>
        </div>
        <div class="body">
            <h1>Show TcgaClinicalRestricted</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>

                    
                        <tr class="prop">
                            <td valign="top" class="name">Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Cf Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'cfId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dob:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'dob')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dod:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'dod')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dod Minus Dop:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'dodMinusDop')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Dodfu Minus Dop:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'dodfuMinusDop')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Exam:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'firstExam')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Procedure:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'firstProcedure')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">First Radiation:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'firstRadiation')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Gender:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'gender')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Informed Consent Acquired:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'informedConsentAcquired')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Karnofsky Score:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'karnofskyScore')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Last Follow Up:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'lastFollowUp')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Patient Id:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'patientId')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Ptid:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'ptid')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Tumor Tissue Site:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'tumorTissueSite')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name">Vital Status:</td>
                            
                            <td valign="top" class="value">${fieldValue(bean:tcgaClinicalRestricted, field:'vitalStatus')}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${tcgaClinicalRestricted?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="Edit" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
