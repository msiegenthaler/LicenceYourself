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
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <div class="dialog">
            	<ul>
            	<li>UserId: ${ user.userid }</li>
            	<li>EMail: ${ user.email }</li>
            	<li>
            		Products
            		<ul>
            			<g:each in="${ products }" var="c">
            				<li>
            					<g:link controller="product" action="show" id="${c.id}">
            						${ c.name.encodeAsHTML() }
            					</g:link>
            				</li>
            			</g:each>
            		</ul>
            	</li>
            	</ul>
			</div>
		</div>
</body>
</html>