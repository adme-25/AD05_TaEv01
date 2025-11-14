package adavila.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import adavila.repositorios.DirectorRepositorio;

@Controller
@RequestMapping ("/directores")
public class DirectoresControlador {
	@Autowired
   	private DirectorRepositorio dr;
	
	@GetMapping ({""})
	public String getPelicula(Model model) {
		model.addAttribute("directores", dr.findAll());
		return "directores";
	}
}
