package com.krakdev.clientes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.krakdev.clientes.entidades.Cliente;

class ServicioClienteTest {

	private ServicioCliente servicio;

	@BeforeEach
	void setUp() {
		servicio = new ServicioCliente();
	}

	@Test
	@DisplayName("Crear: guarda el cliente con su email")
	void crearGuardaEmail() {
		Cliente cliente = new Cliente("001", "Ana", "Ruiz", "ana@mail.com");

		Cliente resultado = servicio.crear(cliente);

		assertNotNull(resultado);
		assertEquals("ana@mail.com", resultado.getEmail());
		assertEquals(1, servicio.listar().size());
		assertEquals("ana@mail.com", servicio.listar().get(0).getEmail());
	}

	@Test
	@DisplayName("Crear: cédula duplicada devuelve null")
	void crearDuplicadoDevuelveNull() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));

		Cliente duplicado = servicio.crear(new Cliente("001", "Otro", "Cliente", "otro@mail.com"));

		assertNull(duplicado);
		assertEquals(1, servicio.listar().size());
	}

	@Test
	@DisplayName("Consultar: listar devuelve los clientes con email")
	void listarDevuelveEmail() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));
		servicio.crear(new Cliente("002", "Luis", "Paz", "luis@mail.com"));

		List<Cliente> clientes = servicio.listar();

		assertEquals(2, clientes.size());
		assertEquals("ana@mail.com", clientes.get(0).getEmail());
		assertEquals("luis@mail.com", clientes.get(1).getEmail());
	}

	@Test
	@DisplayName("Consultar: buscarPorCedula encuentra el email")
	void buscarPorCedulaDevuelveEmail() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));

		Cliente encontrado = servicio.buscarPorCedula("001");

		assertNotNull(encontrado);
		assertEquals("ana@mail.com", encontrado.getEmail());
	}

	@Test
	@DisplayName("Consultar: buscarPorCedula inexistente devuelve null")
	void buscarPorCedulaInexistente() {
		assertNull(servicio.buscarPorCedula("999"));
	}

	@Test
	@DisplayName("Actualizar: propaga nombre, apellido y email")
	void actualizarPropagaEmail() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));

		Cliente resultado = servicio.actualizar("001", new Cliente("001", "Ana", "Ruis", "ana.nueva@mail.com"));

		assertNotNull(resultado);
		assertEquals("Ana", resultado.getNombre());
		assertEquals("Ruis", resultado.getApellido());
		assertEquals("ana.nueva@mail.com", resultado.getEmail());
	}

	@Test
	@DisplayName("Actualizar: el cambio de email queda persistido en la lista")
	void actualizarPersisteEnLaLista() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));

		servicio.actualizar("001", new Cliente("001", "Ana", "Ruis", "ana.nueva@mail.com"));

		assertEquals("ana.nueva@mail.com", servicio.buscarPorCedula("001").getEmail());
	}

	@Test
	@DisplayName("Actualizar: cédula inexistente devuelve null")
	void actualizarInexistente() {
		assertNull(servicio.actualizar("999", new Cliente("999", "X", "Y", "x@mail.com")));
	}

	@Test
	@DisplayName("Eliminar: borra el cliente y su email")
	void eliminarBorraCliente() {
		servicio.crear(new Cliente("001", "Ana", "Ruiz", "ana@mail.com"));

		assertTrue(servicio.eliminar("001"));
		assertTrue(servicio.listar().isEmpty());
		assertNull(servicio.buscarPorCedula("001"));
	}

	@Test
	@DisplayName("Eliminar: cédula inexistente devuelve false")
	void eliminarInexistente() {
		assertFalse(servicio.eliminar("999"));
	}
}
