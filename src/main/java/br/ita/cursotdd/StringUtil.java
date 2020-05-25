package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {

	private static Map<String, String> excecoes = Map.of("CPF", "CPF");

	private static String formatarString(String valor) {

		if (excecoes.containsKey(valor)) {
			valor = excecoes.get(valor);
		} else {
			valor = valor.toLowerCase();
		}
		return valor;
	}

	private static String obterPrimeiraPalavraExcecao(String valorParam) {
		for (String palavraExcecao : excecoes.values()) {
			if(valorParam.trim().startsWith(palavraExcecao)) {
				return palavraExcecao;
			}
		} 
		return "";
	}
	
	
	private static boolean isValorInvalido(String valorParam) {
		return ! Pattern.matches("[a-zA-Z]+", valorParam);
	}
	
	
	public static List<String> converterCamelCase(String valorParam) {

		if (valorParam == null || valorParam.trim().length() == 0) {
			return new ArrayList<String>();
		}
		if (isValorInvalido(valorParam)) {
			throw new RuntimeException("Inválido-caracteres especiais não são permitidos, somente letras e números.");
		}
		
		String primeiraPalavraExececao = obterPrimeiraPalavraExcecao(valorParam);
		if (primeiraPalavraExececao!="") {
			String primeiraPalavra = excecoes.get(primeiraPalavraExececao);
			List<String> palavras = Stream.of(primeiraPalavra).collect(Collectors.toList());
			
			String palavrasRestantes = valorParam.replace(primeiraPalavraExececao, "");
			palavras.addAll(converterCamelCase(palavrasRestantes))	;
			
			return palavras;
		} else {
			int posicao = 0 ;
			for (Character ch : valorParam.toCharArray()) {
				if(Character.isUpperCase(ch) && posicao>0) {
						String primeiraPalavra = valorParam.substring(0, posicao);
						List<String> palavras = converterCamelCase(primeiraPalavra);
						
						String palavrasRestantes = valorParam.substring(posicao);
						palavras.addAll(converterCamelCase(palavrasRestantes))	;
							
					return palavras ;
				}
				posicao++;
			}
		}
		String valor = formatarString(valorParam);

		return Stream.of(valor).collect(Collectors.toList());
	}




}
