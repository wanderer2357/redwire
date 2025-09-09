package wanderer2357.redwire.model;

import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import wanderer2357.redwire.model.embeddable.ImmutableArticle;

@Entity
@Table(name = "client_order_detail", indexes = {
	    @Index(name = "idx_client_order", columnList = "client_order_id"),
	    @Index(name = "idx_id_client_order", columnList = "id, client_order_id")
	})
public class ClientOrderDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "client_order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private ClientOrderHeaderEntity clientOrderHeaderEntity;
	
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private ArticleEntity articleEntity;
	
	@Embedded
	private ImmutableArticle immutableArticle;
	
	@Column(nullable = false)
	@Min(value =  1)
	private int quantity;
	
	public ClientOrderDetailEntity() {
	}
	
	public ClientOrderDetailEntity(@NotNull ClientOrderHeaderEntity clientOrderHeaderEntity,
			@NotNull ArticleEntity articleEntity,
			@NotNull @Min(value =  1) int quantity) {
		this.clientOrderHeaderEntity = clientOrderHeaderEntity;
		this.articleEntity = articleEntity;
		this.immutableArticle = new ImmutableArticle(articleEntity.getCode(),
				articleEntity.getLabel1(),
				articleEntity.getLabel2(), 
				articleEntity.getPrice(),
				articleEntity.getCostPrice());
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public ClientOrderHeaderEntity getClientOrderHeaderEntity() {
		return clientOrderHeaderEntity;
	}

	public void setClientOrderHeaderEntity(@NotNull ClientOrderHeaderEntity clientOrderHeaderEntity) {
		this.clientOrderHeaderEntity = clientOrderHeaderEntity;
	}

	public ArticleEntity getArticleEntity() {
		return articleEntity;
	}

	public void setArticleEntity(@NotNull ArticleEntity articleEntity) {
		this.articleEntity = articleEntity;
	}

	public ImmutableArticle getImmutableArticle() {
		return immutableArticle;
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
        ClientOrderDetailEntity that = (ClientOrderDetailEntity) object;
        return this.id.equals(that.id) && this.clientOrderHeaderEntity.equals(that.clientOrderHeaderEntity);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.clientOrderHeaderEntity.getId());
    }

	@Override
	public String toString() {
		return "ClientOrderDetailEntity{" +
				"id=" + this.id +
				", clientOrderHeaderEntity=" + (this.clientOrderHeaderEntity != null ? this.clientOrderHeaderEntity.toString() : "null") +
				", articleEntity=" + (this.articleEntity != null ? this.articleEntity.toString() : "null") +
				", immutableArticleEntity=" + (this.immutableArticle != null ? this.immutableArticle.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}