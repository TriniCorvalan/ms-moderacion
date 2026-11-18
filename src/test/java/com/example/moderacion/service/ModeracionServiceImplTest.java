package com.example.moderacion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.moderacion.model.EstadoContenido;
import com.example.moderacion.model.Tema;
import com.example.moderacion.repository.ComentarioRepository;
import com.example.moderacion.repository.TemaRepository;

@ExtendWith(MockitoExtension.class)
class ModeracionServiceImplTest {

	@Mock
	private TemaRepository temaRepository;

	@Mock
	private ComentarioRepository comentarioRepository;

	@InjectMocks
	private ModeracionServiceImpl service;

	@Test
	void banearTema_cambiaEstadoABaneado() {
		Tema tema = new Tema();
		tema.setId(1L);
		tema.setEstado(EstadoContenido.ACTIVO);
		when(temaRepository.findById(1L)).thenReturn(Optional.of(tema));
		when(temaRepository.save(any(Tema.class))).thenAnswer(inv -> inv.getArgument(0));

		Tema result = service.banearTema(1L);

		assertEquals(EstadoContenido.BANEADO, result.getEstado());
		verify(temaRepository).save(tema);
	}

	@Test
	void banearTema_inexistente_lanzaError() {
		when(temaRepository.findById(99L)).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> service.banearTema(99L));
	}

	@Test
	void desbanearTema_cambiaEstadoAActivo() {
		Tema tema = new Tema();
		tema.setId(1L);
		tema.setEstado(EstadoContenido.BANEADO);
		when(temaRepository.findById(1L)).thenReturn(Optional.of(tema));
		when(temaRepository.save(any(Tema.class))).thenAnswer(inv -> inv.getArgument(0));

		Tema result = service.desbanearTema(1L);

		assertEquals(EstadoContenido.ACTIVO, result.getEstado());
		verify(temaRepository).save(tema);
	}
}
