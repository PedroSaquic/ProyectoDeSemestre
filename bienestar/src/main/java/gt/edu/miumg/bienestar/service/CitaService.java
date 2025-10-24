
package gt.edu.miumg.bienestar.service;

import gt.edu.miumg.bienestar.dto.CitaRequest;
import gt.edu.miumg.bienestar.dto.CitaResponse;
import gt.edu.miumg.bienestar.model.Cita;
import gt.edu.miumg.bienestar.model.Cliente;
import gt.edu.miumg.bienestar.model.Servicio;
import gt.edu.miumg.bienestar.repository.CitaRepository;
import gt.edu.miumg.bienestar.repository.ClienteRepository;
import gt.edu.miumg.bienestar.repository.ServicioRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class CitaService {
   private final CitaRepository citaRepository; 
   private final ClienteRepository clienteRepository;
   private final ServicioRepository servicioRepository;
   
   public CitaService(CitaRepository citaRepository, ClienteRepository clienteRepository, ServicioRepository servicioRepository){
       this.citaRepository = citaRepository;
       this.clienteRepository = clienteRepository;
       this.servicioRepository = servicioRepository;
   }
   
   public CitaResponse agendarCita(CitaRequest request, boolean esRecepcionista, String correoUsuarioActual){
       
       Cliente cliente = clienteRepository.findById(request.getClienteId())
               .orElseThrow(() -> new RuntimeException("Cliente invalido"));
       
       Servicio servicio = servicioRepository.findById(request.getServicioId())
               .orElseThrow(() -> new RuntimeException("Servicio invalido"));
       
       if(request.getFechaHora().isBefore(LocalDateTime.now().plusHours(2))){
           throw new RuntimeException("la cita debe agendarse con 2 horas de anticipacion");    
       }
       
       long pendientes = citaRepository.countPendienteByCliente(cliente);
       if(!esRecepcionista && pendientes >= 3){
           throw new RuntimeException("Ya tienes 3 citas pendientes");
       }
       if(!esRecepcionista && correoUsuarioActual != null && !cliente.getCorreo().equals(correoUsuarioActual)){
           throw new RuntimeException("No puedes crear citas para otros usuarios");
       }
       
       long ocupadas = citaRepository.countByServicioAndFechaHora(servicio, request.getFechaHora());
       if(ocupadas >= servicio.getMaxConcurrentes()){
           throw new RuntimeException("Cupo completo del servicio en este horario");
       }
       
       //para crear cita
       Cita cita = new Cita();
       cita.setCliente(cliente);
       cita.setServicio(servicio);
       cita.setFechaHora(request.getFechaHora());
       cita.setNotas(request.getNotas());
       cita.setEstado(esRecepcionista ? Cita.EstadoCita.CONFIRMADA : Cita.EstadoCita.PENDIENTE);
       citaRepository.save(cita);
       
       return new CitaResponse(
               cita.getIdCitas(),
               cliente.getNombre(),
               servicio.getNombre(),
               cita.getFechaHora(),
               cita.getEstado().toString(),
               esRecepcionista ?
                 "Cita programada con exito para  " + cita.getFechaHora() :
                 "Tu solicitud de cita ha sido recibida."
       );
   }
}
