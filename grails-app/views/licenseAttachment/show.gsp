
<%@ page import="licenseyourself.LicenseAttachment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'licenseAttachment.label', default: 'LicenseAttachment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <sec:ifAllGranted roles="ROLE_MANAGER">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            </sec:ifAllGranted>
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
                            <td valign="top" class="name"><g:message code="licenseAttachment.license.label" default="License" /></td>
                            
                            <td valign="top" class="value"><g:link controller="license" action="show" id="${licenseAttachmentInstance?.license?.id}">${licenseAttachmentInstance?.license?.encodeAsHTML()}</g:link></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="licenseAttachment.name.label" default="Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: licenseAttachmentInstance, field: "name")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="licenseAttachment.content.label" default="Content" /></td>
                            
							<td valign="top" class="value">
                           		<g:link action="download" id="${licenseAttachmentInstance?.id}">${licenseAttachmentInstance.file.name?.encodeAsHTML()}</g:link>
                           	</td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            
            <sec:ifAllGranted roles="ROLE_MANAGER">
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${licenseAttachmentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
            </sec:ifAllGranted>
        </div>
    </body>
</html>
