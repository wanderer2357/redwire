package wanderer2357.redwire.model.embeddable;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class ImmutableAddress {
	
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
	
	public ImmutableAddress() {
	}
	
	public ImmutableAddress(@NotBlank @Size(max = 100) String street1,
			@NotNull @Size(max = 100) String street2, 
			@NotBlank @Size(max = 11) String postalCode,
			@NotNull @Size(max = 30) String regionStateProvince,
			@NotBlank @Size(max = 30) String country) {
		this.street1 = street1;
		this.street2 = street2;
		this.postalCode = postalCode;
		this.regionStateProvince = regionStateProvince;
		this.country = country;
		
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
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableAddress that = (ImmutableAddress) object;
        return (this.street1.equals(that.street1)
        		&& this.street1.equals(that.street1)
        		&& this.street2.equals(that.street2)
        		&& this.postalCode.equals(that.postalCode)
        		&& this.regionStateProvince.equals(that.regionStateProvince)
        		&& this.country.equals(that.country));
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.street1, this.street2, this.postalCode, this.regionStateProvince, this.country);
    }
	
	@Override
	public String toString() {
		return "ImmutableAddress{" +
				"street1='" + this.street1 + '\'' +
				", street2='" + this.street2 + '\'' +
				", postalCode=" + this.postalCode + '\'' +
				", regionStateProvince=" + this.regionStateProvince + '\'' +
				", country=" + this.country + '\'' +
				'}';
	}

}
