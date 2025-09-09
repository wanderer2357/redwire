package wanderer2357.redwire.dto;

import java.util.Objects;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SupplierOrderDetailDto {
	
	private Long id;
	private Long supplierOrderHeaderId;
	private Long articleId;
	private ImmutableArticleDto immutableArticleDto;
	private int quantity;
	
	public SupplierOrderDetailDto() {
	}
	
	public SupplierOrderDetailDto(Long id,
			Long supplierOrderHeaderId,
			Long articleId,
			ImmutableArticleDto immutableArticleDto,
			int quantity) {
		this.id = id;
		this.supplierOrderHeaderId = supplierOrderHeaderId;
		this.articleId = articleId;
		this.immutableArticleDto = immutableArticleDto;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public Long getSupplierOrderHeaderId() {
		return supplierOrderHeaderId;
	}
	
	public Long getArticleId() {
		return articleId;
	}

	public ImmutableArticleDto getImmutableArticleDto() {
		return immutableArticleDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(@NotNull @Min(value =  1) int quantity) {
		this.quantity = quantity;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        SupplierOrderDetailDto that = (SupplierOrderDetailDto) object;
        return this.id.equals(that.id) && this.supplierOrderHeaderId.equals(that.supplierOrderHeaderId)
        		&& this.articleId.equals(that.articleId);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplierOrderHeaderId, this.articleId);
    }

	@Override
	public String toString() {
		return "SupplierOrderDetailDTO{" +
				"id=" + this.id +
				", supplierOrderHeaderId=" + this.supplierOrderHeaderId +
				", articleId=" + this.articleId +
				", immutableArticleDto=" + (this.immutableArticleDto != null ? this.immutableArticleDto.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}