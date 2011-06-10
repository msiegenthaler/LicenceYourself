package licenseyourself

class UserController {
	def userService
	def licenseUsageService

	def index = {
		redirect(action: "list", params: params)
	}
	
	def show = {
		def user = userService.findByUserId(params.id)
			
		def us = LicenseUsage.findAllByUserid(user.userid)
		def products = us.groupBy { it.license.product }

		[
			user: user,
			products: products,
			departments: userService.departmentsForUser(user)
		]
	}
	
    def list = {
		 [
			 users: licenseUsageService.allUsersHavingLicenses().collect { [
				userid: it.userid,
				email: it.email,
				products: Product.findAllByUser(it).collect { it.name }.sort()  
			 ]}
		 ]
	}
}
