package wanderer2357.redwire.model.embeddable;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class CartItemKey implements Serializable {

    private static final long serialVersionUID = 1L;
	private Long clientId;
    private Long articleId;

    public CartItemKey() {
    }

    public CartItemKey(@NotNull Long clientId,
    		@NotNull Long articleId) {
        this.clientId = clientId;
        this.articleId = articleId;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getArticleId() {
        return articleId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CartItemKey that = (CartItemKey) object;
        return this.clientId.equals(that.clientId) && this.articleId.equals(that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, articleId);
    }
}
