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
import wanderer2357.redwire.model.embeddable.ImmutableSupplier;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "supplier_order_header", indexes = { @Index(name = "idx_supplier_id", columnList = "supplier_id"),
		@Index(name = "idx_id_supplier", columnList = "id, supplier_id") })
public class SupplierOrderHeaderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private SupplierEntity supplierEntity;

	@Embedded
	private ImmutableSupplier immutableSupplier;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusEnum orderStatus = OrderStatusEnum.Pending;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public SupplierOrderHeaderEntity() {
	}
	
	public SupplierOrderHeaderEntity(Long id) {
		this.id = id;
	}

	public SupplierOrderHeaderEntity(@NotNull SupplierEntity supplierEntity,
			@NotNull OrderStatusEnum orderStatus) {
		this.supplierEntity = supplierEntity;
		this.immutableSupplier = new ImmutableSupplier(
		        supplierEntity.getCompanyName(),
		        supplierEntity.getEmail(),
		        supplierEntity.getPhone(),
		        supplierEntity.getStreet1(),
		        supplierEntity.getStreet2(),
		        supplierEntity.getPostalCode(),
		        supplierEntity.getRegionStateProvince(),
		        supplierEntity.getCountry(),
		        supplierEntity.getRepresentativeFirstname(),
		        supplierEntity.getRepresentativeLastname(),
		        supplierEntity.getRepresentativeEmail(),
		        supplierEntity.getRepresentativePhone()
		    );
	}

	public Long getId() {
		return id;
	}

	public SupplierEntity getSupplierEntity() {
		return supplierEntity;
	}

	public void setSupplierEntity(@NotNull SupplierEntity supplierEntity) {
		this.supplierEntity = supplierEntity;
	}

	public ImmutableSupplier getImmutableSupplier() {
		return immutableSupplier;
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
		SupplierOrderHeaderEntity that = (SupplierOrderHeaderEntity) object;
		return this.id.equals(that.id) && this.supplierEntity.equals(that.supplierEntity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.supplierEntity.getId());
	}

	@Override
	public String toString() {
		return "SupplierOrderHeaderEntity{" + 
				"id=" + this.id + 
				", supplierEntity=" + (this.supplierEntity != null ? this.supplierEntity.toString() : "null") +
				", immutableSupplierEntity=" + (this.immutableSupplier != null ? this.immutableSupplier.toString() : "null") +
				", orderStatus=" + this.orderStatus + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + '}';
	}

}