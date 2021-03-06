package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Funcionario;

@Repository
public interface FuncionarioRepo extends JpaRepository<Funcionario, Long> {

	

	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "update Funcionario set token = ?1 where login = ?2")
	void atualizaTokenUser(String token,String login);
	
	
	@Query("Select f from Funcionario f where f.login = ?1")
	Funcionario findUserByLogin(String login);
	
	
	
}
