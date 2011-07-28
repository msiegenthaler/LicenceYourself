import licenseyourself.AdUserDetailsContextMapper;
import licenseyourself.DevelopmentUserProvider;

// Place your Spring DSL code here

beans = {

	ldapUserDetailsMapper(AdUserDetailsContextMapper) {}
	
	userProvider(DevelopmentUserProvider) {}
}
