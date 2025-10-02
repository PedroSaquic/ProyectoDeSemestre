
package gt.edu.miumg.bienestar.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author pedro
 */
    @RestController
    @RequestMapping("/api/hello")
public class HelloController {
     @GetMapping
    public String hello() {
        return "¡Bienvenido al sistema de bienestar!🐒🐒🐒🐒🐒";
    }
}
 