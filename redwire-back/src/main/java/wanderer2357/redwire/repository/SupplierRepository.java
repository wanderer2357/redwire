package wanderer2357.redwire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanderer2357.redwire.model.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>{

	boolean existsById(Long id);
	List<SupplierEntity> findByEmail(String email);
	List<SupplierEntity> findByPhone(String phone);
	boolean existsByName(String name);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
	boolean existsByEmailAndIdNot(String email, Long Id);
	boolean existsByPhoneAndIdNot(String phone, Long Id);
}
