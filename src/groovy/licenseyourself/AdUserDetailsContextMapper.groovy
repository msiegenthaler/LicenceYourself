package licenseyourself

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

/**
 * Maps the fields in the Active Directory to the {@link User}. 
 * @author ea38
 */
public class AdUserDetailsContextMapper implements UserDetailsContextMapper {
	AdUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authorities) {
		String fullname = ctx.originalAttrs.attrs['name'].values[0]
		String email = ctx.originalAttrs.attrs['mail'].values[0]
		String userid = ctx.originalAttrs.attrs['samaccountname'].values[0].toString().toLowerCase()

		new AdUserDetails(userid, '', true, true, true, true, authorities, fullname, email)
	}

	void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		throw new IllegalStateException("Cannot write to ActiveDirectory")
	}
}

/**
 * Implementation of {@link User} based on data from Active Directory.
 * @author ea38
 */
class AdUserDetails extends org.springframework.security.core.userdetails.User implements User {
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