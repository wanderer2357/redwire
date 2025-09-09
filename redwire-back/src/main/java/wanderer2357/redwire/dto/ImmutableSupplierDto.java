package wanderer2357.redwire.dto;

import java.util.Objects;

public class ImmutableSupplierDto {
	
	private String companyName;
	private String street1;
	private String street2;
	private String postalCode;
	private String regionStateProvince;
	private String country;
	private String email;
	private String phone;
	private String representativeFirstname;
	private String representativeLastname;
	private String representativeEmail;
	private String representativePhone;
	
	public ImmutableSupplierDto() {
	}
	
	public ImmutableSupplierDto(String companyName,
			String street1,
			String street2,
			String postalCode,
			String regionStateProvince,
			String country,
			String email,
			String phone,
			String representativeFirstname,
			String representativeLastname,
			String representativeEmail,
			String representativePhone
			) {
		this.companyName = companyName;
		this.street1 = street1;
		this.street2 = street2;
		this.postalCode = postalCode;
		this.regionStateProvince = regionStateProvince;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.representativeFirstname = representativeFirstname;
		this.representativeLastname = representativeLastname;
		this.representativeEmail = representativeEmail;
		this.representativePhone = representativePhone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getStreet1() {
		return street1;
	}

	public String getStreet2() {
		return street2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getRegionStateProvince() {
		return regionStateProvince;
	}

	public String getCountry() {
		return country;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getRepresentativeFirstname() {
		return representativeFirstname;
	}

	public String getRepresentativeLastname() {
		return representativeLastname;
	}

	public String getRepresentativeEmail() {
		return representativeEmail;
	}

	public String getRepresentativePhone() {
		return representativePhone;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableSupplierDto that = (ImmutableSupplierDto) object;
        return (this.companyName.equals(that.companyName)
        		&& this.street1.equals(that.street1)
        		&& this.street2.equals(that.street2)
        		&& this.postalCode.equals(that.postalCode)
        		&& this.regionStateProvince.equals(that.regionStateProvince)
        		&& this.country.equals(that.country)
        		&& this.email.equals(that.email)
        		&& this.phone.equals(that.phone)
        		&& this.representativeFirstname.equals(that.representativeFirstname)
        		&& this.representativeLastname.equals(that.representativeLastname)
        		&& this.representativeEmail.equals(that.representativeEmail)
        		&& this.representativePhone.equals(that.representativePhone));
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.companyName, this.street1, this.street2, this.postalCode, this.regionStateProvince,
        		this.country, this.email, this.phone, this.representativeFirstname, this.representativeLastname,
        		this.representativeEmail, this.representativePhone);
    }
	
	@Override
	public String toString() {
		return "ImmutableSupplierDto{" +
				", companyName='" + this.companyName + '\'' +
				", street1='" + this.street1 + '\'' +
				", street2='" + this.street2 + '\'' +
				", postalCode='" + this.postalCode + '\'' +
				", regionStateProvince='" + this.regionStateProvince + '\'' +
				", country='" + this.country + '\'' +
				", email='" + this.email + '\'' +
				", phone='" + this.phone + '\'' +
				", representativeFirstname='" + this.representativeFirstname + '\'' +
				", representativeLastname='" + this.representativeLastname + '\'' +
				", representativeEmail='" + this.representativeEmail + '\'' +
				", representativePhone='" + this.representativePhone + '\'' +
				'}';
	}
	

}
