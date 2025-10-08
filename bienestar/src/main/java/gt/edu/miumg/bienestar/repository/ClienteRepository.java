
package gt.edu.miumg.bienestar.repository;

import gt.edu.miumg.bienestar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByCorreo(String correo);
    boolean existsByDpi(String dpi);
}
