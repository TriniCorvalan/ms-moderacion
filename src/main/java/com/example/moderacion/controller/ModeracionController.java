package com.example.moderacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.moderacion.model.Comentario;
import com.example.moderacion.model.Tema;
import com.example.moderacion.service.ModeracionService;

@RestController
@RequestMapping("/moderacion")
@CrossOrigin(origins = "*")
public class ModeracionController {

	@Autowired
	private ModeracionService moderacionService;

	@GetMapping("/temas")
	public List<Tema> listarTemas(@RequestParam(required = false) String estado) {
		return moderacionService.listarTemas(estado);
	}

	@PutMapping("/temas/{id}/banear")
	public ResponseEntity<?> banearTema(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(moderacionService.banearTema(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/temas/{id}/desbanear")
	public ResponseEntity<?> desbanearTema(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(moderacionService.desbanearTema(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/comentarios")
	public List<Comentario> listarComentarios(@RequestParam(required = false) String estado) {
		return moderacionService.listarComentarios(estado);
	}

	@PutMapping("/comentarios/{id}/banear")
	public ResponseEntity<?> banearComentario(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(moderacionService.banearComentario(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/comentarios/{id}/desbanear")
	public ResponseEntity<?> desbanearComentario(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(moderacionService.desbanearComentario(id));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
