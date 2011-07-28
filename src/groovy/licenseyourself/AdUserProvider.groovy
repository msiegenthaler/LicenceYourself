package licenseyourself

import java.beans.PropertyChangeEvent;
import java.util.Collection;

import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class AdUserProvider implements UserProvider {
	def ldapUserSearch
	def ldapAuthoritiesPopulator
	def userDetailsMapper

	User userForUserid(String userid) {
		def data = loadUserData(userid)
		createUserFromData(data, userid)
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
		else new NonExistingUser(userid: userid)
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
