package com.biblioteca.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.biblioteca.model.Autor;
import com.biblioteca.model.Categoria;

@SpringBootTest
public class MetadadosRepositoryTest {
	
	@Autowired
	private AutorRepository autorRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Test
	public void testSalvarAutor() {
		
		Autor autor = new Autor();
		autor.setNome("Gersonilo");
		
		Autor autorSalvo = autorRepo.save(autor);
		
		assertNotNull(autorSalvo.getId());
		
	}
	
	@Test
	public void testSalvarCategoria() {
		
		Categoria categoria = new Categoria();
		categoria.setNome("Didatico");
		categoria.setDescricao("Livros Tecnicos");
		
		Categoria categoriaSalva = categoriaRepo.save(categoria);
		
		assertNotNull(categoriaSalva.getId());
	}

}
