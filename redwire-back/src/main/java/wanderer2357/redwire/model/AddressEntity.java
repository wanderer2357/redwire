package wanderer2357.redwire.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address", indexes = {
	    @Index(name = "idx_client_id", columnList = "client_id"),
	    @Index(name = "idx_id_client", columnList = "id, client_id")
	})
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ClientEntity clientEntity;
	
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
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public AddressEntity() {
	}
	
	public AddressEntity(Long id) {
		this.id = id;
	}
	
	public AddressEntity(@NotNull ClientEntity clientEntity,
			@NotBlank @Size(max = 100) String street1,
			@NotNull @Size(max = 100) String street2, 
			@NotBlank @Size(max = 11) String postalCode,
			@NotNull @Size(max = 30) String regionStateProvince,
			@NotBlank @Size(max = 30) String country) {
		
		this.clientEntity = clientEntity;
		this.street1 = street1;
		this.street2 = street2;
		this.postalCode = postalCode;
		this.regionStateProvince = regionStateProvince;
		this.country = country;
		
	}

	public Long getId() {
		return id;
	}
	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(@NotNull ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
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
        AddressEntity that = (AddressEntity) object;
        return this.id.equals(that.id) && this.clientEntity.equals(that.getClientEntity());
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientEntity.getId());
    }
	
	@Override
	public String toString() {
		return "AddressEntity{" +
				"id=" + this.id +
				", clientEntity=" + (this.clientEntity != null ? this.clientEntity.getId() : "null") +
				", street1='" + this.street1 + '\'' +
				", street2='" + this.street2 + '\'' +
				", postalCode=" + this.postalCode + '\'' +
				", regionStateProvince=" + this.regionStateProvince + '\'' +
				", country=" + this.country + '\'' +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}