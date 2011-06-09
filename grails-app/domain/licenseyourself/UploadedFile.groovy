package licenseyourself


class UploadedFile {
	String name
	String mimeType
	byte[] content
	
	static constraints = {
		name(blank: false)
		mimeType(blank: false)
	}
}
