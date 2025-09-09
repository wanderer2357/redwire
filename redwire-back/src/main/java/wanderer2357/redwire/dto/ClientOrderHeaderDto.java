package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import wanderer2357.redwire.enumeration.OrderStatusEnum;

public class ClientOrderHeaderDto {
	
	private Long id;
	private Long clientId; 
	private ImmutableClientDto immutableClientDto;
	private Long addressId;
	private ImmutableAddressDto immutableAddressDto;
	private OrderStatusEnum orderStatus = OrderStatusEnum.Pending;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public ClientOrderHeaderDto () {
		
	}
	
	public ClientOrderHeaderDto (Long id,
			Long clientId,
			ImmutableClientDto immutableClientDto,
			Long addressId,
			ImmutableAddressDto immutableAddressDto,
			OrderStatusEnum orderStatus,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.clientId = clientId;
		this.immutableClientDto = immutableClientDto;
		this.addressId = addressId;
		this.immutableAddressDto = immutableAddressDto;
		this.orderStatus = orderStatus;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}
	
	public Long getClientId() {
		return clientId;
	}
	
	public ImmutableClientDto getImmutableClientDto() {
		return immutableClientDto;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public ImmutableAddressDto getImmutableAddressDto() {
		return immutableAddressDto;
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
		ClientOrderHeaderDto that = (ClientOrderHeaderDto) object;
        return this.id.equals(that.id) 
        		&& this.clientId.equals(that.clientId) 
        		&& this.addressId.equals(that.addressId);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientId, this.addressId);
    }
	
	@Override
	public String toString() {
		return "ClientOrderHeaderDTO{" +
				"id=" + this.id +
				"clientId=" + this.clientId +
				", immutableClientDto=" + (this.immutableClientDto != null ? this.immutableClientDto.toString() : "null") +
				", immutableAddressDto=" + (this.immutableAddressDto != null ? this.immutableAddressDto.toString() : "null")  +
				", orderStatus=" + this.orderStatus +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}