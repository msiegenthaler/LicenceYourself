package licenseyourself

class LicenseUsageService {
	static transactional = true

	def userService

	def allUsersHavingLicenses() {
		def c = LicenseUsage.createCriteria()
		def ids = c.list() {
			projections { distinct('userid') }
		}
		ids.collect { userService.findByUserId(it) }
	}
}
