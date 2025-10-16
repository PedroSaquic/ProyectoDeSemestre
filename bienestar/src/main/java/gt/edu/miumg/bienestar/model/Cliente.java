package gt.edu.miumg.bienestar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

    @Entity
    @Table(name = "clientes")  
public class Cliente{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long  idCliente;
        @NotBlank(message = "El Nombre es obligatorio")
        private String nombre;
        @Column(unique=true, length=13)
        @NotBlank(message = "DPI invalido")
        private String dpi;
        private LocalDate fechaNaci;
        @Email(message = "intente con otro correo")
        private String correo;
        @Column(length=20)
        private String telefono;
        @Column(nullable = false)
        @JsonIgnore
        private String contrasena;
        @Column(nullable = false)
        private String rol = "CLIENTE";
        
        public Cliente(){}

    public Cliente(Long idCliente, String nombre, String dpi, LocalDate fechaNaci, String correo, String telefono, String contrasena, String rol) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.dpi = dpi;
        this.fechaNaci = fechaNaci;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public LocalDate getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(LocalDate fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }  
    
    public String getRol(){
        return rol;
    }
    
    public void setRol(String rol){
        this.rol = rol;
    }
        
}
