package licenseyourself

class UploadedFileService {

	def streamToClient(UploadedFile file, resp) {
		def fileName = URLEncoder.encode(file.name)
		resp.addHeader("content-disposition", "attachment;filename=$fileName")
		resp.contentType = file.mimeType
		resp.contentLength = file.content.size()
		resp.outputStream << file.content
	}
	
	def UploadedFile getFromClient(req, key) {
		def f = req.getFile(key)
		if (f == null || f.size == 0) return null
		new UploadedFile(name: f.originalFilename, mimeType: f.contentType, content: f.bytes)
	}
	
}
