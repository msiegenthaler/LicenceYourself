
<%@ page import="licenseyourself.License" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'license.label', default: 'License')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <sec:ifAllGranted roles="ROLE_MANAGER">
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
            </sec:ifAllGranted>
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
                            <g:sortableColumn property="product" title="${message(code: 'license.product.label', default: 'Product')}" />
                            <g:sortableColumn property="owner" title="${message(code: 'license.owner.label', default: 'Owner')}" />
                            <g:sortableColumn property="allowedConcurrent" title="${message(code: 'license.allowedConcurrent.label', default: 'Allowed Concurrent')}" />
                            <g:sortableColumn property="allowedInstallations" title="${message(code: 'license.allowedInstallations.label', default: 'Allowed Installations')}" />
                            <g:sortableColumn property="comment" title="${message(code: 'license.comment.label', default: 'Comment')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${licenseInstanceList}" status="i" var="licenseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                            	<g:link action="show" id="${licenseInstance.id}">${fieldValue(bean: licenseInstance, field: "product.name")}</g:link>
                            </td>
                            <td>${fieldValue(bean: licenseInstance, field: "owner.name")}</td>
                            <td>
                            	${fieldValue(bean: licenseInstance, field: "allowedConcurrent")}
                            </td>
                            <td>${fieldValue(bean: licenseInstance, field: "allowedInstallations")}</td>
                            <td>${fieldValue(bean: licenseInstance, field: "comment")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${licenseInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
