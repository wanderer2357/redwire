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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "stock_detail", indexes = {
	    @Index(name = "idx_article_id", columnList = "article_id"),
	    @Index(name = "idx_id_article", columnList = "id, article_id")
	})
public class StockDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ArticleEntity articleEntity;
	
	@Column(nullable = false)
	@Size(max = 20)
	@NotBlank
	private String location;
	
	@Column(nullable = false)
	@Min(value =  1)
	private int quantity;
	
	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;
	
	public StockDetailEntity () {
		
	}
	
	public StockDetailEntity (@NotNull ArticleEntity articleEntity,
			@NotBlank @Size(max = 20) String location,
			@NotNull @Min(value = 1) int quantity) {
		this.articleEntity = articleEntity;
		this.location = location;
		this.quantity = quantity;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(@NotBlank @Size(max = 20) String location) {
		this.location = location;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(@NotNull @Min(value = 1) int quantity) {
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
        StockDetailEntity that = (StockDetailEntity) object;
        return this.id.equals(that.id) && this.articleEntity.equals(that.articleEntity);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.articleEntity.getId());
    }

	@Override
	public String toString() {
		return "StockDetailEntity{" +
				"id=" + this.id +
				", articleEntity=" + (this.articleEntity != null ? this.articleEntity.toString() : "null") +
				", location='" + this.location + '\'' +
				", createdAt=" + this.createdAt +
				", updatedAt=" + this.updatedAt +
				'}';
	}

}