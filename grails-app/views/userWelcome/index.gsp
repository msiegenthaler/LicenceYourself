
<%@ page import="licenseyourself.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="userWelcome.label" default="Welcome" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        </div>
        <div class="body">
            <h1><g:message code="userWelcome.label" default="Welcome" /></h1>
            
            Hello: <sec:loggedInUserInfo field="username"/>
        </div>
    </body>
</html>
