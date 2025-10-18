
package gt.edu.miumg.bienestar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCitas;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio", nullable = false)
    private Servicio servicio;
    private LocalDateTime fechaHora;
    private String notas;
    
    @Enumerated(EnumType.STRING)
    private EstadoCita estado; //PENDIENTE O CONFIRMADA
    
    public enum EstadoCita{
        PENDIENTE,
        CONFIRMADA,
        CANCELADA
    }

    public Cita(Long idCitas, Cliente cliente, Servicio servicio, LocalDateTime fechaHora, String notas, EstadoCita estado) {
        this.idCitas = idCitas;
        this.cliente = cliente;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.notas = notas;
        this.estado = estado;
    }

    public Long getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(Long idCitas) {
        this.idCitas = idCitas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }
    
    
    
}
