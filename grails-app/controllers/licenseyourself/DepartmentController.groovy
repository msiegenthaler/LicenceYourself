package licenseyourself

import grails.plugins.springsecurity.Secured;

@Secured(["hasRole('ROLE_USER')"])
class DepartmentController {
	static transactional = ["save", "update", "delete"]
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def userService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		if (params.sort == null) params.sort = 'name'
        [departmentInstanceList: Department.list(params), departmentInstanceTotal: Department.count()]
    }

	@Secured(["hasRole('ROLE_ADMIN')"])
    def create = {
        def departmentInstance = new Department()
        departmentInstance.properties = params
        return [departmentInstance: departmentInstance]
    }
	
	private def collectParents(d) {
		def res = []
		def p = d.parent
		while (p != null) {
			if (res.contains(p)) break
			res << p
			p = p.parent
		}
		res
	}
	private def checkNotRecursive(departmentInstance) {
		def parents = collectParents(departmentInstance)
		if (parents.contains(departmentInstance)) {
			departmentInstance.parent = null
            departmentInstance.errors.rejectValue("parent", "department.recursive.failure", [] as Object[], "Recursive department structure")
		} 
	}

	@Secured(["hasRole('ROLE_ADMIN')"])
    def save = {
        def departmentInstance = new Department(params)
		checkNotRecursive(departmentInstance)
        if (departmentInstance.save(flush: true)) {
            flash.message = "${g.message(code: 'default.created.message', args: [g.message(code: 'department.label', default: 'Department'), departmentInstance.id])}"
            redirect(action: "show", id: departmentInstance.id)
        }
        else {
            render(view: "create", model: [departmentInstance: departmentInstance])
        }
    }

    def show = {
        def departmentInstance = Department.get(params.id)
        if (!departmentInstance) {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
            redirect(action: "list")
        }
        else {
			def users = userService.usersForDepartmentRecursive(departmentInstance)
            [departmentInstance: departmentInstance, users: users]
        }
    }

	@Secured(["hasRole('ROLE_ADMIN')"])
    def edit = {
        def departmentInstance = Department.get(params.id)
        if (!departmentInstance) {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [departmentInstance: departmentInstance]
        }
    }

	@Secured(["hasRole('ROLE_ADMIN')"])
    def update = {
        def departmentInstance = Department.get(params.id)
        if (departmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (departmentInstance.version > version) {
                    
                    departmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [g.message(code: 'department.label', default: 'Department')] as Object[], "Another user has updated this Department while you were editing")
                    render(view: "edit", model: [departmentInstance: departmentInstance])
                    return
                }
            }
            departmentInstance.properties = params
			checkNotRecursive(departmentInstance)
            if (!departmentInstance.hasErrors() && departmentInstance.save(flush: true)) {
                flash.message = "${g.message(code: 'default.updated.message', args: [g.message(code: 'department.label', default: 'Department'), departmentInstance.id])}"
                redirect(action: "show", id: departmentInstance.id)
            }
            else {
                render(view: "edit", model: [departmentInstance: departmentInstance])
            }
        }
        else {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
            redirect(action: "list")
        }
    }

	@Secured(["hasRole('ROLE_ADMIN')"])
    def delete = {
        def departmentInstance = Department.get(params.id)
        if (departmentInstance) {
            try {
                departmentInstance.delete(flush: true)
                flash.message = "${g.message(code: 'default.deleted.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${g.message(code: 'default.not.deleted.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'department.label', default: 'Department'), params.id])}"
            redirect(action: "list")
        }
    }
}
