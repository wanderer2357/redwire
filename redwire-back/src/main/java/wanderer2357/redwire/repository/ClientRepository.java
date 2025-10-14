package wanderer2357.redwire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanderer2357.redwire.model.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
	boolean existsById(Long id);
	List<ClientEntity> findByEmail(String email);
	List<ClientEntity> findByPhone(String phone);
	boolean existsByEmail(String email);
	boolean existsByPhone(String phone);
	boolean existsByEmailAndIdNot(String email, Long id);
	boolean existsByPhoneAndIdNot(String phone, Long id);
}
