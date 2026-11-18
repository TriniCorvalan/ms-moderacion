package com.example.moderacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moderacion.model.Comentario;
import com.example.moderacion.model.EstadoContenido;
import com.example.moderacion.model.Tema;
import com.example.moderacion.repository.ComentarioRepository;
import com.example.moderacion.repository.TemaRepository;

@Service
public class ModeracionServiceImpl implements ModeracionService {

	@Autowired
	private TemaRepository temaRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public List<Tema> listarTemas(String estado) {
		if (estado == null || estado.isBlank()) {
			return temaRepository.findAllByOrderByFechaCreacionDesc();
		}
		return temaRepository.findByEstadoOrderByFechaCreacionDesc(estado.trim().toUpperCase());
	}

	@Override
	public Tema banearTema(Long id) {
		Tema tema = temaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tema no encontrado"));
		tema.setEstado(EstadoContenido.BANEADO);
		return temaRepository.save(tema);
	}

	@Override
	public Tema desbanearTema(Long id) {
		Tema tema = temaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Tema no encontrado"));
		tema.setEstado(EstadoContenido.ACTIVO);
		return temaRepository.save(tema);
	}

	@Override
	public List<Comentario> listarComentarios(String estado) {
		if (estado == null || estado.isBlank()) {
			return comentarioRepository.findAllByOrderByFechaCreacionDesc();
		}
		return comentarioRepository.findByEstadoOrderByFechaCreacionDesc(estado.trim().toUpperCase());
	}

	@Override
	public Comentario banearComentario(Long id) {
		Comentario comentario = comentarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
		comentario.setEstado(EstadoContenido.BANEADO);
		return comentarioRepository.save(comentario);
	}

	@Override
	public Comentario desbanearComentario(Long id) {
		Comentario comentario = comentarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Comentario no encontrado"));
		comentario.setEstado(EstadoContenido.ACTIVO);
		return comentarioRepository.save(comentario);
	}
}
