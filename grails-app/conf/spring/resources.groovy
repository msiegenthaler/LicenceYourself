import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import grails.util.Environment;
import licenseyourself.AdUserDetailsContextMapper;
import licenseyourself.AdUserProvider;
import licenseyourself.AdUsernameToDnMapper;
import licenseyourself.DevelopmentAuthenticationProvider;
import licenseyourself.DevelopmentUserProvider;
import licenseyourself.UserProvider;

// Place your Spring DSL code here

beans = {

	ldapUserDetailsMapper(AdUserDetailsContextMapper) {
		grailsApplication = ref("grailsApplication")
	}


	if (Environment.current.developmentMode) {
		userProvider(DevelopmentUserProvider) {}
		developmentAuthenticationProvider(DevelopmentAuthenticationProvider) {
			userProvider = ref("userProvider")
		}
	}
	else {
		ldapUsernameMapper(AdUsernameToDnMapper) {}
		
//		rememberMeServices(PersistentTokenBasedRememberMeServices) {
//			userDetailsService = ref(userProvider)
//		}
		
		userProvider(AdUserProvider) {
			ldapUserSearch = ref("ldapUserSearch")
			ldapAuthoritiesPopulator = ref("ldapAuthoritiesPopulator")
			userDetailsMapper = ref("ldapUserDetailsMapper")
		}
		
		userDetailsService(AdUserProvider) {
			ldapUserSearch = ref("ldapUserSearch")
			ldapAuthoritiesPopulator = ref("ldapAuthoritiesPopulator")
			userDetailsMapper = ref("ldapUserDetailsMapper")
		} 
//				userDetailsService = ref("userProvider") 
	}

}
