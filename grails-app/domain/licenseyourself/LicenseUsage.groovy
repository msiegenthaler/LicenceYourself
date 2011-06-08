package licenseyourself

import net.lucasward.grails.plugin.FindAtRevisionQuery;

import org.hibernate.envers.Audited;

@Audited
class LicenseUsage {
	def userService

	String userid
	
	def getCheckoutDate() {
		revisionEntity?.revisionDate
	}
	
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
		def un = user.name
		"$product ($un)"
	}

	static constraints = {
		userid(blank: false)
	}
}
