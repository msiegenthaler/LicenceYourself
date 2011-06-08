package licenseyourself

import org.hibernate.envers.Audited;

@Audited
class LicenseAttachment {
	
	String name
	byte[] content
	
	def getUploadDate() {
		revisionEntity?.revisionDate
	}

	static belongsTo = [license:License]
	
    static constraints = {
		name(blank: false)
    }
}
