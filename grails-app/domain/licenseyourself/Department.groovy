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
	
	def isTopLevel() {
		parent == null
	}
	def getRecursive() {
		def res = new HashSet()
		res << this
		children.each {
			res.addAll(it.recursive)
		}
		res
	}
	
	
	static belongsTo = [parent:Department]
	static hasMany = [licenses:License, children:Department]
	
	
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
		parent(nullable: true)
    }
}
