package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Pizza;

@Repository
@Transactional
public interface PizzaRepo extends JpaRepository<Pizza, Long> {

	@Query("select p from Pizza p where p.pedidos.id = ?1")
	List<Pizza> findPizzaPerOrder(Long pedidoId);
}
