package licenseyourself

class DepartmentController {
	static transactional = ["save", "update", "delete"]
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		if (params.sort == null) params.sort = 'name'
        [departmentInstanceList: Department.list(params), departmentInstanceTotal: Department.count()]
    }

    def create = {
        def departmentInstance = new Department()
        departmentInstance.properties = params
        return [departmentInstance: departmentInstance]
    }

    def save = {
        def departmentInstance = new Department(params)
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
            [departmentInstance: departmentInstance]
        }
    }

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
