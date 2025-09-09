package wanderer2357.redwire.dto;

import java.util.Objects;

public class CartItemDto {
	
	private Long clientId;
	private ArticleDto articleDto;
	private int quantity;

	public CartItemDto() {
	}
	
	public CartItemDto(Long clientId, ArticleDto articleDto, int quantity) {
		this.clientId = clientId;
		this.articleDto = articleDto;
		this.quantity = quantity;
	}
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public ArticleDto getArticleDto() {
		return articleDto;
	}

	public void setArticleDto(ArticleDto articleDto) {
		this.articleDto = articleDto;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CartItemDto that = (CartItemDto) object;
        return this.clientId.equals(that.clientId) && this.articleDto.equals(that.articleDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.clientId, this.articleDto.getId());
    }
    
    @Override
	public String toString() {
		return "CartItemDTO{" +
				", clientId=" + this.clientId +
				", articleDto=" + (this.articleDto != null ? this.articleDto.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}