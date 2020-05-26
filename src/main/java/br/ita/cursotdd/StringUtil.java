package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isDigit;

public class StringUtil {

	private static Map<String, String> excecoes = Map.of("CPF", "CPF");
	
	private static boolean isPrimeiraPalavraConstituiExececao(String texto) {
		for (String palavraExcecao : excecoes.values()) {
			if(texto.trim().startsWith(palavraExcecao)) 
					return true;
		}
		return false;
	}

	private static boolean isTextoPossuiCaracteresInvalidos(String texto) {
		return ! Pattern.matches("[a-zA-Z0-9]+", texto);
	}

	private static boolean isTextoNumerico(String texto) {
		return  Pattern.matches("[0-9]+", texto);
	}	
	
	class SeparacaoPalavras{
		String primeiraPalavra ="";
		String demaisPalavras  ="";
		public SeparacaoPalavras(String primeiraPalavra, String demaisPalavras) {
			super();
			this.primeiraPalavra = primeiraPalavra;
			this.demaisPalavras = demaisPalavras;
		}
	}
	private static SeparacaoPalavras criarSeparacaoPalavras(String primeiraPalavra, String demaisPalavras) {
		return  (new StringUtil()).new SeparacaoPalavras(primeiraPalavra,demaisPalavras); 
	}	
	 	
	
	private static int obterIndiceSeparadorPalavras(String palavraInicial) {
		int indiceSeparadorDePalavras=0;
		boolean caracterAnteriorDigito = isDigit(palavraInicial.charAt(0)) ;
		while (++indiceSeparadorDePalavras < palavraInicial.length()) {
			char caracter = palavraInicial.charAt(indiceSeparadorDePalavras);
			if ( isUpperCase(caracter)) 						break;
			if ( isDigit(caracter) && !caracterAnteriorDigito) 	break;	
		}
		return indiceSeparadorDePalavras;
	}	
	
	private static SeparacaoPalavras separarPalavraQueNaoPossuiExcecaoNoInicio(String texto) {
		int indiceSeparadorDePalavras = obterIndiceSeparadorPalavras(texto);	
		if (indiceSeparadorDePalavras<texto.length()) {
			return criarSeparacaoPalavras((texto.substring(0, indiceSeparadorDePalavras)).toLowerCase()
												, texto.substring(indiceSeparadorDePalavras));
		}else {
			return criarSeparacaoPalavras(texto.toLowerCase(), "");
		}
	}

	private static SeparacaoPalavras separarPalavraQuePossuiExcecaoNoInicio(String texto) {
		for (String palavraExcecao : excecoes.values()) {
			if(texto.trim().startsWith(palavraExcecao)) {
				return criarSeparacaoPalavras( excecoes.get(palavraExcecao), texto.replaceFirst(palavraExcecao, ""));
			}
		}		
		return criarSeparacaoPalavras(texto, "") ;		
	}
	
	private static SeparacaoPalavras separarPalavras(String texto) {
		if (isPrimeiraPalavraConstituiExececao(texto)) {
			 return separarPalavraQuePossuiExcecaoNoInicio(texto);
		}else {
			return separarPalavraQueNaoPossuiExcecaoNoInicio(texto);
		}
		
	}
	
	private static List<String> separarPalavrasEmLista(String texto) {
		List<String> retorno = new ArrayList<String>() ;
		SeparacaoPalavras separacaoPalavras = separarPalavras(texto);
		retorno.add(separacaoPalavras.primeiraPalavra);
		if (separacaoPalavras.demaisPalavras.length()>0) {
			retorno.addAll(separarPalavrasEmLista(separacaoPalavras.demaisPalavras)); 
		}
		return retorno;
	}


	public static List<String> converterCamelCase(String original) {
		
		if (original == null || original.trim().length() == 0) 
				return new ArrayList<String>() ;
		if (isTextoPossuiCaracteresInvalidos(original)) 
				throw new RuntimeException("Caracteres especiais não são permitidos, somente letras e números.");
		List<String> palavras = separarPalavrasEmLista(original);
		if ( (!palavras.isEmpty()) && (isTextoNumerico(palavras.get(0))) ) 
				throw new RuntimeException("Não deve começar com números.");	
		return palavras ;
		
	}


}
