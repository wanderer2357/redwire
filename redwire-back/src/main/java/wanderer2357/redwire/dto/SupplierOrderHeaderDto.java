package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import wanderer2357.redwire.enumeration.OrderStatusEnum;

public class SupplierOrderHeaderDto {

	private Long id;
	private Long supplierId;
	private ImmutableSupplierDto immutableSupplierDto;
	private OrderStatusEnum orderStatus;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public SupplierOrderHeaderDto() {
	}

	public SupplierOrderHeaderDto(Long id,
			Long supplierId,
			ImmutableSupplierDto immutableSupplierDto,
			OrderStatusEnum orderStatus,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.supplierId = supplierId;
		this.immutableSupplierDto = immutableSupplierDto;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}
	
	public Long getSupplierId() {
		return supplierId;
	}

	public ImmutableSupplierDto getImmutableSupplierDto() {
		return immutableSupplierDto;
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
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
		SupplierOrderHeaderDto that = (SupplierOrderHeaderDto) object;
		return this.id.equals(that.id) && this.supplierId.equals(that.supplierId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.supplierId);
	}

	@Override
	public String toString() {
		return "SupplierOrderHeaderDTO{" + 
				"id=" + this.id +
				", supplierId=" + this.supplierId +
				", immutableSupplierDto="+ (this.immutableSupplierDto != null ? this.immutableSupplierDto.toString() : "null") +
				", orderStatus=" + this.orderStatus +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt + '}';
	}

}