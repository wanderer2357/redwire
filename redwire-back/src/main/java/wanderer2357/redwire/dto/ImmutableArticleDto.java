package wanderer2357.redwire.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ImmutableArticleDto {
	
	private String code;
	private String label1;
	private String label2;
	private BigDecimal price;
	private BigDecimal costPrice;
	
	public ImmutableArticleDto(String code,
			String label1, 
			String label2,
			BigDecimal price, 
			BigDecimal costPrice) {
		this.code = code;
		this.label1 = label1;
		this.label2 = label2;
		this.price = price;
		this.costPrice = costPrice;
	}

	public String getCode() {
		return code;
	}

	public String getLabel1() {
		return label1;
	}

	public String getLabel2() {
		return label2;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableArticleDto that = (ImmutableArticleDto) object;
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
		return "ImmutableArticleDto{" +
				", code='" + this.code + '\'' +
				", label1='" + this.label1 + '\'' +
				", label2='" + this.label2 + '\'' +
				", price=" + this.price + '\'' +
				", costPrice=" + this.costPrice + '\'' +
				'}';
	}

}
