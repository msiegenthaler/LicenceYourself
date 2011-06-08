package licenseyourself

import org.hibernate.envers.Audited;

@Audited
class License {
	def userService

	String comment
	boolean subscription
	
	Integer allowedInstallations
	Integer allowedConcurrent
	
	String licenseKey
	byte[] licenseFile

	static belongsTo = [product:Product, owner:Department]
	static hasMany = [users:LicenseUsage, attachments:LicenseAttachment]
	
	def String toString() { 
		"$product.name-$owner.name-$id"	
	}
	
	static constraints = {
		allowedConcurrent(min: 0)
		allowedInstallations(min: 0, nullable: true)
		comment(nullable: true)
		licenseKey(nullable: true)
		licenseFile(nullable: true)
	}
}
