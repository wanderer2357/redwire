package wanderer2357.redwire.dto;

import java.util.Objects;

public class ClientOrderDetailDto {
	
	private Long id;
	private Long clientOrderHeaderId;
	private Long articleId;
	private ImmutableArticleDto immutableArticleDto;
	private int quantity;
	
	public ClientOrderDetailDto() {
	}
	
	public ClientOrderDetailDto(Long id,
			Long clientOrderHeaderId,
			Long articleId,
			ImmutableArticleDto immutableArticleDto,
			int quantity) {
		this.id = id;
		this.clientOrderHeaderId = clientOrderHeaderId;
		this.articleId = articleId;
		this.immutableArticleDto = immutableArticleDto;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public Long getClientOrderHeaderId() {
		return clientOrderHeaderId;
	}
	
	public Long getArticleId() {
		return id;
	}

	public ImmutableArticleDto getImmutableArticleDto() {
		return immutableArticleDto;
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
        if (object == null || this.getClass() != object.getClass()) return false;
        ClientOrderDetailDto that = (ClientOrderDetailDto) object;
        return this.id.equals(that.id) && this.clientOrderHeaderId.equals(that.clientOrderHeaderId)
        		&& this.articleId.equals(that.articleId);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientOrderHeaderId, this.articleId);
    }

	@Override
	public String toString() {
		return "ClientOrderDetailDTO{" +
				"id=" + this.id +
				", clientOrderHeaderId=" + this.clientOrderHeaderId +
				", articleId=" + this.articleId +
				", immutableArticleDto=" + (this.immutableArticleDto != null ? this.immutableArticleDto.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}