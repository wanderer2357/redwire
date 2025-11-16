package wanderer2357.redwire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanderer2357.redwire.model.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{

	boolean existsById(Long id);
	boolean existsByName(String name);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
}
