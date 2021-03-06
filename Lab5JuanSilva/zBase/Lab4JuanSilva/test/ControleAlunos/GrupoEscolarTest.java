package ControleAlunos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class GrupoEscolarTest {
	
	private GrupoEscolar grupo1;
	private GrupoEscolar grupo2;
	private GrupoEscolar grupo3;
	private Aluno aluno;
	
	@BeforeEach
	void criaGrupo() {
		this.grupo1 = new GrupoEscolar("Lista");
		this.grupo2 = new GrupoEscolar("Lista");
		this.grupo3 = new GrupoEscolar("Futebol");
		this.aluno = new Aluno("250", "Jax Teller", "CC");
	}

	@Test
	void testToStringGrupo() {
		this.grupo1.alocaAluno(aluno);
		assertEquals("* Aluno: 250 - Jax Teller - CC" + "\n", this.grupo1.toStringGrupo());
	}
	
	@Test
	void testHashCode() {
		assertEquals(this.grupo1.hashCode(), this.grupo2.hashCode());
		assertNotEquals(this.grupo1.hashCode(), this.grupo3.hashCode());
	}
	
	@Test
	void tesEquals() {
		assertTrue(this.grupo1.equals(this.grupo2));
		assertTrue(this.grupo2.equals(this.grupo1));
		assertFalse(this.grupo1.equals(this.grupo3));
	}

}
