package adavila.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {
	
	@GetMapping ({"/","/welcome"})
	public String welcome() {
		return "index";
	}
}
