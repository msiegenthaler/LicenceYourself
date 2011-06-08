package licenseyourself

class LicenseAttachmentController {
	static transactional = ["save", "update", "delete"]
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [licenseAttachmentInstanceList: LicenseAttachment.list(params), licenseAttachmentInstanceTotal: LicenseAttachment.count()]
    }

    def create = {
        def licenseAttachmentInstance = new LicenseAttachment()
        licenseAttachmentInstance.properties = params
        return [licenseAttachmentInstance: licenseAttachmentInstance]
    }

    def save = {
        def licenseAttachmentInstance = new LicenseAttachment(params)
        if (licenseAttachmentInstance.save(flush: true)) {
            flash.message = "${g.message(code: 'default.created.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), licenseAttachmentInstance.id])}"
            redirect(action: "show", id: licenseAttachmentInstance.id)
        }
        else {
            render(view: "create", model: [licenseAttachmentInstance: licenseAttachmentInstance])
        }
    }

    def show = {
        def licenseAttachmentInstance = LicenseAttachment.get(params.id)
        if (!licenseAttachmentInstance) {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
            redirect(action: "list")
        }
        else {
            [licenseAttachmentInstance: licenseAttachmentInstance]
        }
    }

    def edit = {
        def licenseAttachmentInstance = LicenseAttachment.get(params.id)
        if (!licenseAttachmentInstance) {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [licenseAttachmentInstance: licenseAttachmentInstance]
        }
    }

    def update = {
        def licenseAttachmentInstance = LicenseAttachment.get(params.id)
        if (licenseAttachmentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (licenseAttachmentInstance.version > version) {
                    
                    licenseAttachmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment')] as Object[], "Another user has updated this LicenseAttachment while you were editing")
                    render(view: "edit", model: [licenseAttachmentInstance: licenseAttachmentInstance])
                    return
                }
            }
            licenseAttachmentInstance.properties = params
            if (!licenseAttachmentInstance.hasErrors() && licenseAttachmentInstance.save(flush: true)) {
                flash.message = "${g.message(code: 'default.updated.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), licenseAttachmentInstance.id])}"
                redirect(action: "show", id: licenseAttachmentInstance.id)
            }
            else {
                render(view: "edit", model: [licenseAttachmentInstance: licenseAttachmentInstance])
            }
        }
        else {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def licenseAttachmentInstance = LicenseAttachment.get(params.id)
        if (licenseAttachmentInstance) {
            try {
                licenseAttachmentInstance.delete(flush: true)
                flash.message = "${g.message(code: 'default.deleted.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${g.message(code: 'default.not.deleted.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'licenseAttachment.label', default: 'LicenseAttachment'), params.id])}"
            redirect(action: "list")
        }
    }
}
