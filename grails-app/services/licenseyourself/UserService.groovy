package licenseyourself

class UserService {
	def springSecurityService
	def userProvider
	
	def findByUserId(String id) {
		userProvider.userForUserid(id)
	}

	def currentUser() {
		springSecurityService.currentUser
	}
	
	def departmentsForUser(User user) {
		userProvider.departmentsForUser(user).collect { Department.findByExternalId(it) }.findAll { it != null }
	}
	
	def usersForDepartment(Department department) {
		userForDepartments([department])
	}
	def usersForDepartmentRecursive(Department department) {
		userForDepartments(department.recursive)
	}
	private def userForDepartments(deps) {
		def d = deps.collect { it.externalId }
		userProvider.usersForDepartments(d)
	}
}
