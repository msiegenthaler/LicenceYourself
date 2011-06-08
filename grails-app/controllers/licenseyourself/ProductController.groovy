package licenseyourself

class ProductController {
	static transactional = ["save", "update", "delete"]
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)

		def prods = Product.list(params)
		def allowed = prods.collect {
			it.licenses.sum { l -> l.allowedInstallations }
		}
		def installed = prods.collect {
			it.licenses.sum { l -> l.users.size() }
		}

		[productInstanceList: prods, allowed: allowed, installed: installed, productInstanceTotal: Product.count()]
	}

	def create = {
		def productInstance = new Product()
		productInstance.properties = params
		return [productInstance: productInstance]
	}

	def save = {
		def productInstance = new Product(params)
		if (productInstance.save(flush: true)) {
			flash.message = "${g.message(code: 'default.created.message', args: [g.message(code: 'product.label', default: 'Product'), productInstance.id])}"
			redirect(action: "show", id: productInstance.id)
		}
		else {
			render(view: "create", model: [productInstance: productInstance])
		}
	}

	def show = {
		def productInstance = Product.get(params.id)
		if (!productInstance) {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
			redirect(action: "list")
		}
		else {
			[productInstance: productInstance]
		}
	}

	def edit = {
		def productInstance = Product.get(params.id)
		if (!productInstance) {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [productInstance: productInstance]
		}
	}

	def update = {
		def productInstance = Product.get(params.id)
		if (productInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (productInstance.version > version) {

					productInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						g.message(code: 'product.label', default: 'Product')]
					as Object[], "Another user has updated this Product while you were editing")
					render(view: "edit", model: [productInstance: productInstance])
					return
				}
			}
			productInstance.properties = params
			if (!productInstance.hasErrors() && productInstance.save(flush: true)) {
				flash.message = "${g.message(code: 'default.updated.message', args: [g.message(code: 'product.label', default: 'Product'), productInstance.id])}"
				redirect(action: "show", id: productInstance.id)
			}
			else {
				render(view: "edit", model: [productInstance: productInstance])
			}
		}
		else {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def productInstance = Product.get(params.id)
		if (productInstance) {
			try {
				productInstance.delete(flush: true)
				flash.message = "${g.message(code: 'default.deleted.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${g.message(code: 'default.not.deleted.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${g.message(code: 'default.not.found.message', args: [g.message(code: 'product.label', default: 'Product'), params.id])}"
			redirect(action: "list")
		}
	}
}
