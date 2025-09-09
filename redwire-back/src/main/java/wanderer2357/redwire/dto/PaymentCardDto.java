package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import wanderer2357.redwire.enumeration.PaymentCardTypeEnum;
import wanderer2357.redwire.enumeration.StatusEnum;

public class PaymentCardDto {
	
	private Long id;
	private Long clientId;
	private PaymentCardTypeEnum paymentCardType;
	private String lastFourDigits;
	private String expirationMonth;
	private String expirationYear;
	private String cardHolderName;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String token;
	StatusEnum status = StatusEnum.ACTIVE;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public PaymentCardDto() {
		
	}
	
	public PaymentCardDto(Long id,
			Long clientId,
			PaymentCardTypeEnum paymentCardType,
			String lastFourDigits,
			String expirationMonth,
			String expirationYear,
			String cardHolderName,
			String token,
			StatusEnum status,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.clientId = clientId;
		this.paymentCardType = paymentCardType;
		this.lastFourDigits = lastFourDigits;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardHolderName = cardHolderName;
		this.token = token;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public PaymentCardTypeEnum getPaymentCardType() {
		return paymentCardType;
	}

	public void setPaymentCardType(PaymentCardTypeEnum paymentCardType) {
		this.paymentCardType = paymentCardType;
	}

	public String getLastFourDigits() {
		return lastFourDigits;
	}

	public void setLastFourDigits(String lastFourDigits) {
		this.lastFourDigits = lastFourDigits;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
        PaymentCardDto that = (PaymentCardDto) object;
        return this.id.equals(that.id);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
	
	@Override
	public String toString() {
		return "PaymentCardDTO{" +
				"id=" + this.id +
				", clientId=" + this.clientId +
				", paymentCardType='" + this.paymentCardType + '\'' +
				", lastFourDigits='" + this.lastFourDigits + '\'' +
				", expirationMonth='" + this.expirationMonth + '\'' +
				", expirationYear='" + this.expirationYear + '\'' +
				", cardHolderName='" + this.cardHolderName + '\'' +
				", token='" + this.token + '\'' +
				", status=" + this.status +
				'}';
	}
	
}