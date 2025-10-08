
package gt.edu.miumg.bienestar.controller;

import gt.edu.miumg.bienestar.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gt.edu.miumg.bienestar.service.ClienteService;


@RestController 
@RequestMapping("/api/clientes")
@CrossOrigin(origins ="*")
public class ClienteController { 
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<Cliente> getAllClientes(){
        return clienteService.obtenerTodos();
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente){
        try{
            Cliente nuevo = clienteService.registrarCliente(cliente);
            return ResponseEntity.ok(nuevo);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al registrar Cliente");
        }
    }
}
