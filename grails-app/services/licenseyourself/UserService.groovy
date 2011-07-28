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
		userProvider.departmentsForUser(user)
	}
	
	def usersForDepartment(Department department) {
		userProvider.usersForDepartment(department, false)
	}
	def usersForDepartmentRecursive(Department department) {
		userProvider.usersForDepartment(department, true)
	}
}
