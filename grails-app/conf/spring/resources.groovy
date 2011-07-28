import grails.util.Environment;
import licenseyourself.AdUserDetailsContextMapper;
import licenseyourself.AdUserProvider;
import licenseyourself.DevelopmentUserProvider;
import licenseyourself.UserProvider;

// Place your Spring DSL code here

beans = {

	ldapUserDetailsMapper(AdUserDetailsContextMapper) {}


	if (Environment.current.developmentMode) {
		userProvider(DevelopmentUserProvider) {}
	}
	else {
		userProvider(AdUserProvider) {
			ldapUserSearch = ref("ldapUserSearch")
			userDetailsMapper = ref("ldapUserDetailsMapper")
		}
	}

}
