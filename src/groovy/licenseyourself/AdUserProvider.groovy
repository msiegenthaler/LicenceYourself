package licenseyourself

import java.beans.PropertyChangeEvent;
import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AdUserProvider implements UserProvider {
	def ldapUserSearch
	def userDetailsMapper

	User userForUserid(String userid) {
		try {
			def dirctx = ldapUserSearch.searchForUser(userid)
			userDetailsMapper.mapUserFromContext(dirctx, userid, [])
		} catch (UsernameNotFoundException e) {
			return new NonExistingUser(userid: userid)
		}
	}
	
	Collection<String> departmentsForUser(User arg0) {
		//TODO
		[]
	}
	
	Collection<User> usersForDepartments(Collection dep) {
		//TODO
		[]
	}
}

@Immutable
private class NonExistingUser implements User {
	final String email = null
	final String name = null
	final String userid
	String toString() {
		userid
	}
}
