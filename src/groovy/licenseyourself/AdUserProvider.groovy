
package licenseyourself

import java.beans.PropertyChangeEvent;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AdUserProvider implements UserProvider, UserDetailsService {
	def ldapUserSearch
	def ldapAuthoritiesPopulator
	def userDetailsMapper

	UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException, DataAccessException {
		def data = loadUserData(userid)
		def user = createUserFromData(data, username, false)
		if (user == null) throw new UsernameNotFoundException("user ${username} was not found")
		user
	}
	

	User userForUserid(String userid) {
		def data = loadUserData(userid)
		def user = createUserFromData(data, userid)
		if (user == null) user = new NonExistingUser(userid: userid)
		user
	}

	Collection<String> departmentsForUser(User user) {
		def uid = user.userid
		def data = loadUserData(uid)
		groupsFromData(data, uid)
	}

	private def loadUserData(String userid) {
		try {
			ldapUserSearch.searchForUser(userid)
		} catch (UsernameNotFoundException e) {
			null
		}
	}

	private def createUserFromData(def data, String userid) {
		if (data!=null) userDetailsMapper.mapUserFromContext(data, userid, [])
		else null
	}

	private def groupsFromData(def data, String userid) {
		if (data==null) return []
		def groups = ldapAuthoritiesPopulator.getGrantedAuthorities(data, userid)
		groups.collect {
			def name = it.authority.toLowerCase()
			if (name.startsWith('role_')) name.substring(5)
			else name
		}
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
