import licenseyourself.AdUserDetailsContextMapper;
import licenseyourself.AdUserProvider;
import licenseyourself.DevelopmentUserProvider;
import licenseyourself.UserProvider;

// Place your Spring DSL code here

beans = {

	ldapUserDetailsMapper(AdUserDetailsContextMapper) {}
	
	userProvider(DevelopmentUserProvider) {}
//	userProvider(AdUserProvider) {
//		ldapUserSearch = ref("ldapUserSearch")
//		userDetailsMapper = ref("ldapUserDetailsMapper")
//	}
}
