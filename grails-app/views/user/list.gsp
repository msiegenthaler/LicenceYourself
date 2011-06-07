<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
		</div>
		<div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <div class="list">
            	<table>
            	<thead>
            	<tr>
            		<th>${message(code: 'user.userid.label', default: 'User-Id')}</th>
            		<th>${message(code: 'user.email.label', default: 'EMail')}</th>
            		<th>${message(code: 'user.products', default: 'Licensed')}</th>
            	</tr>
            	</thead>
            	<tbody>
				<g:each in="${users}" var="c">
				<tr>
				    <td><g:link action="show" id="${c.userid}">${c.userid?.encodeAsHTML()}</g:link></td>
				    <td>${c.email?.encodeAsHTML()}</td>
				    <td>${c.products.join(', ')}</td>
				</tr>
				</g:each>
				</tbody>
				</table>
			</div>
		</div>
</body>
</html>