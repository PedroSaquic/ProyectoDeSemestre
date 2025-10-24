
package gt.edu.miumg.bienestar.controller;

import gt.edu.miumg.bienestar.dto.CitaRequest;
import gt.edu.miumg.bienestar.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {
    
    private final CitaService citaService;
    
    public CitaController(CitaService citaService){
        this.citaService = citaService;
    }
    
    //Programar como ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/programar")
    public ResponseEntity<?> programar(@RequestBody CitaRequest request){
        var response = citaService.agendarCita(request, true, null);
        return ResponseEntity.ok(response);
    }
    
    //Agendar como Cliente(App)
    @PostMapping("/solicitar")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<?> solicitar(@RequestBody CitaRequest request,
                                        Authentication authentication){
        
        String correoUsuarioActual = authentication.getName();
        var response = citaService.agendarCita(request, false, correoUsuarioActual);
        return ResponseEntity.ok(response);
    }
}
