

<%@ page import="licenseyourself.License" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'license.label', default: 'License')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${licenseInstance}">
            <div class="errors">
                <g:renderErrors bean="${licenseInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save"  enctype="multipart/form-data">
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="product"><g:message code="license.product.label" default="Product" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'product', 'errors')}">
                                    <g:select name="product.id" from="${licenseyourself.Product.list()}" optionKey="id" value="${licenseInstance?.product?.id}"  />
                                </td>
                            </tr>

                         	<tr class="prop">
                                <td valign="top" class="name">
                                    <label for="owner"><g:message code="license.owner.label" default="Owner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'owner', 'errors')}">
                                    <g:select name="owner.id" from="${licenseyourself.Department.list()}" optionKey="id" value="${licenseInstance?.owner?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="allowedConcurrent"><g:message code="license.allowedConcurrent.label" default="Allowed Concurrent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'allowedConcurrent', 'errors')}">
                                    <g:textField name="allowedConcurrent" value="${fieldValue(bean: licenseInstance, field: 'allowedConcurrent')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="allowedInstallations"><g:message code="license.allowedInstallations.label" default="Allowed Installations" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'allowedInstallations', 'errors')}">
                                    <g:textField name="allowedInstallations" value="${fieldValue(bean: licenseInstance, field: 'allowedInstallations')}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="subscription"><g:message code="license.subscription.label" default="Subscription" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'subscription', 'errors')}">
                                    <g:checkBox name="subscription" value="${licenseInstance?.subscription}" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="comment"><g:message code="license.comment.label" default="Comment" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'comment', 'errors')}">
                                    <g:textField name="comment" value="${licenseInstance?.comment}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="licenseKey"><g:message code="license.licenseKey.label" default="License Key" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'licenseKey', 'errors')}">
                                    <g:textField name="licenseKey" value="${licenseInstance?.licenseKey}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="licenseFile"><g:message code="license.licenseFile.label" default="License File" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: licenseInstance, field: 'licenseFile', 'errors')}">
                                    <input type="file" id="licenseFile" name="licenseFile" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
