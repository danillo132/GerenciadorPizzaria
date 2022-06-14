package br.com.repository;

import org.springframework.transaction.annotation.Transactional;

import br.com.model.Pedidos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PedidosRepo extends JpaRepository<Pedidos, Long> {

	@Query("select p, b, c from Pizza p, Bebidas b, Calzone c where p.pedidos.id = ?1 and b.pedidos.id = ?1 and c.pedidos.id = ?1")
	List<Object[]> listarPedido(Long pedidoId);
}
