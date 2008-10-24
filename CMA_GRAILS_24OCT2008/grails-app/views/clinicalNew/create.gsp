
<%@ page import="gov.nih.nci.cma.domain.ClinicalNew" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create ClinicalNew</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">ClinicalNew List</g:link></span>
        </div>
        <div class="body">
            <h1>Create ClinicalNew</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${clinicalNew}">
            <div class="errors">
                <g:renderErrors bean="${clinicalNew}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cfId">Cf Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'cfId','errors')}">
                                    <input type="text" id="cfId" name="cfId" value="${fieldValue(bean:clinicalNew,field:'cfId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dob">Dob:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'dob','errors')}">
                                    <input type="text" id="dob" name="dob" value="${fieldValue(bean:clinicalNew,field:'dob')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dod">Dod:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'dod','errors')}">
                                    <input type="text" id="dod" name="dod" value="${fieldValue(bean:clinicalNew,field:'dod')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dodMinusDop">Dod Minus Dop:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'dodMinusDop','errors')}">
                                    <input type="text" id="dodMinusDop" name="dodMinusDop" value="${fieldValue(bean:clinicalNew,field:'dodMinusDop')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dodfuMinusDop">Dodfu Minus Dop:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'dodfuMinusDop','errors')}">
                                    <input type="text" id="dodfuMinusDop" name="dodfuMinusDop" value="${fieldValue(bean:clinicalNew,field:'dodfuMinusDop')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstExam">First Exam:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'firstExam','errors')}">
                                    <input type="text" id="firstExam" name="firstExam" value="${fieldValue(bean:clinicalNew,field:'firstExam')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstProcedure">First Procedure:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'firstProcedure','errors')}">
                                    <input type="text" id="firstProcedure" name="firstProcedure" value="${fieldValue(bean:clinicalNew,field:'firstProcedure')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstRadiation">First Radiation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'firstRadiation','errors')}">
                                    <input type="text" id="firstRadiation" name="firstRadiation" value="${fieldValue(bean:clinicalNew,field:'firstRadiation')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="gender">Gender:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'gender','errors')}">
                                    <input type="text" id="gender" name="gender" value="${fieldValue(bean:clinicalNew,field:'gender')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="informedConsentAcquired">Informed Consent Acquired:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'informedConsentAcquired','errors')}">
                                    <input type="text" id="informedConsentAcquired" name="informedConsentAcquired" value="${fieldValue(bean:clinicalNew,field:'informedConsentAcquired')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="karnofskyScore">Karnofsky Score:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'karnofskyScore','errors')}">
                                    <input type="text" id="karnofskyScore" name="karnofskyScore" value="${fieldValue(bean:clinicalNew,field:'karnofskyScore')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastFollowUp">Last Follow Up:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'lastFollowUp','errors')}">
                                    <input type="text" id="lastFollowUp" name="lastFollowUp" value="${fieldValue(bean:clinicalNew,field:'lastFollowUp')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="patientId">Patient Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'patientId','errors')}">
                                    <input type="text" id="patientId" name="patientId" value="${fieldValue(bean:clinicalNew,field:'patientId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ptid">Ptid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'ptid','errors')}">
                                    <input type="text" id="ptid" name="ptid" value="${fieldValue(bean:clinicalNew,field:'ptid')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tumorTissueSite">Tumor Tissue Site:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'tumorTissueSite','errors')}">
                                    <input type="text" id="tumorTissueSite" name="tumorTissueSite" value="${fieldValue(bean:clinicalNew,field:'tumorTissueSite')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vitalStatus">Vital Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:clinicalNew,field:'vitalStatus','errors')}">
                                    <input type="text" id="vitalStatus" name="vitalStatus" value="${fieldValue(bean:clinicalNew,field:'vitalStatus')}"/>
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
