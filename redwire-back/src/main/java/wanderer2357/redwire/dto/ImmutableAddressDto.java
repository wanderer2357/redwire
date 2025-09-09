package wanderer2357.redwire.dto;

import java.util.Objects;

public class ImmutableAddressDto {
	
	private String street1;
	private String street2;
	private String postalCode;
	private String regionStateProvince;
	private String country;
	
	public ImmutableAddressDto(String street1,
			String street2, 
			String postalCode,
			String regionStateProvince,
			String country) {
		this.street1 = street1;
		this.street2 = street2;
		this.postalCode = postalCode;
		this.regionStateProvince = regionStateProvince;
		this.country = country;
		
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
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableAddressDto that = (ImmutableAddressDto) object;
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
		return "ImmutableAddressDto{" +
				"street1='" + this.street1 + '\'' +
				", street2='" + this.street2 + '\'' +
				", postalCode=" + this.postalCode + '\'' +
				", regionStateProvince=" + this.regionStateProvince + '\'' +
				", country=" + this.country + '\'' +
				'}';
	}

}
