package com.example.moderacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moderacion.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

	List<Comentario> findByEstadoOrderByFechaCreacionDesc(String estado);

	List<Comentario> findAllByOrderByFechaCreacionDesc();
}
