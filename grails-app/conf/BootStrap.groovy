import licenseyourself.Department;
import licenseyourself.License;
import licenseyourself.LicenseAttachment;
import licenseyourself.LicenseUsage;
import licenseyourself.Product;
import licenseyourself.UploadedFile;

class BootStrap {

	def init = { servletContext ->

		def xs_lic_ms
		
		Department.withTransaction {
			def ste = new Department(name: 'SE Steuern', responsibleId: 'sr').save()
			def se = new Department(name: 'SE', responsibleId: 'dbi').save()

			def ulc = new Product(name: 'Canoo ULC',
					description: 'Rich-Application Framework (Java)',
					homepage: 'http://www.ulcjava.com'
					).save()
			def ulc_lic_file = new UploadedFile(name: 'ulc.txt', mimeType: 'text/plain', content: 'ULC Key: 12313141A21BFE'.getBytes())
			def ulc_lic = new License(owner: ste, subscription: true, allowedInstallations: 20, allowedConcurrent: 20, licenseFile: ulc_lic_file, product: ulc).save()
			def ulc_lic_ms = new LicenseUsage(license: ulc_lic, userid: 'ms', checkoutDate: new Date()).save()
			def ulc_lic_fh = new LicenseUsage(license: ulc_lic, userid: 'fh', checkoutDate: new Date()).save()
			def ulc_lic_sb = new LicenseUsage(license: ulc_lic, userid: 'sb', checkoutDate: new Date()).save()

			def xs = new Product(name: 'XmlSpy 2011',
					description: 'XML editor for windows',
					homepage: 'http://www.altova.com/xmlspy.html'
					).save()
			def xs_lic = new License(owner: se, subscription: false, allowedInstallations: null, allowedConcurrent: 5, product: xs).save()
			xs_lic_ms = new LicenseUsage(license: xs_lic, userid: 'ms1', checkoutDate: new Date()).save()
			def xs_lic_as = new LicenseUsage(license: xs_lic, userid: 'as', checkoutDate: new Date()).save()
			def xs_lic_cs = new LicenseUsage(license: xs_lic, userid: 'cs', checkoutDate: new Date()).save()
			
			def a_fa = new UploadedFile(name: 'A.txt', content: 'Hello Attachment A'.bytes, mimeType: 'text/plain')
			def a_fb = new UploadedFile(name: 'B.txt', content: 'Hello Attachment B'.bytes, mimeType: 'text/plain')
			def xs_lic_a = new LicenseAttachment(license: xs_lic, name: 'Attachment A', file: a_fa).save()
			def xs_lic_b = new LicenseAttachment(license: xs_lic, name: 'Attachment B', file: a_fb).save()
		}
		
		Department.withTransaction {
			xs_lic_ms.userid = 'ms'
			xs_lic_ms.save()
		}
	}
	def destroy = {
	}
}
