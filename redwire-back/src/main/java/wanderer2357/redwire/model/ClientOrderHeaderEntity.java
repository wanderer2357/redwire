package wanderer2357.redwire.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import wanderer2357.redwire.enumeration.OrderStatusEnum;
import wanderer2357.redwire.model.embeddable.ImmutableAddress;
import wanderer2357.redwire.model.embeddable.ImmutableClient;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "client_order_header", indexes = {
	    @Index(name = "idx_client_id", columnList = "client_id"),
	    @Index(name = "idx_id_client", columnList = "id, client_id")
	})
public class ClientOrderHeaderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private ClientEntity clientEntity;
	
	@Embedded
	private ImmutableClient immutableClient;
	
	@ManyToOne
	@JoinColumn(name = "address_id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private AddressEntity addressEntity;
	
	@Embedded
	private ImmutableAddress immutableAddress;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum orderStatus = OrderStatusEnum.Pending;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	public ClientOrderHeaderEntity () {	
	}
	
	public ClientOrderHeaderEntity(Long id) {
		this.id = id;
	}
	
	public ClientOrderHeaderEntity (@NotNull ClientEntity clientEntity,
			@NotNull AddressEntity addressEntity,
			@NotNull OrderStatusEnum orderStatus) {
		this.clientEntity = clientEntity;
		this.immutableClient = new ImmutableClient(clientEntity.getFirstname(), 
				clientEntity.getLastname(), 
				clientEntity.getEmail(), 
				clientEntity.getPhone());
		this.addressEntity = addressEntity;
		this.immutableAddress = new ImmutableAddress(addressEntity.getStreet1(), 
				addressEntity.getStreet2(), 
				addressEntity.getPostalCode(), 
				addressEntity.getRegionStateProvince(), 
				addressEntity.getCountry());
		this.orderStatus = orderStatus;
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
	
	public ImmutableClient getImmutableClient() {
		return immutableClient;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(@NotNull AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}

	public ImmutableAddress getImmutableAddress() {
		return immutableAddress;
	}
	
	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(@NotNull OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
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
		ClientOrderHeaderEntity that = (ClientOrderHeaderEntity) object;
        return this.id.equals(that.id) && this.clientEntity.equals(that.clientEntity);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientEntity.getId());
    }
	
	@Override
	public String toString() {
		return "ClientOrderHeaderEntity{" +
				"id=" + this.id +
				", clientEntity=" + (this.clientEntity != null ? this.clientEntity.toString() : "null") +
				", immutableClient=" + (this.immutableClient != null ? this.immutableClient.toString() : "null") +
				", addressEntity=" + (this.addressEntity != null ? this.addressEntity.toString() : "null")  +
				", immutableAddress=" + (this.immutableAddress != null ? this.immutableAddress.toString() : "null")  +
				", orderStatus=" + this.orderStatus +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}