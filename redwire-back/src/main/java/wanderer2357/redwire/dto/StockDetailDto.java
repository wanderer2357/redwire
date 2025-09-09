package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockDetailDto {
	
	private Long id;
	private ArticleDto articleDto;
	private String location;
	private int quantity;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public StockDetailDto () {
		
	}
	
	public StockDetailDto (Long id,
			ArticleDto articleDto,
			String location,
			int quantity,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.articleDto = articleDto;
		this.location = location;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public ArticleDto getArticleDto() {
		return articleDto;
	}

	public void setArticleDto(ArticleDto articleDto) {
		this.articleDto = articleDto;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
        StockDetailDto that = (StockDetailDto) object;
        return this.id.equals(that.id) && this.articleDto.equals(that.articleDto);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.articleDto);
    }

	@Override
	public String toString() {
		return "StockDetailDTO{" +
				"id=" + this.id +
				", articleDto=" + (this.articleDto != null ? this.articleDto.toString() : "null") +
				", location='" + this.location + '\'' +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}