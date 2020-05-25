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
	
	@Test(expected = RuntimeException.class)
	public void quandoInformarStringComCaracteresEspeciais_DeveLancarExcecao() {
		
		StringUtil.converterCamelCase("nome#");
		StringUtil.converterCamelCase("_nome");
		StringUtil.converterCamelCase("nom@e");

	}		
	
	@Test
	public void quandoInformarStringComNomeComposto_DeveRetornarListaComVariosElementos() {
		
		List<String> palavras = StringUtil.converterCamelCase("nomeComposto");
		assertTrue(palavras!=null && palavras.size()==2 
					&& palavras.get(0).equals("nome")
					&& palavras.get(1).equals("composto")
					);
		
		palavras = StringUtil.converterCamelCase("NomeComposto");
		assertTrue(palavras!=null && palavras.size()==2 
					&& palavras.get(0).equals("nome")
					&& palavras.get(1).equals("composto")
					);	
		
	}		
	
	@Test(expected = RuntimeException.class)
	public void quandoInformarStringComecandoComNumero_DeveLancarExcecao() {
		StringUtil.converterCamelCase("10Primeiros");
	}		
		
	@Test
	public void quandoInformarStringComNumeroIinterno_DeveRetornarListaContendoNumero() {
		
		List<String> palavras = StringUtil.converterCamelCase("recupera10Primeiros");
		assertTrue(palavras!=null && palavras.size()==3 
					&& palavras.get(0).equals("recupera")
					&& palavras.get(1).equals("10")
					&& palavras.get(2).equals("primeiros")
					);
		
		
	}	
	
	@Test
	public void quandoInformarStringComCPFMaiusculaNoMeioDaPalavra_DeveRetornarListaContendoCPFMaisculo() {
		
		List<String> palavras = StringUtil.converterCamelCase("numeroCPFContribuinte");
		assertTrue(palavras!=null && palavras.size()==3 
					&& palavras.get(0).equals("numero")
					&& palavras.get(1).equals("CPF")
					&& palavras.get(2).equals("contribuinte")
					);
		
		 palavras = StringUtil.converterCamelCase("CPFCPFCPFCPF");
			assertTrue(palavras!=null && palavras.size()==4
						&& palavras.get(0).equals("CPF")
						&& palavras.get(1).equals("CPF")
						&& palavras.get(2).equals("CPF")
						&& palavras.get(3).equals("CPF")
						);		
		
	}	
}
