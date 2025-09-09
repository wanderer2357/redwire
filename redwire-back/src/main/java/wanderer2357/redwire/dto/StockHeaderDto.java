package wanderer2357.redwire.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class StockHeaderDto {
	
	private Long id;
	private Long articleId;
	private int min;
	private int max;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public StockHeaderDto(){
		this.min = 0;
	    this.max = 0;
	}
	
	public StockHeaderDto(Long id,
			Long articleId,
			int min,
			int max,
			LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.id = id;
		this.articleId = articleId;
		this.min = min;
		this.max = max;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setMinAndMax(int min, int max) {
		this.min = min;
		this.max = max;
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
        StockHeaderDto that = (StockHeaderDto) object;
        return this.id.equals(that.id) && this.articleId.equals(that.articleId);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.articleId);
    }
	
	@Override
	public String toString() {
		return "StockHeaderDTO{" +
				"id=" + this.id +
				", articleId=" + this.articleId +
				", min=" + this.min +
				", max=" + this.max +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}