
package gt.edu.miumg.bienestar.dto;

import java.time.LocalDateTime;

public class CitaRequest {
    private Long clienteId;
    private Long servicioId;
    private LocalDateTime fechaHora;
    private String notas;

    public CitaRequest(Long clienteId, Long servicioId, LocalDateTime fechaHora, String notas) {
        this.clienteId = clienteId;
        this.servicioId = servicioId;
        this.fechaHora = fechaHora;
        this.notas = notas;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
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
    
    
}
