package adavila.modelos;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
		private int id;
	@Column(name = "titulo")
		private String titulo;
	@Column(name = "year")
		private int year;
	
	@ManyToOne
	@JoinColumn (name = "director_id")
	   	private Director director;
	
	@OneToMany (mappedBy = "pelicula",cascade = CascadeType.ALL, orphanRemoval = true)
	List <Resena> resenas = new ArrayList<>();

	public Pelicula(String titulo, int year) {
		super();
		this.titulo = titulo;
		this.year = year;
	}
	
	public Pelicula(String titulo, int year, int director_id) {
		super();
		this.titulo = titulo;
		this.year = year;
		this.director.setId(director_id);
	}
}
