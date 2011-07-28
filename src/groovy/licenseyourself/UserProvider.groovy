package licenseyourself

/**
 * Backend for dealing with users.
 * @see User
 */
interface UserProvider {
	
	User userForUserid(String userid)
	
	Collection<String> departmentsForUser(User user)

}
