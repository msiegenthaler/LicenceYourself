package licenseyourself

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 * Maps the fields in the Active Directory to the {@link User}. 
 */
public class AdUserDetailsContextMapper implements UserDetailsContextMapper {
	def grailsApplication

	AdUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authorities) {
		String fullname = ctx.originalAttrs.attrs['name']?.values?.getAt(0)
		String email = ctx.originalAttrs.attrs['mail']?.values?.getAt(0)
		String userid = username.toLowerCase()
		def a = convertAuthorities(authorities)
		if (fullname==null) fullname = username

		new AdUserDetails(userid, '', true, true, true, true, a, fullname, email)
	}

	private def convertAuthorities(def authorities) {
		def gc = grailsApplication.config.licenseyourself.groups
		def admin = asRole(gc.admin)
		def manager = asRole(gc.manager)

		def authn = authorities.collect { it.authority }
		def res = new ArrayList(authorities)
		
		//add roles
		res.add(mkAuthority(Roles.USER)) //everybody authenticated has the user role
		if (authn.contains(admin)) res.add(mkAuthority(Roles.ADMIN))
		if (authn.contains(manager)) res.add(mkAuthority(Roles.MANAGER))
		res
	}

	private def mkAuthority(String name) {
		new GrantedAuthorityImpl(name)
	}
	private def asRole(String raw) {
		if (raw == null) return null;
		"ROLE_" + raw.toUpperCase()
	}

	void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		throw new IllegalStateException("Cannot write to ActiveDirectory")
	}
}

/**
 * Implementation of {@link User} based on data from Active Directory.
 * @author ea38
 */
public class AdUserDetails extends org.springframework.security.core.userdetails.User implements User {
	private final String fullname
	private final String email

	AdUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
	boolean credentialsNonExpired, boolean accountNonLocked,
	Collection<GrantedAuthority> authorities, String fullname,
	String email) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
		this.fullname = fullname
		this.email = email
	}

	String getUserid() {
		username
	}

	String getName() {
		fullname
	}

	String getEmail() {
		email
	}

	String toString() {
		fullname
	}
}