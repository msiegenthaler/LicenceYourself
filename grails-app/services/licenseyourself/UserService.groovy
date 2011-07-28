package licenseyourself

import java.util.Collection;

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
		def depids = deps.collect { it.externalId }
		def users = allUserIdsHavingLicenses()
		def r = users.collect {
			findByUserId(it)
		}.findAll {
			def groups = userProvider.departmentsForUser(it)
			depids.findAll { groups.contains(it.toLowerCase()) }.size() > 0
		}
	}

	def allUsersHavingLicenses() {
		allUserIdsHavingLicenses().collect { findByUserId(it) }
	}

	private def allUserIdsHavingLicenses() {
		def c = LicenseUsage.createCriteria()
		def ids = c.list() {
			projections { distinct('userid') }
		}
		ids
	}

}
