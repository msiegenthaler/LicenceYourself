package licenseyourself

class UploadedFileService {

	def streamToClient(UploadedFile file, resp) {
		def fileName = URLEncoder.encode(file.name)
		resp.addHeader("content-disposition", "attachment;filename=$fileName")
		resp.contentType = file.mimeType
		resp.contentLength = file.content.size()
		resp.outputStream << file.content
	}
	
}
