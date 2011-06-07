
<%@ page import="licenseyourself.LicenseUsage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'licenseUsage.label', default: 'LicenseUsage')}" />
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
                            <g:sortableColumn property="license" title="${message(code: 'licenseUsage.product.label', default: 'Product')}" />
                            
                            <g:sortableColumn property="userid" title="${message(code: 'licenseUsage.userid.label', default: 'User')}" />
                        
                            <th><g:message code="licenseUsage.licenseOwner.label" default="License Owner" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${licenseUsageInstanceList}" status="i" var="licenseUsageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                            	<g:link action="show" id="${licenseUsageInstance.id}">
	                            	${fieldValue(bean: licenseUsageInstance, field: "license.product.name")}
                            	</g:link>
                            </td>
                        
                            <td>
                           		${fieldValue(bean: licenseUsageInstance, field: "user.name")}
                            </td>
                        
                            <td>${fieldValue(bean: licenseUsageInstance, field: "license.owner.name")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${licenseUsageInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
