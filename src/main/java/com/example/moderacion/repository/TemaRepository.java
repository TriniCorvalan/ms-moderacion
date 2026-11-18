package com.example.moderacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.moderacion.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	List<Tema> findByEstadoOrderByFechaCreacionDesc(String estado);

	List<Tema> findAllByOrderByFechaCreacionDesc();
}
