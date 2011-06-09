
<%@ page import="licenseyourself.License" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'license.label', default: 'License')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.product.label" default="Product" /></td>
                            
                            <td valign="top" class="value">
                            	<g:link controller="product" action="show" id="${ licenseInstance.product.id }">
                            		${fieldValue(bean: licenseInstance, field: "product.name")}
                            	</g:link>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.owner.label" default="Owner" /></td>
                            
                            <td valign="top" class="value">
                            	<g:link controller="department" action="show" id="${ licenseInstance.owner.id }">
		                            ${fieldValue(bean: licenseInstance, field: "owner.name")}
		                        </g:link>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.allowedConcurrent.label" default="Allowed Concurrent" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: licenseInstance, field: "allowedConcurrent")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.allowedInstallations.label" default="Allowed Installations" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: licenseInstance, field: "allowedInstallations")}</td>
						</tr>
						                            
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.subscription.label" default="Subscription" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${licenseInstance?.subscription}" /></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.comment.label" default="Comment" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: licenseInstance, field: "comment")}</td>
                        </tr>                    
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.licenseKey.label" default="License Key" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: licenseInstance, field: "licenseKey")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.licenseFile.label" default="License File" /></td>
                            
                            <td valign="top" class="value">
                            <g:if test="${ licenseInstance?.licenseFile }">
                            	<g:link action="downloadLicenseFile" id="${licenseInstance?.id}">${licenseInstance.licenseFile.name?.encodeAsHTML()}</g:link>
							</g:if>
                            </td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.users.label" default="Users" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${licenseInstance.users}" var="u">
                                    <li><g:link controller="licenseUsage" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="license.attachments.label" default="Attachments" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${licenseInstance.attachments}" var="attachment">
                                    <li><g:link controller="licenseAttachment" action="show" id="${attachment.id}">${attachment?.name?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${licenseInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
