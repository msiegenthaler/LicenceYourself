package licenseyourself

class UserService {

	def findByUserId(String id) {
		//TODO temporary
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

}
