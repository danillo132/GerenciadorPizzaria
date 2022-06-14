package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Cliente;

@Repository
@Transactional
public interface ClienteRepo extends JpaRepository<Cliente, Long> {

	
	@Query("select c from Cliente c where c.cpf = ?1")
	Cliente findByCpf(String cpf);
}
