package licenseyourself

import java.util.Collection;

import org.springframework.ldap.AuthenticationException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;


class DevelopmentAuthenticationProvider implements AuthenticationProvider {
	def userProvider
	
	Authentication authenticate(Authentication auth) throws AuthenticationException {
		if (auth instanceof DevelopmentAuthenticationToken) return auth
		
		def uid = auth.principal.toString().toLowerCase()
		def roles
		switch (uid) {
			case "admin": 
			case "ms":
				roles = ['ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER']
				break
			case "manager":
			case "sr":
			case "cs":
				roles = ['ROLE_MANAGER', 'ROLE_USER']
				break;
			default:
				roles = ['ROLE_USER']
		}
		roles = roles.collect { new GrantedAuthorityImpl(it) }
		
		def details = userProvider.userForUserid(uid)
		def principal = new DevelopmentUser(uid, "", true, true, true, true, roles, uid, details.name, details.email)
		
		def token = new DevelopmentAuthenticationToken(principal, roles)
		token.setDetails(principal)
		token
	}
	
	boolean supports(Class clazz) {
		true
	}
	
}

class DevelopmentAuthenticationToken extends AbstractAuthenticationToken {
	private def username
	
	DevelopmentAuthenticationToken(def username, Collection<GrantedAuthority> auths) {
		super(auths)
		this.username = username
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}
}

class DevelopmentUser extends org.springframework.security.core.userdetails.User implements User {
	private final String fullname
	private final String email
	private final String userid

	DevelopmentUser(String username, String password, boolean enabled, boolean accountNonExpired,
	boolean credentialsNonExpired, boolean accountNonLocked,
	Collection<GrantedAuthority> authorities, String userid, String fullname,
	String email) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities)
		this.userid = userid
		this.fullname = fullname
		this.email = email
	}
	
	
	String getUserid() {
		userid
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