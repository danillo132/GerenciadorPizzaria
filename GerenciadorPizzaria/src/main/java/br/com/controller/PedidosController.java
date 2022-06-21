package br.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Bebidas;
import br.com.model.Calzone;
import br.com.model.Cliente;
import br.com.model.Pedidos;
import br.com.model.Pizza;
import br.com.repository.BebidasRepo;
import br.com.repository.CalzoneRepo;
import br.com.repository.ClienteRepo;
import br.com.repository.PedidosRepo;
import br.com.repository.PizzaRepo;

@RequestMapping(value = "/pedido")
@RestController
public class PedidosController {

	Pedidos pedidosAberto = new Pedidos();
	Cliente clienteEncontrado = new Cliente();

	//Injeção de dependências
	@Autowired
	private PedidosRepo pedidosRepository;

	@Autowired
	private PizzaRepo pizzaRepository;

	@Autowired
	private BebidasRepo bebidasRepository;

	@Autowired
	private CalzoneRepo calzoneRepository;

	@Autowired
	private ClienteRepo clienteRepository;

	//Verifica se o cliente está cadastrado através do cpf fornecido como parâmetro
	@GetMapping(value = "/cadastradoCliente/{cpf}", produces = "application/json")
	public ResponseEntity<Cliente> findCliente(@PathVariable("cpf") String cpf) {

		clienteEncontrado = clienteRepository.findByCpf(cpf);//Busca no banco o cliente através do cpf

		return new ResponseEntity<Cliente>(clienteEncontrado, HttpStatus.OK);//retorna um JSON do cliente
		
	}

	//Cria um novo pedido após o usuário ser encontrado
	@PostMapping(value = "/cadastradoCliente/novoPedido", produces = "application/json")
	public ResponseEntity<Pedidos> novoPedido(@RequestBody Pedidos pedidos) {
		pedidos.setCliente(clienteEncontrado);//Relaciona o pedido com o cliente

		pedidosAberto = pedidosRepository.save(pedidos);//Salva o pedido no banco

		return new ResponseEntity<Pedidos>(pedidosAberto, HttpStatus.OK);//Retorna o pedido aberto em JSON
	}

	//Adiciona uma pizza ao pedido
	@PostMapping(value = "/addPizza", produces = "application/json")
	public ResponseEntity<Pizza> addPizza(@RequestBody Pizza pizza) {
		pizza.setPedidos(pedidosAberto);//Relaciona o pedido com a pizza

		Pizza pizzaAdicionada = pizzaRepository.save(pizza);//Salva a pizza no banco

		return new ResponseEntity<Pizza>(pizzaAdicionada, HttpStatus.OK);//Retorna a pizza salva em JSON
	}

	//Adiciona uma bebida ao pedido
	@PostMapping(value = "/addBebidas", produces = "application/json")
	public ResponseEntity<Bebidas> addBebidas(@RequestBody Bebidas bebidas) {
		bebidas.setPedidos(pedidosAberto);//Relaciona o pedido com a bebida

		Bebidas bebidaAdicionada = bebidasRepository.save(bebidas); //Salva  a bebida no banco

		return new ResponseEntity<Bebidas>(bebidaAdicionada, HttpStatus.OK);//Retorna a bebida salva em JSON
	}

	//Adiciona um calzone ao pedido
	@PostMapping(value = "/addCalzone", produces = "application/json")
	public ResponseEntity<Calzone> addCalzone(@RequestBody Calzone calzones) {
		calzones.setPedidos(pedidosAberto);//Relaciona o pedido com o calzone

		Calzone bebidaAdicionada = calzoneRepository.save(calzones); //Salva o calzone no banco

		return new ResponseEntity<Calzone>(bebidaAdicionada, HttpStatus.OK); //Retorna o calzone salvo em JSON
	}

	//Método que busca todas as pizzas do pedido em aberto
	public List<Pizza> listarPizzasPorPedido() {

		//Busca das pizzas no banco através do ID do pedido em aberto
		List<Pizza> pizzas = pizzaRepository.findPizzaPerOrder(pedidosAberto.getId());

		return pizzas; //Retorna uma lista de pizzas
	}

	//Método que busca todas as bebidas do pedido em aberto
	public List<Bebidas> listarBebidasPorPedido() {

		//Busca das bebidas no banco através do ID do pedido em aberto
		List<Bebidas> bebidas = bebidasRepository.findBebidasPerOrder(pedidosAberto.getId());

		return bebidas; //Retorna uma lista de bebidas
	}

	//Método que busca todas os calzones do pedido em aberto
	public List<Calzone> listarCalzonesPorPedido() {
		//Busca dos calzones no banco através do ID do pedido em aberto
		List<Calzone> calzones = calzoneRepository.findCalzonePerOrder(pedidosAberto.getId());

		return calzones;//Retorna uma lista de calzones
	}
	
	//Retorna o pedido finalizado com todos produtos 
	@GetMapping(value = "/finalizarPedido", produces = "application/json")
	public ResponseEntity<?> finalizarPedido(){
	
		
		List<Pizza> pizzas =  listarPizzasPorPedido();
		List<Bebidas> bebidas = listarBebidasPorPedido();
		List<Calzone> calzones = listarCalzonesPorPedido();
		
		
		if(pizzas.size() > 0) {//Setando a lista de pizzas do pedido se existir alguma pizza
			pedidosAberto.setPizzas(pizzas);
		}
		
		if(bebidas.size() > 0) {//Setando a lista de bebidas do pedido se existir alguma bebida
		 pedidosAberto.setBebidas(bebidas);
		}
		
		if(calzones.size() > 0) {//Setando a lista de calzones do pedido se existir algum calzone
			pedidosAberto.setCalzones(calzones);
		}
		
		
		
		return new ResponseEntity<Pedidos>(pedidosAberto, HttpStatus.OK); //Retornando o pedido finalizado em formato JSON
	}
	
	//Remove uma pizza do pedido atual
	@DeleteMapping(value = "/removerPizza/{id}", produces = "application/text")
	public String removerPizzadoPedido(@PathVariable("id") Long id) {//Recebe o id da pizza como parâmetro
		if(id != null) {//Verifica se o id veio nulo ou não
			pizzaRepository.deleteById(id); //Remove do banco
		}else {
			return "ID vazio ou inexistente";//Retorna uma mensagem caso o id seja inexistente
		}
		
		
		return "Pizza removida!";//Retorna uma mensagem caso a pizza seja removida com sucesso
	}
	
	//Remove uma bebida do pedido atual
	@DeleteMapping(value = "/removerBebidas/{id}", produces = "application/text")
	public String removerBebidasdoPedido(@PathVariable("id") Long id) {
		if(id != null) {
			bebidasRepository.deleteById(id);
		}else {
			return "ID vazio ou inexistente";
		}
		
		
		return "Bebida removida!";
	}
	
	//Remove um calzone do pedido atual
	@DeleteMapping(value = "/removerCalzone/{id}", produces = "application/text")
	public String removerCalzonedoPedido(@PathVariable("id") Long id) {
		if(id != null) {
			calzoneRepository.deleteById(id);
		}else {
			return "ID vazio ou inexistente";
		}
		
		
		return "Calzone removido!";
	}
	
	//Cancela o pedido atual
	@DeleteMapping(value = "/cancelarPedido/{id}", produces = "application/text")
	public String cancelarPedido(@PathVariable("id") Long id) {
		if(id != null) {
			pedidosRepository.deleteById(id);
		}else {
			return "ID vazio ou inexistente";
		}
		
		
		return "Pedido cancelado!";
	}
	
	

}
