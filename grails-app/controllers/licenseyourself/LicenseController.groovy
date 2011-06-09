package licenseyourself

class LicenseController {
	static transactional = ["save", "update", "delete"]
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def uploadedFileService

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		if (params.sort == null) params.sort = 'owner'
		[licenseInstanceList: License.list(params), licenseInstanceTotal: License.count()]
	}

	def create = {
		def licenseInstance = new License()
		licenseInstance.properties = params
		return [licenseInstance: licenseInstance]
	}

	def save = {
		def licenseInstance = new License(params)
		if (licenseInstance.save(flush: true)) {
			flash.message = "${g.message(code: 'default.created.message', args: [g.message(code: 'license.label', default: 'License'), licenseInstance.id])}"
			redirect(action: "show", id: licenseInstance.id)
		}
		else {
			render(view: "create", model: [licenseInstance: licenseInstance])
		}
	}

	def show = {
		def licenseInstance = License.get(params.id)
		if (!licenseInstance) {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
			redirect(action: "list")
		}
		else {
			[licenseInstance: licenseInstance]
		}
	}

	def edit = {
		def licenseInstance = License.get(params.id)
		if (!licenseInstance) {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [licenseInstance: licenseInstance]
		}
	}

	def update = {
		def licenseInstance = License.get(params.id)
		if (licenseInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (licenseInstance.version > version) {

					licenseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						g.message(code: 'license.label', default: 'License')]
					as Object[], "Another user has updated this License while you were editing")
					render(view: "edit", model: [licenseInstance: licenseInstance])
					return
				}
			}
			licenseInstance.properties = params
			if (!licenseInstance.hasErrors() && licenseInstance.save(flush: true)) {
				flash.message = "${g.message(code: 'default.updated.message', args: [g.message(code: 'license.label', default: 'License'), licenseInstance.id])}"
				redirect(action: "show", id: licenseInstance.id)
			}
			else {
				render(view: "edit", model: [licenseInstance: licenseInstance])
			}
		}
		else {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def licenseInstance = License.get(params.id)
		if (licenseInstance) {
			try {
				licenseInstance.delete(flush: true)
				flash.message = "${g.message(code: 'default.deleted.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${g.message(code: 'default.not.deleted.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
			redirect(action: "list")
		}
	}

	def downloadLicenseFile = {
		def licenseInstance = License.get(params.id)
		if (licenseInstance != null) {
			uploadedFileService.streamToClient(licenseInstance.licenseFile, response)
		} else {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'license.label', default: 'License'), params.id])}"
			redirect(action: "list")
		}
	}
}
