
<%@ page import="licenseyourself.Product" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <sec:ifAllGranted roles="ROLE_MANAGER,ROLE_ADMIN">
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
                            <g:sortableColumn property="name" title="${message(code: 'product.name.label', default: 'Name')}" />
                            <g:sortableColumn property="description" title="${message(code: 'product.description.label', default: 'Description')}" />
                            <g:sortableColumn property="homepage" title="${message(code: 'product.homepage.label', default: 'Homepage')}" />
                            <th>${message(code: 'product.users.label', default: 'Users')}</th>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${productInstanceList}" status="i" var="productInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td>
                            	<g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "name")}</g:link>
                            </td>
                            <td>${fieldValue(bean: productInstance, field: "description")}</td>
                            <td>${fieldValue(bean: productInstance, field: "homepage")}</td>
                            <td>
                            	${ installed.get(i) } / ${ allowed.get(i) }
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${productInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
