package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Calzone;

@Repository
@Transactional
public interface CalzoneRepo extends JpaRepository<Calzone, Long> {

	
	
	@Query("select c from Calzone c where c.pedidos.id = ?1")
	List<Calzone> findCalzonePerOrder(Long pedidoId);
}
