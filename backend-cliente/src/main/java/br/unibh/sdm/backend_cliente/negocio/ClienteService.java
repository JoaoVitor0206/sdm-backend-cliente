package br.unibh.sdm.backend_cliente.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_cliente.entidades.Cliente;
import br.unibh.sdm.backend_cliente.persistencia.ClienteRepository;

@Service
public class ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepo = clienteRepository;
    }

    public List<Cliente> getClientes() {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os objetos");
        }
        Iterable<Cliente> lista = this.clienteRepo.findAll();
        if (lista == null) {
            return new ArrayList<Cliente>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Cliente getClienteById(String userId) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando Cliente com o codigo {}", userId);
        }
        Optional<Cliente> retorno = this.clienteRepo.findById(userId);
        if (!retorno.isPresent()) {
            throw new RuntimeException("Cliente com o id " + userId + " nao encontrada");
        }
        return retorno.get();
    }

    public List<Cliente> getClienteByCodigo(String userId) {
        if (logger.isInfoEnabled()) {
            logger.info("Buscando todos os objetos");
        }
        Iterable<Cliente> lista = this.clienteRepo.findByUserId(userId);
        if (lista == null) {
            return new ArrayList<Cliente>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Cliente saveCliente(Cliente cliente) {
        if (logger.isInfoEnabled()) {
            logger.info("Salvando Cliente com os detalhes {}", cliente.toString());
        }
        return this.clienteRepo.save(cliente);
    }

    public void deleteCliente(String userId) {
        if (logger.isInfoEnabled()) {
            logger.info("Excluindo Cliente com userId {}", userId);
        }
        this.clienteRepo.deleteById(userId);
    }

    public boolean isClienteExists(Cliente cliente) {
        Optional<Cliente> retorno = this.clienteRepo.findById(cliente.getUserId());
        return retorno.isPresent() ? true : false;
    }

}