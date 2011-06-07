package licenseyourself

import groovy.lang.Immutable;

@Immutable
class User {
	String userid
	String name
	String email

	String toString() {
		name
	}
}
