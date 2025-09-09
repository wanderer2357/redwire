package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;


public class AddressDto {
	
    private Long id;
    private Long clientId;
    private String street1;
    private String street2;
    private String postalCode;
	private String regionStateProvince;
    private String country;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

	public AddressDto(Long id,
			Long clientId,
			String street1,
			String street2,
			String postalCode,
			String regionStateProvince,
			String country,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.clientId = clientId;
		this.street1 = street1;
		this.street2 = street2;
		this.postalCode = postalCode;
		this.regionStateProvince = regionStateProvince;
		this.country = country;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public String getStreet1() {
		return street1;
	}
	
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}
	
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegionStateProvince() {
		return regionStateProvince;
	}
	
	public void setRegionStateProvince(String regionStateProvince) {
		this.regionStateProvince = regionStateProvince;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        AddressDto that = (AddressDto) object;
        return this.id.equals(that.id) && this.clientId.equals(that.clientId);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
	
	@Override
	public String toString() {
		return "AddressDTO{" +
				"id=" + this.id +
				", clientId='" + this.clientId + '\'' +
				", street1='" + this.street1 + '\'' +
				", street2='" + this.street2 + '\'' +
				", postalCode=" + this.postalCode +
				", regionStateProvince=" + this.regionStateProvince +
				", country=" + this.country +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}