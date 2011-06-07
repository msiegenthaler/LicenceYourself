package licenseyourself

import org.hibernate.envers.Audited;

@Audited
class Department {
	def userService
	
	String name
	String responsibleId
	
	def getResponsible() {
		userService.findByUserId(responsibleId)
	}
	def setResponsible(User user) {
		responsibleId = user?.userid
	}
	
	
	static hasMany = [licenses:License]
	
	
	def getProducts() {
		def c = License.createCriteria()
		c.list {
			projections { distinct('product') }
			eq('owner', this)
		}
	}
	
	def String toString() {
		name
	}

    static constraints = {
    }
}
