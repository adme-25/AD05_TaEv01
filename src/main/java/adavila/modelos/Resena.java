package adavila.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Resena {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
		private int id;
	
	@Column(name = "autor")
		private String autor;
	
	@Column(name = "valoracion")
	private String valoracion;
	
	@Column(name = "opinion")
	private String opinion;
	
	@ManyToOne
	@JoinColumn (name = "pelicula_id")
	   	private Pelicula pelicula;

	public Resena(String autor, String valoracion, String opinion) {
		super();
		this.autor = autor;
		this.valoracion = valoracion;
		this.opinion = opinion;
	}
	
	public Resena(String autor, String valoracion, String opinion, int pelicula) {
		super();
		this.autor = autor;
		this.valoracion = valoracion;
		this.opinion = opinion;
		this.pelicula.setId(pelicula);
	}

}
