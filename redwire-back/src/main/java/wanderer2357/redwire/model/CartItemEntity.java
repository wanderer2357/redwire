package wanderer2357.redwire.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import wanderer2357.redwire.model.embeddable.CartItemKey;

@Entity
@Table(name = "cart_item", indexes = {
	    @Index(name = "idx_client_id", columnList = "client_id"),
	    @Index(name = "idx_client_article", columnList = "client_id, article_id")
	})
public class CartItemEntity {

	@EmbeddedId
    private CartItemKey id;
	
	@ManyToOne
	@MapsId("clientId")
	@JoinColumn(name = "client_id", nullable = false)
	private ClientEntity clientEntity;

	@ManyToOne
	@MapsId("articleId")
	@JoinColumn(name = "article_id", nullable = false)
	private ArticleEntity articleEntity;
	
	@Column(nullable = false)
	@Min(value = 1)
	private int quantity;

	public CartItemEntity() {
	}
	
	public CartItemEntity(@NotNull ClientEntity clientEntity,
			@NotNull ArticleEntity articleEntity,
			@Min(value = 1) int quantity) {
		this.id = new CartItemKey(clientEntity.getId(), articleEntity.getId());
		this.clientEntity = clientEntity;
		this.articleEntity = articleEntity;
		this.quantity = quantity;
	}

	public CartItemKey getId() {
		return id;
	}
	
	public ClientEntity getClientEntity() {
		return clientEntity;
	}

	public void setClientEntity(@NotNull ClientEntity clientEntity) {
		this.clientEntity = clientEntity;
	}

	public ArticleEntity getArticleEntity() {
		return articleEntity;
	}

	public void setArticleEntity(@NotNull ArticleEntity articleEntity) {
		this.articleEntity = articleEntity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(@Min(value = 1) int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "CartItemEntity{" +
				"id=" + this.id +
				", clientEntity=" + (this.clientEntity != null ? this.clientEntity.toString() : "null") +
				", articleEntity=" + (this.articleEntity != null ? this.articleEntity.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}