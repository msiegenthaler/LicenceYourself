package licenseyourself

import javax.sql.rowset.spi.TransactionalWriter;

class LicenseUsageController {
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		if (params.sort == null) params.sort = 'license'
        [licenseUsageInstanceList: LicenseUsage.list(params), licenseUsageInstanceTotal: LicenseUsage.count()]
    }

    def create = {
        def licenseUsageInstance = new LicenseUsage()
        licenseUsageInstance.properties = params
        return [licenseUsageInstance: licenseUsageInstance]
    }

    def save = {
        def licenseUsageInstance = new LicenseUsage(params)
        if (licenseUsageInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), licenseUsageInstance.id])}"
            redirect(action: "show", id: licenseUsageInstance.id)
        }
        else {
            render(view: "create", model: [licenseUsageInstance: licenseUsageInstance])
        }
    }

    def show = {
        def licenseUsageInstance = LicenseUsage.get(params.id)
        if (!licenseUsageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
            redirect(action: "list")
        }
        else {
            [licenseUsageInstance: licenseUsageInstance]
        }
    }

    def edit = {
        def licenseUsageInstance = LicenseUsage.get(params.id)
        if (!licenseUsageInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [licenseUsageInstance: licenseUsageInstance]
        }
    }

    def update = {
        def licenseUsageInstance = LicenseUsage.get(params.id)
        if (licenseUsageInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (licenseUsageInstance.version > version) {
                    
                    licenseUsageInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'licenseUsage.label', default: 'LicenseUsage')] as Object[], "Another user has updated this LicenseUsage while you were editing")
                    render(view: "edit", model: [licenseUsageInstance: licenseUsageInstance])
                    return
                }
            }
            licenseUsageInstance.properties = params
            if (!licenseUsageInstance.hasErrors() && licenseUsageInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), licenseUsageInstance.id])}"
                redirect(action: "show", id: licenseUsageInstance.id)
            }
            else {
                render(view: "edit", model: [licenseUsageInstance: licenseUsageInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def licenseUsageInstance = LicenseUsage.get(params.id)
        if (licenseUsageInstance) {
            try {
                licenseUsageInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'licenseUsage.label', default: 'LicenseUsage'), params.id])}"
            redirect(action: "list")
        }
    }
}
