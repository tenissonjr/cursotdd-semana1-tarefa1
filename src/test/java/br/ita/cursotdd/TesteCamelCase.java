package br.ita.cursotdd;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TesteCamelCase {

	@Test
	public void quandoInformarStringVaziaOuNula_DeveRetornarListaVazia() {
		
		List<String> palavras = StringUtil.converterCamelCase(null);
		
		assertTrue(palavras!=null && palavras.isEmpty());
		
		palavras = StringUtil.converterCamelCase("");
		
		assertTrue(palavras!=null && palavras.isEmpty());
		
	}

}
