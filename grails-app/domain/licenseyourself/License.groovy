package licenseyourself

class License {
	def userService

	String comment
	
	int allowedInstallations
	int allowedConcurrent
	
	String licenseKey
	byte[] licenseFile
	

	static belongsTo = [product:Product, owner:Department]
	static hasMany = [users:LicenseUsage]
	
	def String toString() { 
		"$product.name-$owner.name-$id"	
	}
	
	static constraints = {
		allowedConcurrent(min: 0)
		allowedInstallations(min: 0)
		comment(nullable: true)
		licenseKey(nullable: true)
		licenseFile(nullable: true)
	}
}
