package wanderer2357.redwire.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import wanderer2357.redwire.enumeration.PaymentCardTypeEnum;
import wanderer2357.redwire.enumeration.StatusEnum;

@Entity
@Table(name = "payment_card", indexes = {
	    @Index(name = "idx_client_id", columnList = "client_id"),
	    @Index(name = "idx_id_client", columnList = "id, client_id")
	})
public class PaymentCardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ClientEntity clientEntity;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentCardTypeEnum paymentCardType;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 4)
	@Pattern(regexp = "^[0-9]+$", message = "Last four digits must be numeric")
	private String lastFourDigits;

	@Column(nullable = false)
	@NotBlank
	@Size(max = 2)
	@Pattern(regexp = "^[0-9]+$", message = "Expiration month must be numeric")
	private String expirationMonth;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 4)
	@Pattern(regexp = "^[0-9]+$", message = "Expiration year must be numeric")
	private String expirationYear;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 70)
	private String cardHolderName;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 255)
	private String token;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	StatusEnum status = StatusEnum.ACTIVE;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public PaymentCardEntity() {
	}
	
	public PaymentCardEntity(@NotNull ClientEntity clientEntity,
			@NotNull PaymentCardTypeEnum paymentCardType,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Last four digits must be numeric") @Size(max = 4) String lastFourDigits,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Expiration month must be numeric") @Size(max = 2) String expirationMonth,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Expiration year must be numeric") @Size(max = 2) String expirationYear,
			@NotBlank @Size(max = 70) String cardHolderName,
			@NotBlank @Size(max = 255) String token) {
		this.clientEntity = clientEntity;
		this.paymentCardType = paymentCardType;
		this.lastFourDigits = lastFourDigits;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardHolderName = cardHolderName;
		this.token = token;
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

	public PaymentCardTypeEnum getPaymentCardType() {
		return paymentCardType;
	}

	public void setPaymentCardType(@NotNull PaymentCardTypeEnum paymentCardType) {
		this.paymentCardType = paymentCardType;
	}

	public String getLastFourDigits() {
		return lastFourDigits;
	}

	public void setLastFourDigits(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Last four digits must be numeric") @Size(max = 4) String lastFourDigits) {
		this.lastFourDigits = lastFourDigits;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Expiration month must be numeric") @Size(max = 2) String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Expiration year must be numeric") @Size(max = 2) String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(@NotBlank @Size(max = 70) String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(@NotBlank @Size(max = 255) String token) {
		this.token = token;
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
        PaymentCardEntity that = (PaymentCardEntity) object;
        return this.id.equals(that.id);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
	
	@Override
	public String toString() {
		return "PaymentCardEntity{" +
				"id=" + this.id +
				", clientEntity=" + (this.clientEntity != null ? this.clientEntity.toString() : "null") +
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