package licenseyourself

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_USER'])
class UserWelcomeController {
    def index = { }
}
