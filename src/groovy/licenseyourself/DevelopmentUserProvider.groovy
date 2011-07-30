package licenseyourself

import groovy.lang.Immutable;

import java.util.Collection;

/**
 * Dummy implementation that returns a fixed set of users.
 */
class DevelopmentUserProvider implements UserProvider {

	User userForUserid(String id) {
		switch (id) {
			case 'ms':
				return new SimpleUserImpl(userid: id, name: 'Mario Siegenthaler', email: 'mario.siegenthaler@bedag.ch')
			case 'sr':
				return new SimpleUserImpl(userid: id, name: 'Robert Siegenthaler',email:  'robert.siegenthaler@bedag.ch')
			case 'dbi':
				return new SimpleUserImpl(userid: id, name: 'Daniel Biemann', email: 'daniel.biemann@bedag.ch')
			case 'fh':
				return new SimpleUserImpl(userid: id, name: 'Ferdinand Hübner', email: 'ferdinand.huebner@bedag.ch')
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
	
	Collection<String> departmentsForUser(User user) {
		switch (user?.userid) {
			case 'sr':
				return [
					'ste'
				]

			case 'ms':
			case 'fh':
			case 'sb':
				return [
					'dej'
				]

			case 'as':
			case 'cs':
				return [
					'gba'
				]

			case 'dbi':
				return [
					'se'
				]

			default:
				return null
		}
	}
}

@Immutable
private class SimpleUserImpl implements User {
	final String userid
	final String name
	final String email
	
	String toString() {
		name
	}
}