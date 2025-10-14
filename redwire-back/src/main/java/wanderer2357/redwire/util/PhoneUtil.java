package wanderer2357.redwire.util;

import org.springframework.stereotype.Component;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Component
public class PhoneUtil {
	
	private final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	
	public boolean isValidPhoneNumber(String number) {
	    try {
	        PhoneNumber phoneNumber = phoneUtil.parse(number, "ZZ");
	        return phoneUtil.isValidNumber(phoneNumber);
	    } catch (NumberParseException e) {
	        return false;
	    }
	}
	
	public String extractRegionCode(String number) {
        try {
            PhoneNumber phoneNumber = phoneUtil.parse(number, "ZZ");
            return phoneUtil.getRegionCodeForNumber(phoneNumber);
        } catch (NumberParseException e) {
            return null;
        }
    }

    public String formatToE164(String number) {
        try {
            PhoneNumber phoneNumber = phoneUtil.parse(number, "ZZ");
            return phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            return null;
        }
    }

}
