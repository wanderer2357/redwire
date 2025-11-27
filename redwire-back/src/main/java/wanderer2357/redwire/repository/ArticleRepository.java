package wanderer2357.redwire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanderer2357.redwire.model.ArticleEntity;
import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long>{
	List<ArticleEntity> findByCode(String code);
	boolean existsByCode(String code);
}
