package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Bebidas;

@Repository
@Transactional
public interface BebidasRepo extends JpaRepository<Bebidas, Long> {

	@Query("select b from Bebidas b where b.pedidos.id = ?1")
	List<Bebidas> findBebidasPerOrder(Long pedidoId);
}
