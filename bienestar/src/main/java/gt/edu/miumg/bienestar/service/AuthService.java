
package gt.edu.miumg.bienestar.service;

import gt.edu.miumg.bienestar.dto.LoginRequest;
import gt.edu.miumg.bienestar.dto.LoginResponse;
import gt.edu.miumg.bienestar.model.Cliente;
import gt.edu.miumg.bienestar.security.JwtUtil;
import gt.edu.miumg.bienestar.repository.ClienteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final ClienteRepository clienteRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public AuthService(ClienteRepository clienteRepository, JwtUtil jwtUtil){
        this.clienteRepository = clienteRepository;
        this.jwtUtil = jwtUtil;
    } 
    
    public LoginResponse login(LoginRequest request){
        Cliente cliente = clienteRepository.findByCorreo(request.getCorreo())
                .orElseThrow(()-> new RuntimeException("Usuario o contraseña invalidos"));
        
        if(!passwordEncoder.matches(request.getContrasena(), cliente.getContrasena())){
            throw new RuntimeException("Usuario o contraseña invalidos");
        }
        
        String token = jwtUtil.generateToken(cliente.getCorreo(), cliente.getRol(), cliente.getNombre());
        return new LoginResponse(token, cliente.getIdCliente(), cliente.getNombre(), cliente.getCorreo());
    }
}
