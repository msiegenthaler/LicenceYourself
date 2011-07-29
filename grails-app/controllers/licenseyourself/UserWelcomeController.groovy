package licenseyourself

import grails.plugins.springsecurity.Secured;

@Secured(["hasRole('ROLE_USER')"])
class UserWelcomeController {
    def index = { }
}
