package licenseyourself

class UserService {
	
	private def allKnown = ['ms', 'sr', 'dbi', 'fh', 'sb', 'as', 'cs']

	def findByUserId(String id) {
		//TODO temporary, replace with ldap
		switch (id) {
			case 'ms':
				return new SimpleUserImpl(userid: id, name: 'Mario Siegenthaler', email: 'mario.siegenthaler@bedag.ch')
			case 'sr':
				return new SimpleUserImpl(userid: id, name: 'Robert Siegenthaler',email:  'robert.siegenthaler@bedag.ch')
			case 'dbi':
				return new SimpleUserImpl(userid: id, name: 'Daniel Biemann', email: 'daniel.biemann@bedag.ch')
			case 'fh':
				return new SimpleUserImpl(userid: id, name: 'Ferdinand HŸbner', email: 'ferdinand.huebner@bedag.ch')
			case 'sb':
				return new SimpleUserImpl(userid: id, name: 'Sebastian Bayer', email: 'sebastian.bayer@bedag.ch')
			case 'as':
				return new SimpleUserImpl(userid: id, name: 'Alexander Stucki', email: 'alexander.stucki@bedag.ch')
			case 'cs':
				return new SimpleUserImpl(userid: id, name: 'Christian Saner', email: 'christian.saner@bedag.ch')
			default:
				new SimpleUserImpl(userid: id, name: "$id", email: "$id@domain.com")
		}
	}

	def currentUser() {
		//TODO actual implementation based on spring security
		findByUserId('ms')
	}
	
	def departmentsForUser(User user) {
		//TODO get from ldap
		switch (user?.userid) {
			case 'sr':
				return [Department.findByName('SE Steuern')]
				
			case 'ms':
			case 'fh':
			case 'sb':
				return [Department.findByName('SE Steuern Java')]
				
			case 'as':
			case 'cs':
				return [Department.findByName('SE Grundbuch')]
				
			case 'dbi':
				return [Department.findByName('SE')]
				
			default:
				return null
		}
	}
	
	def usersForDepartment(Department department) {
		usersForDepartments([department])
	}
	def usersForDepartmentRecursive(Department department) {
		usersForDepartments(department.recursive)
	}
	def usersForDepartments(ds) {
		def users = allKnown.collect { findByUserId(it) }
		users = users.findAll { departmentsForUser(it).intersect(ds).size() > 0 }
		users.sort { it.name }
	}
}


@Immutable
class SimpleUserImpl implements User {
	final String userid
	final String name
	final String email
}