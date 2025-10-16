
package gt.edu.miumg.bienestar.dto;

public class LoginResponse {
    private String token;
    private Long idCliente;
    private String nombre;
    private String correo;
    
    public LoginResponse(String token, Long idCliente, String nombre, String correo){
        this.token = token;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
