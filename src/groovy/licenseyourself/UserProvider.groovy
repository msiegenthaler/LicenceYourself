package licenseyourself

/**
 * Backend for dealing with users.
 * @see User
 */
interface UserProvider {
	
	User userForUserid(String userid)
	
	Collection<Department> departmentsForUser(User user)
	
	Collection<User> usersForDepartment(Department department, boolean includeSubdepartments)

}
