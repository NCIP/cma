
<%@ page import="gov.nih.nci.cma.domain.TcgaClinicalRestricted" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Create TcgaClinicalRestricted</title>         
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">TcgaClinicalRestricted List</g:link></span>
        </div>
        <div class="body">
            <h1>Create TcgaClinicalRestricted</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tcgaClinicalRestricted}">
            <div class="errors">
                <g:renderErrors bean="${tcgaClinicalRestricted}" as="list" />
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
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'cfId','errors')}">
                                    <input type="text" id="cfId" name="cfId" value="${fieldValue(bean:tcgaClinicalRestricted,field:'cfId')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dob">Dob:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'dob','errors')}">
                                    <input type="text" id="dob" name="dob" value="${fieldValue(bean:tcgaClinicalRestricted,field:'dob')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dod">Dod:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'dod','errors')}">
                                    <input type="text" id="dod" name="dod" value="${fieldValue(bean:tcgaClinicalRestricted,field:'dod')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dodMinusDop">Dod Minus Dop:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'dodMinusDop','errors')}">
                                    <input type="text" id="dodMinusDop" name="dodMinusDop" value="${fieldValue(bean:tcgaClinicalRestricted,field:'dodMinusDop')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="dodfuMinusDop">Dodfu Minus Dop:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'dodfuMinusDop','errors')}">
                                    <input type="text" id="dodfuMinusDop" name="dodfuMinusDop" value="${fieldValue(bean:tcgaClinicalRestricted,field:'dodfuMinusDop')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstExam">First Exam:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'firstExam','errors')}">
                                    <input type="text" id="firstExam" name="firstExam" value="${fieldValue(bean:tcgaClinicalRestricted,field:'firstExam')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstProcedure">First Procedure:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'firstProcedure','errors')}">
                                    <input type="text" id="firstProcedure" name="firstProcedure" value="${fieldValue(bean:tcgaClinicalRestricted,field:'firstProcedure')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="firstRadiation">First Radiation:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'firstRadiation','errors')}">
                                    <input type="text" id="firstRadiation" name="firstRadiation" value="${fieldValue(bean:tcgaClinicalRestricted,field:'firstRadiation')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="gender">Gender:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'gender','errors')}">
                                    <input type="text" id="gender" name="gender" value="${fieldValue(bean:tcgaClinicalRestricted,field:'gender')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="informedConsentAcquired">Informed Consent Acquired:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'informedConsentAcquired','errors')}">
                                    <input type="text" id="informedConsentAcquired" name="informedConsentAcquired" value="${fieldValue(bean:tcgaClinicalRestricted,field:'informedConsentAcquired')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="karnofskyScore">Karnofsky Score:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'karnofskyScore','errors')}">
                                    <input type="text" id="karnofskyScore" name="karnofskyScore" value="${fieldValue(bean:tcgaClinicalRestricted,field:'karnofskyScore')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lastFollowUp">Last Follow Up:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'lastFollowUp','errors')}">
                                    <input type="text" id="lastFollowUp" name="lastFollowUp" value="${fieldValue(bean:tcgaClinicalRestricted,field:'lastFollowUp')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="patientId">Patient Id:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'patientId','errors')}">
                                    <input type="text" id="patientId" name="patientId" value="${fieldValue(bean:tcgaClinicalRestricted,field:'patientId')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ptid">Ptid:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'ptid','errors')}">
                                    <input type="text" id="ptid" name="ptid" value="${fieldValue(bean:tcgaClinicalRestricted,field:'ptid')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tumorTissueSite">Tumor Tissue Site:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'tumorTissueSite','errors')}">
                                    <input type="text" id="tumorTissueSite" name="tumorTissueSite" value="${fieldValue(bean:tcgaClinicalRestricted,field:'tumorTissueSite')}"/>
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="vitalStatus">Vital Status:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:tcgaClinicalRestricted,field:'vitalStatus','errors')}">
                                    <input type="text" id="vitalStatus" name="vitalStatus" value="${fieldValue(bean:tcgaClinicalRestricted,field:'vitalStatus')}"/>
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
