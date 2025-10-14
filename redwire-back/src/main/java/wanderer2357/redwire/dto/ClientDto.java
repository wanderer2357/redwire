package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import wanderer2357.redwire.enumeration.StatusEnum;

public class ClientDto {

	private Long id;
	@NotBlank
	private String firstname;
	@NotBlank
	private String lastname;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String phone;
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min = 12)
	private String clearPassword;
	private StatusEnum status = StatusEnum.ACTIVE;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
	public ClientDto() {
	}
	
	public ClientDto(Long id,
			String firstname,
			String lastname,
			String email, 
			String phone,
			String clearPassword,
			StatusEnum status,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.clearPassword = clearPassword;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	
	public String getClearPassword() {
		return clearPassword;
	}
	
	public void setClearPassword(String clearPassword) {
		this.clearPassword = clearPassword;
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
        ClientDto that = (ClientDto) object;
        return this.id.equals(that.id) && this.email.equals(that.email);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.email);
    }
	
	@Override
	public String toString() {
		return "ClientDTO{" +
				"id=" + this.id +
				", firstname='" + this.firstname + '\'' +
				", lastname='" + this.lastname + '\'' +
				", email='" + this.email + '\'' +
				", phone='" + this.phone + '\'' +
				", status=" + this.status +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}