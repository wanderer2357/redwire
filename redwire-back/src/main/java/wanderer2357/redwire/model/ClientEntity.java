package wanderer2357.redwire.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.enumeration.StatusEnum;

@Entity
@Table(name = "client", indexes = {
	    @Index(name = "idx_email", columnList = "email"),
	    @Index(name = "idx_id_email", columnList = "id, email"),
	    @Index(name = "idx_phone", columnList = "phone"),
	    @Index(name = "idx_id_phone", columnList = "id, phone"),
	    @Index(name = "idx_email_phone", columnList = "email, phone")
	})
@Embeddable
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	@Patchable
	private String firstname;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	@Patchable
	private String lastname;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 254)
	@Email
	@Patchable
	private String email;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric")
	@Patchable
	private String phone;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 128)
	private String passwordHash;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Patchable
	private StatusEnum status = StatusEnum.ACTIVE;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	
	public ClientEntity() {
	}
	
	public ClientEntity(Long id) {
		this.id = id;
	}
	
	public ClientEntity(@NotBlank @Size(max=50) String firstname,
			@NotBlank @Size(max=50) String lastname,
			@NotBlank @Email @Size(max=254) String email,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric") @Size(max=20) String phone,
			@NotBlank @Size(max=255) String passwordHash) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.passwordHash = passwordHash;
	}
	
	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(@NotBlank @Size(max=50) String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(@NotBlank @Size(max=50) String lastname) {
		this.lastname = lastname;
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

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(@NotBlank @Size(max=255) String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(@NotNull StatusEnum status) {
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
        ClientEntity that = (ClientEntity) object;
        return this.id.equals(that.id) && this.email.equals(that.email);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.email);
    }
	
	@Override
	public String toString() {
		return "ClientEntity{" +
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