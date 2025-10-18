
package gt.edu.miumg.bienestar.repository;

import gt.edu.miumg.bienestar.model.Cita;
import gt.edu.miumg.bienestar.model.Cliente;
import gt.edu.miumg.bienestar.model.Servicio;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CitaRepository extends JpaRepository<Cita, Long>{
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.servicio = :servicio AND c.fechaHora = :fechaHora AND c.estado <> 'CANCELADA'")
    long countByServicioAndFechaHora(Servicio servicio, LocalDateTime fechaHora);
    
    @Query("SELECT COUNT(c) FROM Cita c WHERE c.cliente = :cliente AND c.estado = 'PENDIENTE'")
    long countPendienteByCliente(Cliente cliente);
    
    List<Cita> findByCliente(Cliente cliente);
}
