package adavila.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import adavila.repositorios.ResenaRepositorio;

@Controller
@RequestMapping ("/resenas")
public class ResenasControlador {
	@Autowired
   	private ResenaRepositorio rr;
	
	@GetMapping ({""})
	public String getPelicula(Model model) {
		model.addAttribute("resenas", rr.findAll());
		return "resenas";
	}
}
