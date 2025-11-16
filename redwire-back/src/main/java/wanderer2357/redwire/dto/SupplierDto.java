package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import wanderer2357.redwire.enumeration.StatusEnum;

public class SupplierDto {
	
	@Null
	private Long id;
	@NotBlank
	private String companyName;
	@NotBlank
	private String street1;
	private String street2;
	@NotBlank
	private String postalCode;
	private String regionStateProvince;
	@NotBlank
	private String country;
	@Email
	private String email;
	@NotBlank
	private String phone;
	@NotBlank
	private String representativeFirstname;
	@NotBlank
	private String representativeLastname;
	@NotBlank
	private String representativeEmail;
	@NotBlank
	private String representativePhone;
	private StatusEnum status = StatusEnum.ACTIVE;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public SupplierDto() {
		
	}
	
	public SupplierDto(Long id,
			String companyName,
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
			String representativePhone,
			StatusEnum status,
			LocalDateTime createdAt,
			LocalDateTime updatedAt
			) {
		this.id = id;
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
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRepresentativeFirstname() {
		return representativeFirstname;
	}

	public void setRepresentativeFirstname(String representativeFirstname) {
		this.representativeFirstname = representativeFirstname;
	}

	public String getRepresentativeLastname() {
		return representativeLastname;
	}

	public void setRepresentativeLastname(String representativeLastname) {
		this.representativeLastname = representativeLastname;
	}

	public String getRepresentativeEmail() {
		return representativeEmail;
	}

	public void setRepresentativeEmail(String representativeEmail) {
		this.representativeEmail = representativeEmail;
	}

	public String getRepresentativePhone() {
		return representativePhone;
	}

	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
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
        SupplierDto that = (SupplierDto) object;
        return this.id.equals(that.id) && this.companyName.equals(that.companyName);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.companyName);
    }
	
	@Override
	public String toString() {
		return "SupplierDTO{" +
				"id=" + this.id + 
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
				", status=" + this.status +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}
	
}