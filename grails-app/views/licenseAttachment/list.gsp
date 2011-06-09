
<%@ page import="licenseyourself.LicenseAttachment" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'licenseAttachment.label', default: 'LicenseAttachment')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <th><g:message code="licenseAttachment.license.label" default="License" /></th>
                            <g:sortableColumn property="name" title="${message(code: 'licenseAttachment.name.label', default: 'Name')}" />
                            <th><g:message code="licenseAttachment.file.label" default="File" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${licenseAttachmentInstanceList}" status="i" var="licenseAttachmentInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean: licenseAttachmentInstance, field: "license")}</td>
                        
                            <td>
                            	<g:link action="show" id="${licenseAttachmentInstance.id}">
                            		${fieldValue(bean: licenseAttachmentInstance, field: "name")}
                            	</g:link>
                            </td>
                            
                            <td>
	                            <g:link action="download" id="${licenseAttachmentInstance?.id}">
	                            	<g:message code="licenseAttachment.file.download" default="download" />
								</g:link>
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${licenseAttachmentInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
