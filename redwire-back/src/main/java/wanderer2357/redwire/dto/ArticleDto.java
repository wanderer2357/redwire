package wanderer2357.redwire.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import wanderer2357.redwire.enumeration.StatusEnum;

public class ArticleDto {
	
	@Null
	private Long id;
	@NotBlank
	private String code;
	@NotBlank
	private String label1;
	@NotNull
	private String label2;
	@Positive
	private BigDecimal price;
	@Positive
	private BigDecimal costPrice;
	@NotNull
	private StatusEnum status = StatusEnum.ACTIVE;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public ArticleDto() {
	}
	
	public ArticleDto(Long id,
			String code,
			String label1,
			String label2,
			BigDecimal price,
			BigDecimal costPrice,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.code = code;
		this.label1 = label1;
		this.label2 = label2;
		this.price = price;
		this.costPrice = costPrice;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
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
        ArticleDto that = (ArticleDto) object;
        return this.id.equals(that.id) && this.code.equals(that.code);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.code);
    }
	
	@Override
	public String toString() {
		return "ArticleDTO{" +
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