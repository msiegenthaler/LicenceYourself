package licenseyourself

class LicenseUsage {
	def userService

	String userid
	Date checkoutDate


	def getUser() {
		userService.findByUserId(userid)
	}
	def setUser(User user) {
		userid = user?.userid
	}

	static belongsTo = [license:License]
	def getProduct() {
		license.product
	}
	
	def String toString() {
		"$product-$userid"
	}

	static constraints = {
		userid(blank: false)
	}
}
