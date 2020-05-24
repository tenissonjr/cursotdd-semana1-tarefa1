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

	@Test
	public void quandoInformarStringUnica_DeveRetornarListaComUmElemento() {
		
		List<String> palavras = StringUtil.converterCamelCase("nome");
		assertTrue(palavras!=null && palavras.size()==1 && palavras.get(0).equals("nome"));
		
	}	
	
	@Test
	public void quandoInformarStringUnicaInciadaPorMaiuscula_DeveRetornarListaComUmElementoEmMinuscula() {
		
		List<String> palavras = StringUtil.converterCamelCase("Nome");
		assertTrue(palavras!=null && palavras.size()==1 && palavras.get(0).equals("nome"));
		
	}	
	
	@Test
	public void quandoInformarStringComCPFMaiuscula_DeveRetornarListaComUmElementoEmMaiuscula() {
		
		List<String> palavras = StringUtil.converterCamelCase("CPF");
		assertTrue(palavras!=null && palavras.size()==1 && palavras.get(0).equals("CPF"));
		
	}		
}
