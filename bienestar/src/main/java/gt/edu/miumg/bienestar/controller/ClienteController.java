
package gt.edu.miumg.bienestar.controller;

import gt.edu.miumg.bienestar.model.Cliente;
import gt.edu.miumg.bienestar.repository.ClienteRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final ClienteRepository clienteRepository;
    
    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }
    
    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }
    
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
}
