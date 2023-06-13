package br.unibh.sdm.backend_cliente.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_cliente.entidades.Cliente;

@EnableScan()
public interface ClienteRepository extends CrudRepository<Cliente, String> {

  List<Cliente> findByUserId(String userId);

}
