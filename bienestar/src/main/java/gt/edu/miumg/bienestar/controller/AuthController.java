package gt.edu.miumg.bienestar.controller;

import gt.edu.miumg.bienestar.dto.LoginRequest;
import gt.edu.miumg.bienestar.dto.LoginResponse;
import gt.edu.miumg.bienestar.service.AuthService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    
    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        try{
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
