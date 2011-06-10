
<%@ page import="licenseyourself.Department" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'department.label', default: 'Department')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                            <td valign="top" class="name"><g:message code="department.name.label" default="Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: departmentInstance, field: "name")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="department.responsibleId.label" default="Responsible" /></td>
                            <td valign="top" class="value">${fieldValue(bean: departmentInstance, field: "responsible")}</td>
                        </tr>
                        
                        <g:if test="${departmentInstance.parent}">
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="department.parent.label" default="Parent Department" /></td>
                            
                            <td valign="top" class="value">
                            	<g:link action="show" id="${ departmentInstance?.parent?.id }">
                            		${ departmentInstance.parent?.name?.encodeAsHTML() }
                            	</g:link>
                            </td>
                        </tr>
                        </g:if>
                        
                        <g:if test="${departmentInstance.children.size()>0}">
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="department.children.label" default="Subdepartments" /></td>
                            <td valign="top" class="value">
                                <ul>
                                <g:each in="${departmentInstance.children}" var="d">
                                    <li>
                                    	<g:link action="show" id="${d.id}">
                                    		${d?.name?.encodeAsHTML()}
                                    	</g:link>
                                    </li>
                                </g:each>
                                </ul>
							</td>
                        </tr>
                        </g:if>
                        
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="department.licenses.products.label" default="Licensed Products" /></td>
                            <td valign="top" class="value">
                                <ul>
                                <g:each in="${departmentInstance.products}" var="p">
                                    <li>
                                    	<g:link controller="product" action="show" id="${p.id}">
                                    		${p?.name?.encodeAsHTML()}
                                    	</g:link>
                                    </li>
                                </g:each>
                                </ul>
							</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="department.users.products.label" default="Members" /></td>
                            <td valign="top" class="value">
                                <ul>
                                <g:each in="${users}" var="u">
                                    <li>
                                    	<g:link controller="user" action="show" id="${u.userid}">
                                    		${u?.name?.encodeAsHTML()}
                                    	</g:link>
                                    </li>
                                </g:each>
                                </ul>
							</td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${departmentInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
