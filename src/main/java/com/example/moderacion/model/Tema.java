package com.example.moderacion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "TEMA")
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TITULO", nullable = false, length = 200)
	private String titulo;

	@Lob
	@Column(name = "CONTENIDO", nullable = false)
	private String contenido;

	@Column(name = "USUARIO_ID", nullable = false)
	private Long usuarioId;

	@Column(name = "CATEGORIA_ID", nullable = false)
	private Long categoriaId;

	@Column(name = "ESTADO", nullable = false, length = 20)
	private String estado = EstadoContenido.ACTIVO;

	@Column(name = "FECHA_CREACION", nullable = false)
	private LocalDateTime fechaCreacion = LocalDateTime.now();
}
