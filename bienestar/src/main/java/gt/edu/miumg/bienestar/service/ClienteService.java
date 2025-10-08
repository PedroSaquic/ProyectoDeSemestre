package gt.edu.miumg.bienestar.service;

import gt.edu.miumg.bienestar.model.Cliente;
import gt.edu.miumg.bienestar.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registrarCliente(Cliente cliente) {
        //correo repetido
        if (clienteRepository.existsByCorreo(cliente.getCorreo())) {
            throw new IllegalArgumentException("El correo ya existe");
        }
        //validar DPI
        if (cliente.getDpi() == null || cliente.getDpi().length() < 8) {
            throw new IllegalArgumentException("El DPI tien almenos 8 digitos");
        }
        //validar contraseña
        if (!cliente.getContrasena().matches("^(?=.*[A-Za-z])(?=.*\\d).{8,}$")) {
            throw new IllegalArgumentException("Contraseña invalida, 8 caracteres, letra, numero");
        }
        //validar formato fecha
        if (cliente.getFechaNaci() == null) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida");
        }
        //validar DPI
        if (clienteRepository.existsByDpi(cliente.getDpi())) {
            throw new IllegalArgumentException("El DPI ya está registrado");
        }

        //guardar en base de datos
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

}
