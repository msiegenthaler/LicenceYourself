import org.codehaus.groovy.grails.commons.GrailsApplication;

import grails.util.Environment;
import grails.util.GrailsUtil;
import licenseyourself.Department;
import licenseyourself.License;
import licenseyourself.LicenseAttachment;
import licenseyourself.LicenseUsage;
import licenseyourself.Product;
import licenseyourself.UploadedFile;

class BootStrap {
	def securityContextPersistenceFilter

	def init = { servletContext ->
		securityContextPersistenceFilter?.forceEagerSessionCreation = true

		if (Environment.current.developmentMode) {
			// create some test data
			Department.withTransaction {
				def se = new Department(name: 'SE', responsibleId: 'dbi', externalId: 'se').save()
				def ste = new Department(name: 'SE Steuern', responsibleId: 'sr', externalId: 'ste', parent: se).save()
				def gba = new Department(name: 'SE Grundbuch', responsibleId: 'cs', externalId: 'gba', parent: se).save()
				def dej = new Department(name: 'SE Steuern Java', responsibleId: 'ms', externalId: 'dej', parent: ste).save()

				def ulc = new Product(name: 'Canoo ULC',
						description: 'Rich-Application Framework (Java)',
						homepage: 'http://www.ulcjava.com'
						).save()
				def ulc_lic_file = new UploadedFile(name: 'ulc.txt', mimeType: 'text/plain', content: 'ULC Key: 12313141A21BFE'.getBytes())
				def ulc_lic = new License(owner: ste, subscription: true, allowedInstallations: 20, allowedConcurrent: 20, licenseFile: ulc_lic_file, product: ulc).save()
				new LicenseUsage(license: ulc_lic, userid: 'ms', checkoutDate: new Date()).save()
				new LicenseUsage(license: ulc_lic, userid: 'fh', checkoutDate: new Date()).save()
				new LicenseUsage(license: ulc_lic, userid: 'sb', checkoutDate: new Date()).save()

				def xs = new Product(name: 'XmlSpy 2011',
						description: 'XML editor for windows',
						homepage: 'http://www.altova.com/xmlspy.html'
						).save()
				def xs_lic = new License(owner: se, subscription: false, allowedInstallations: null, allowedConcurrent: 5, product: xs).save()
				new LicenseUsage(license: xs_lic, userid: 'ms', checkoutDate: new Date()).save()
				new LicenseUsage(license: xs_lic, userid: 'as', checkoutDate: new Date()).save()
				new LicenseUsage(license: xs_lic, userid: 'cs', checkoutDate: new Date()).save()

				def a_fa = new UploadedFile(name: 'A.txt', content: 'Hello Attachment A'.bytes, mimeType: 'text/plain')
				def a_fb = new UploadedFile(name: 'B.txt', content: 'Hello Attachment B'.bytes, mimeType: 'text/plain')
				new LicenseAttachment(license: xs_lic, name: 'Attachment A', file: a_fa).save()
				new LicenseAttachment(license: xs_lic, name: 'Attachment B', file: a_fb).save()
			}
		} else if (Environment.current == Environment.TEST) {
			// create some test data
			Department.withTransaction {
				def se = new Department(name: 'SE', responsibleId: 'raxp', externalId: 'se').save()
				def ste = new Department(name: 'SE Steuern', responsibleId: 'e223', externalId: 'zg5-001-0000-g', parent: se).save()
				def per = new Department(name: 'SE Personal', responsibleId: 'e101', externalId: 'zg5-002-0000-g', parent: se).save()
				//def gba = new Department(name: 'SE Grundbuch', responsibleId: 'cs', externalId: 'zg5-003-0000-g', parent: se).save()
				//def vrk = new Department(name: 'SE Verkehr', responsibleId: 'cs', externalId: 'zg5-003-0000-g', parent: se).save()

				def ulc = new Product(name: 'Canoo ULC',
						description: 'Rich-Application Framework (Java)',
						homepage: 'http://www.ulcjava.com'
						).save()
				def ulc_lic_file = new UploadedFile(name: 'ulc.txt', mimeType: 'text/plain', content: 'ULC Key: 12313141A21BFE'.getBytes())
				def ulc_lic = new License(owner: ste, subscription: true, allowedInstallations: 20, allowedConcurrent: 20, licenseFile: ulc_lic_file, product: ulc).save()
				new LicenseUsage(license: ulc_lic, userid: 'ea38', checkoutDate: new Date()).save()
				new LicenseUsage(license: ulc_lic, userid: 'rbze', checkoutDate: new Date()).save()
				new LicenseUsage(license: ulc_lic, userid: 'e223', checkoutDate: new Date()).save()

				def xs = new Product(name: 'XmlSpy 2011',
						description: 'XML editor for windows',
						homepage: 'http://www.altova.com/xmlspy.html'
						).save()
				def xs_lic = new License(owner: se, subscription: false, allowedInstallations: null, allowedConcurrent: 5, product: xs).save()
				new LicenseUsage(license: xs_lic, userid: 'ea38', checkoutDate: new Date()).save()
				new LicenseUsage(license: xs_lic, userid: 'rbze', checkoutDate: new Date()).save()
				new LicenseUsage(license: xs_lic, userid: 'e101', checkoutDate: new Date()).save()
				new LicenseUsage(license: xs_lic, userid: 'rapm', checkoutDate: new Date()).save()

				def a_fa = new UploadedFile(name: 'A.txt', content: 'Hello Attachment A'.bytes, mimeType: 'text/plain')
				def a_fb = new UploadedFile(name: 'B.txt', content: 'Hello Attachment B'.bytes, mimeType: 'text/plain')
				new LicenseAttachment(license: xs_lic, name: 'Attachment A', file: a_fa).save()
				new LicenseAttachment(license: xs_lic, name: 'Attachment B', file: a_fb).save()
			}
		}
	}
	def destroy = {
	}
}
