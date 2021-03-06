import grails.util.Environment;
import grails.util.GrailsUtil;

// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    test {
        grails.serverURL = "http://localhost:8080/${appName}"
    }

}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console
    // appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
		   
    info 'org.springframework.security.rememberme'
    info 'org.springframework.security.web.authentication.rememberme'
    info 'org.springframework.security.ldap'

    warn   'org.mortbay.log'
}

// Spring Security plugins
grails.plugins.springsecurity.active = true
if (!Environment.current.developmentMode) {
	grails.plugins.springsecurity.providerNames = ['ldapAuthProvider', 'anonymousAuthenticationProvider', 'rememberMeAuthenticationProvider']
	grails.plugins.springsecurity.ldap.context.server = 'ldap://ad.bedag.ch:389'
	grails.plugins.springsecurity.ldap.context.managerDn = 'CN=Siegenthaler Mario,OU=bedag,OU=adUsers,DC=ad,DC=bedag,DC=ch' //TODO technischer User
	grails.plugins.springsecurity.ldap.context.managerPassword = System.getProperty("adManagerPassword") 					//TODO jndi
	grails.plugins.springsecurity.ldap.search.base = 'OU=bedag,OU=adUsers,DC=ad,DC=bedag,DC=ch'
	grails.plugins.springsecurity.ldap.authorities.groupSearchBase = 'OU=adGroups,DC=ad,DC=bedag,DC=ch'
	//generic ActiveDirectory-Settings
	grails.plugins.springsecurity.ldap.authorities.ignorePartialResultException = true
	grails.plugins.springsecurity.ldap.search.filter = "sAMAccountName={0}"
	grails.plugins.springsecurity.ldap.search.searchSubtree = true
	grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = true
	grails.plugins.springsecurity.ldap.authorities.groupSearchFilter = 'member={0}'
	grails.plugins.springsecurity.ldap.search.attributesToReturn = ['sAMAccountName', 'mail', 'name']
	//generic settings
	grails.plugins.springsecurity.ldap.auth.hideUserNotFoundExceptions = false
	grails.plugins.springsecurity.ldap.useRememberMe = true
	grails.plugins.springsecurity.rememberMe.persistent = true
	grails.plugins.springsecurity.rememberMe.cookieName = 'licenseyourself_persistent_auth'
	grails.plugins.springsecurity.rememberMe.key = '1TCi=GI2ox?EFO{'
	grails.plugins.springsecurity.rememberMe.persistentToken.domainClassName = 'licenseyourself.login.PersistentLogin'
	//group mapping
	licenseyourself.groups.admin = 'zg5-001-0001-g'
	licenseyourself.groups.manager = 'zg5-001-0001-g'
} else {
	grails.plugin.excludes = 'springSecurityLdap'
	grails.plugins.springsecurity.providerNames = ['developmentAuthenticationProvider', 'anonymousAuthenticationProvider']
}


