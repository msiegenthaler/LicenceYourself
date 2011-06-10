package licenseyourself

class UserService {

	def findByUserId(String id) {
		//TODO temporary, replace with ldap
		switch (id) {
			case 'ms':
				return new User(userid: id, name: 'Mario Siegenthaler', email: 'mario.siegenthaler@bedag.ch')
			case 'sr':
				return new User(userid: id, name: 'Robert Siegenthaler',email:  'robert.siegenthaler@bedag.ch')
			case 'dbi':
				return new User(userid: id, name: 'Daniel Biemann', email: 'daniel.biemann@bedag.ch')
			case 'fh':
				return new User(userid: id, name: 'Ferdinand HŸbner', email: 'ferdinand.huebner@bedag.ch')
			case 'sb':
				return new User(userid: id, name: 'Sebastian Bayer', email: 'sebastian.bayer@bedag.ch')
			case 'as':
				return new User(userid: id, name: 'Alexander Stucki', email: 'alexander.stucki@bedag.ch')
			case 'cs':
				return new User(userid: id, name: 'Christian Saner', email: 'christian.saner@bedag.ch')
			default:
				new User(userid: id, name: "$id", email: "$id@domain.com")
		}
	}

	def currentUser() {
		//TODO actual implementation based on spring security
		findByUserId('ms')
	}
	
	def departmentsForUser(User user) {
		//TODO get from ldap
		switch (user?.userid) {
			case 'ms':
			case 'sr':
			case 'fh':
			case 'sb':
				return [Department.findByName('SE Steuern')]
				
			case 'as':
			case 'cs':
				return [Department.findByName('SE Grundbuch')]
				
			case 'dbi':
				return [Department.findByName('SE')]
				
			default:
				return null
		}
	}
}
