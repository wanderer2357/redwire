package wanderer2357.redwire.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wanderer2357.redwire.model.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long>{
	List<ClientEntity> findByEmail(String email);
	List<ClientEntity> findByPhone(String phone);
}
