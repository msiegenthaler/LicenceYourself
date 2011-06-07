package licenseyourself

import org.hibernate.envers.Audited;

@Audited
class Product {
	String name
	String description
	URL homepage

	static hasMany = [licenses:License]

	def usedBy() {
		licenses.collect(it.users).flatten
	}

	def String toString() {
		name
	}

	static constraints = {
		name(blank: false)
		description(nullable: true)
	}

	static def findAllByUser(User user) {
		if (user==null) return []
		def c = LicenseUsage.createCriteria()
		c.list {
			projections { distinct('license') }
			eq("userid", user.userid)
		}.collect {
			it.product
		}
	}
}
