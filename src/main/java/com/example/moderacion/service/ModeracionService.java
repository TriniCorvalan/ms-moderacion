package com.example.moderacion.service;

import java.util.List;

import com.example.moderacion.model.Comentario;
import com.example.moderacion.model.Tema;

public interface ModeracionService {

	List<Tema> listarTemas(String estado);

	Tema banearTema(Long id);

	Tema desbanearTema(Long id);

	List<Comentario> listarComentarios(String estado);

	Comentario banearComentario(Long id);

	Comentario desbanearComentario(Long id);
}
