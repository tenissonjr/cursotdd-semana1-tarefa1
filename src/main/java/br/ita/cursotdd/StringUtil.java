package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.isDigit;

public class StringUtil {

	private static Map<String, String> excecoes = Map.of("CPF", "CPF");
	
	private static boolean isPrimeiraPalavraEhExececao(String valorParam) {
		for (String palavraExcecao : excecoes.values()) {
			if(valorParam.trim().startsWith(palavraExcecao)) {
				return true;
			}
		} 
		return false;
	}

	private static boolean isValorInvalido(String valor) {
		return ! Pattern.matches("[a-zA-Z0-9]+", valor);
	}

	private static boolean isNumeric(String valor) {
		return  Pattern.matches("[0-9]+", valor);
	}	
	
	private static String[] getDivisaoPalavrasExecao(String palavraInicial) {
		for (String palavraExcecao : excecoes.values()) {
			if(palavraInicial.trim().startsWith(palavraExcecao)) {
				String primeiraPalavra  = excecoes.get(palavraExcecao);
				String demaisPalavras  	= palavraInicial.replace(palavraExcecao, "");
				return new String[]{primeiraPalavra, demaisPalavras} ;
			}
		}		
		return new String[]{palavraInicial, ""} ;	
	}
	
	private static String[] getDivisaoPalavrasOrdinarias(String palavraInicial) {
		String primeiraPalavra 	=""; 
		String demaisPalavras 	="";
		boolean caracterAnteriorDigito = isDigit(palavraInicial.charAt(0)) ;
		int posicao=0;
		while (++posicao < palavraInicial.length()) {
			char caracter = palavraInicial.charAt(posicao);
			if ( isUpperCase(caracter)) 						break;
			if ( isDigit(caracter) && !caracterAnteriorDigito) 	break;	
		}	
		if (posicao<palavraInicial.length()) {
			primeiraPalavra =  (palavraInicial.substring(0, posicao)).toLowerCase();
			demaisPalavras  	= palavraInicial.substring(posicao);		
		}else {
			primeiraPalavra = palavraInicial.toLowerCase();
		}
		
		return new String[]{primeiraPalavra, demaisPalavras};
	}	
	
	public static List<String> converterCamelCase(String valorParam) {
		if (valorParam == null || valorParam.trim().length() == 0) {
			return new ArrayList<String>() ;
		}
		if (isValorInvalido(valorParam)) {
			throw new RuntimeException("Caracteres especiais não são permitidos, somente letras e números.");
		}	
		List<String> retorno = separarPalavrasEmLista(valorParam);
		if ( (!retorno.isEmpty()) && (isNumeric(retorno.get(0))) ) {
			throw new RuntimeException("Não deve começar com números.");
		}		
		return retorno ;
	}



	private static List<String> separarPalavrasEmLista(String valorParam) {
		
		List<String> retorno = new ArrayList<String>() ;
		String[] arrDivisaoPalavras = {"",""};
		if (isPrimeiraPalavraEhExececao(valorParam)) {
			arrDivisaoPalavras =getDivisaoPalavrasExecao(valorParam);
		}else {
			arrDivisaoPalavras =getDivisaoPalavrasOrdinarias(valorParam);
		}

		retorno.add(arrDivisaoPalavras[0]);
		if (arrDivisaoPalavras[1]!="") {
			retorno.addAll(separarPalavrasEmLista(arrDivisaoPalavras[1])); 
		}
		return retorno;
	}








}
