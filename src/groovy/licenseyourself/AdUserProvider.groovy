package licenseyourself

import java.beans.PropertyChangeEvent;
import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AdUserProvider implements UserProvider {
	def ldapUserSearch
	def ldapAuthoritiesPopulator
	def userDetailsMapper

	AdUserDetails userForUserid(String userid) {
		try {
			def dirctx = ldapUserSearch.searchForUser(userid)
			userDetailsMapper.mapUserFromContext(dirctx, userid, [])
		} catch (UsernameNotFoundException e) {
			return new NonExistingUser(userid: userid)
		}
	}
	
	Collection<String> departmentsForUser(User user) {
		try {
			def uid = user.userid
			def dirctx = ldapUserSearch.searchForUser(uid)
			def groups = ldapAuthoritiesPopulator.getGrantedAuthorities(dirctx, uid)
			groups.collect { 
				def name = it.authority.toLowerCase()
				if (name.startsWith('role_')) name.substring(5)
				else name
			}
		} catch (UsernameNotFoundException e) {
			return []
		}
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
