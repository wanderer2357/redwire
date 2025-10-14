package wanderer2357.redwire.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	private final Pattern EMAIL_REGEX = Pattern.compile(
		    "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@" +
		    "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$",
		    Pattern.UNICODE_CHARACTER_CLASS
		);
	
	public boolean isValidEmail(String email) {
	    if (email == null) return false;
	    Matcher matcher = EMAIL_REGEX.matcher(email);
	    return matcher.matches();
	}

}
