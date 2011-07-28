package licenseyourself

import groovy.lang.Immutable;

/**
 * Representation of a user. 
 */
interface User {
	String getUserid()
	String getName()
	String getEmail()
}
