package licenseyourself

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.ldap.LdapUsernameToDnMapper;

class AdUsernameToDnMapper implements LdapUsernameToDnMapper {
	
	DistinguishedName buildDn(String username) {
		new DistinguishedName(username)
	}
}
