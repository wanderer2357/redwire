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
@Table(name = "supplier_order_detail", indexes = {
	    @Index(name = "idx_supplier_order", columnList = "supplier_order_id"),
	    @Index(name = "idx_id_supplier_order", columnList = "id, supplier_order_id")
	})
public class SupplierOrderDetailEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "supplier_order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private SupplierOrderHeaderEntity supplierOrderHeaderEntity;
	
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	@OnDelete(action = OnDeleteAction.RESTRICT)
	private ArticleEntity articleEntity;
	
	@Embedded
	private ImmutableArticle immutableArticle;
	
	@Column(nullable = false)
	@Min(value =  1)
	private int quantity;
	
	public SupplierOrderDetailEntity() {
	}
	
	public SupplierOrderDetailEntity(@NotNull SupplierOrderHeaderEntity supplierOrderHeaderEntity,
			@NotNull ArticleEntity articleEntity,
			@NotNull @Min(value =  1) int quantity) {
		this.supplierOrderHeaderEntity = supplierOrderHeaderEntity;
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

	public SupplierOrderHeaderEntity getSupplierOrderHeaderEntity() {
		return supplierOrderHeaderEntity;
	}

	public void setSupplierOrderHeaderEntity(@NotNull SupplierOrderHeaderEntity supplierOrderHeaderEntity) {
		this.supplierOrderHeaderEntity = supplierOrderHeaderEntity;
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
        SupplierOrderDetailEntity that = (SupplierOrderDetailEntity) object;
        return this.id.equals(that.id) && this.supplierOrderHeaderEntity.equals(that.supplierOrderHeaderEntity);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id, this.supplierOrderHeaderEntity.getId());
    }

	@Override
	public String toString() {
		return "SupplierOrderDetailEntity{" +
				"id=" + this.id +
				", supplierOrderHeaderEntity=" + (this.supplierOrderHeaderEntity != null ? this.supplierOrderHeaderEntity.toString() : "null") +
				", articleEntity=" + (this.articleEntity != null ? this.articleEntity.toString() : "null") +
				", immutableArticle=" + (this.immutableArticle != null ? this.immutableArticle.toString() : "null") +
				", quantity=" + this.quantity +
				'}';
	}

}