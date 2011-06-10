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

                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.name.label" default="Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: user, field: "name")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.userid.label" default="User-ID" /></td>
                            <td valign="top" class="value">${fieldValue(bean: user, field: "userid")}</td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.email.label" default="E-Mail" /></td>
                            <td valign="top" class="value">
                            	<a href="mailto:${ user.email }">
                            		${fieldValue(bean: user, field: "email")}
                            	</a>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.departments.label" default="Member of" /></td>
                            <td valign="top" class="value">
			           			<ul> 
			           			<g:each in="${ departments }" var="d" status="i">
									<li>
										<g:link controller="department" action="show" id="${ d.id }">${ d.name }</g:link>
									</li>
								</g:each>
			           			</ul>
                            </td>
                        </tr>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="user.products.label" default="Licensed" /></td>
                            <td valign="top" class="value">
			            		<ul>
			            			<g:each in="${ products }" var="c">
			            				<li>
			            					<g:link controller="product" action="show" id="${c.id}">
			            						${ c.name.encodeAsHTML() }
			            					</g:link>
			            				</li>
			            			</g:each>
			            		</ul>
							</td>
                        </tr>
                        
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>