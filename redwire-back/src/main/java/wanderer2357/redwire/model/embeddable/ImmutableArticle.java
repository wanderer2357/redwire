package wanderer2357.redwire.model.embeddable;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class ImmutableArticle {
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 50)
	private String code;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	private String label1;
	
	@Column(nullable = false)
	@Size(max = 50)
	private String label2;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal price;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal costPrice;
	
	public ImmutableArticle() {
	}
	
	public ImmutableArticle(@NotBlank @Size(max=50) String code,
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
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableArticle that = (ImmutableArticle) object;
        return (this.code.equals(that.code)
        		&& this.label1.equals(that.label1)
        		&& this.label2.equals(that.label2)
        		&& this.price.equals(that.price)
        		&& this.costPrice.equals(that.costPrice));
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.code, this.label1, this.label2, this.price, this.costPrice);
    }
	
	@Override
	public String toString() {
		return "ImmutableArticle{" +
				", code='" + this.code + '\'' +
				", label1='" + this.label1 + '\'' +
				", label2='" + this.label2 + '\'' +
				", price=" + this.price + '\'' +
				", costPrice=" + this.costPrice + '\'' +
				'}';
	}

}
