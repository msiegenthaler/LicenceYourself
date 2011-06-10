

<%@ page import="licenseyourself.Department" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'department.label', default: 'Department')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${departmentInstance}">
            <div class="errors">
                <g:renderErrors bean="${departmentInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="department.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: departmentInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${departmentInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="responsibleId"><g:message code="department.responsibleId.label" default="Responsible Id" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: departmentInstance, field: 'responsibleId', 'errors')}">
                                    <g:textField name="responsibleId" value="${departmentInstance?.responsibleId}" />
                                </td>
                            </tr>
                            
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="product"><g:message code="department.parent.label" default="Parent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: departmentInstance, field: 'parent', 'errors')}">
                                	<%
										def ds = licenseyourself.Department.list()
										ds.remove(departmentInstance)
										ds.sort { it?.name }
									%>
                                    <g:select name="parent.id" from="${ds}" optionKey="id" optionValue="name"
                                    	noSelection="${[null: '']}" value="${departmentInstance?.parent?.id}" />
                                </td>
                            </tr>                            
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
