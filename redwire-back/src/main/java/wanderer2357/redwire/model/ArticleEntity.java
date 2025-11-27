package wanderer2357.redwire.model;

import java.math.BigDecimal;
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import wanderer2357.redwire.annotation.Patchable;
import wanderer2357.redwire.enumeration.StatusEnum;

@Entity
@Table(name = "article", indexes = {
	    @Index(name = "idx_code", columnList = "code"),
	    @Index(name = "idx_id_code", columnList = "id, code")
	})
@Embeddable
public class ArticleEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 50)
	private String code;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	@Patchable
	private String label1;
	
	@Column(nullable = false)
	@Size(max = 50)
	@Patchable
	private String label2;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	@Patchable
	private BigDecimal price;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	@Patchable
	private BigDecimal costPrice;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@Patchable
	private StatusEnum status = StatusEnum.ACTIVE;
	
	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	public ArticleEntity() {
	}
	
	public ArticleEntity(Long id) {
		this.id = id;
	}
	
	public ArticleEntity(@NotBlank @Size(max=50) String code,
			@NotBlank @Size(max=50) String label1, 
			@NotNull @Size(max=50) String label2,
			@DecimalMin(value = "0.0", inclusive = true) BigDecimal price, 
			@DecimalMin(value = "0.0", inclusive = true) BigDecimal costPrice) {
		this.code = code;
		this.label1 = label1;
		this.label2 = label2;
		this.price = price;
		this.costPrice = costPrice;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(@NotBlank @Size(max=50) String code) {
		this.code = code;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(@NotBlank @Size(max=50) String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(@NotNull @Size(max=50) String label2) {
		this.label2 = label2;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(@DecimalMin(value = "0.0", inclusive = true) BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(@DecimalMin(value = "0.0", inclusive = true) BigDecimal costPrice) {
		this.costPrice = costPrice;
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
        ArticleEntity that = (ArticleEntity) object;
        return this.id.equals(that.id) && this.code.equals(that.code);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.code);
    }
	
	@Override
	public String toString() {
		return "ArticleEntity{" +
				"id=" + this.id +
				", code='" + this.code + '\'' +
				", label1='" + this.label1 + '\'' +
				", label2='" + this.label2 + '\'' +
				", price=" + this.price + '\'' +
				", costPrice=" + this.costPrice + '\'' +
				", status=" + this.status +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}