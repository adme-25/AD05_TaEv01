package adavila.modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Director {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
		private int id;
	
	@Column(name = "nombre")
		private String nombre;
	
	@OneToMany (mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true)
	List <Pelicula> peliculas = new ArrayList<>();

	public Director(String nombre) {
		super();
		this.nombre = nombre;
	}
	
}
