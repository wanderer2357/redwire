package wanderer2357.redwire.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stock_header", indexes = {
	    @Index(name = "idx_article_id", columnList = "article_id"),
	    @Index(name = "idx_id_article", columnList = "id, article_id")
	})
public class StockHeaderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "article_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ArticleEntity articleEntity;
	
	@Column(nullable = false)
	@NotNull
	@Min(value =  0)
	private int min;
	
	@Column(nullable = false)
	@NotNull
	@Min(value =  0)
	private int max;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public StockHeaderEntity(){
		this.min = 0;
	    this.max = 0;
	}
	
	public StockHeaderEntity(@NotNull ArticleEntity articleEntity,
			@Min(value =  0) int min,
			@Min(value =  0) int max) {
		if (max < min) {
            throw new IllegalArgumentException("max should be superior to min");
        }
		this.articleEntity = articleEntity;
		this.min = min;
		this.max = max;
		
	}
	
	public Long getId() {
		return id;
	}

	public ArticleEntity getArticleEntity() {
		return articleEntity;
	}

	public void setArticleEntity(@NotNull ArticleEntity articleEntity) {
		this.articleEntity = articleEntity;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public void setMinAndMax(@Min(value =  0) int min, @Min(value =  0) int max) {
		if (max < min) {
            throw new IllegalArgumentException("max should be superior to min");
        }
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
        StockHeaderEntity that = (StockHeaderEntity) object;
        return this.id.equals(that.id) && this.articleEntity.equals(that.articleEntity);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.articleEntity.getId());
    }
	
	@Override
	public String toString() {
		return "StockHeaderEntity{" +
				"id=" + this.id +
				", article=" + (this.articleEntity != null ? this.articleEntity.toString() : "null") +
				", min=" + this.min +
				", max=" + this.max +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}