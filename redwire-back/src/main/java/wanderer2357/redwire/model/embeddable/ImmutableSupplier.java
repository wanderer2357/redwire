package wanderer2357.redwire.model.embeddable;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
public class ImmutableSupplier {
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 255)
	private String companyName;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 100)
	private String street1;
	
	@Column(nullable = false)
	@Size(max = 100)
	private String street2;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 11)
	private String postalCode;
	
	@Column(nullable = false)
	@Size(max = 30)
	private String regionStateProvince;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 30)
	private String country;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 254)
	@Email
	private String email;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric")
	private String phone;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	private String representativeFirstname;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	private String representativeLastname;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 254)
	@Email
	private String representativeEmail;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "^[0-9]+$", message = "Representative phone number must be numeric")
	private String representativePhone;
	
	public ImmutableSupplier() {
	}
	
	public ImmutableSupplier(@NotBlank @Size(max = 255) String companyName,
			@NotBlank @Size(max = 100) String street1,
			@NotNull @Size(max = 100) String street2,
			@NotBlank @Size(max = 11) String postalCode,
			@NotNull @Size(max = 30) String regionStateProvince,
			@NotBlank @Size(max = 30) String country,
			@NotBlank @Email @Size(max=254) String email,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric") @Size(max=20) String phone,
			@NotNull @Size(max=50) String representativeFirstname,
			@NotNull @Size(max=50) String representativeLastname,
			@NotNull @Email @Size(max=254) String representativeEmail,
			@NotNull @Pattern(regexp = "^[0-9]+$", message = "Representative phone number must be numeric") @Size(max=20) String representativePhone
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

	public void setCompanyName(@NotBlank @Size(max = 100) String companyName) {
		this.companyName = companyName;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(@NotBlank @Size(max = 100) String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(@NotNull @Size(max = 100) String street2) {
		this.street2 = street2;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(@NotBlank @Size(max = 11) String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegionStateProvince() {
		return regionStateProvince;
	}

	public void setRegionStateProvince(@NotNull @Size(max = 30) String regionStateProvince) {
		this.regionStateProvince = regionStateProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(@NotBlank @Size(max = 30) String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email @Size(max=254) String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric") @Size(max=20) String phone) {
		this.phone = phone;
	}

	public String getRepresentativeFirstname() {
		return representativeFirstname;
	}

	public void setRepresentativeFirstname(@NotNull @Size(max=50) String representativeFirstname) {
		this.representativeFirstname = representativeFirstname;
	}

	public String getRepresentativeLastname() {
		return representativeLastname;
	}

	public void setRepresentativeLastname(@NotNull @Size(max=50) String representativeLastname) {
		this.representativeLastname = representativeLastname;
	}

	public String getRepresentativeEmail() {
		return representativeEmail;
	}

	public void setRepresentativeEmail(@NotNull @Email @Size(max=254) String representativeEmail) {
		this.representativeEmail = representativeEmail;
	}

	public String getRepresentativePhone() {
		return representativePhone;
	}

	public void setRepresentativePhone(@NotNull @Pattern(regexp = "^[0-9]+$", message = "Representative phone number must be numeric") @Size(max=20) String representativePhone) {
		this.representativePhone = representativePhone;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableSupplier that = (ImmutableSupplier) object;
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
		return "ImmutableSupplier{" +
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