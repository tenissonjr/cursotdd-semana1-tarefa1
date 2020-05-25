package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

	private static boolean isValorInvalido(String valorParam) {
		return ! Pattern.matches("[a-zA-Z]+", valorParam);
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
		for (int posicao = 0; posicao < palavraInicial.length(); posicao++) {
			if(Character.isUpperCase(palavraInicial.charAt(posicao)) && posicao>0) {
				String primeiraPalavra =  (palavraInicial.substring(0, posicao)).toLowerCase();
				String demaisPalavras  	= palavraInicial.substring(posicao);
				return new String[]{primeiraPalavra, demaisPalavras} ;		
			}
		}	
		return new String[]{palavraInicial.toLowerCase(), ""} ;
	}	
	
	public static List<String> converterCamelCase(String valorParam) {

		List<String> retorno = new ArrayList<String>() ;
		
		if (valorParam == null || valorParam.trim().length() == 0) {
			return retorno;
		}
		if (isValorInvalido(valorParam)) {
			throw new RuntimeException("Inválido-caracteres especiais não são permitidos, somente letras e números.");
		}	
		
		String[] arrDivisaoPalavras = {"",""};
		if (isPrimeiraPalavraEhExececao(valorParam)) {
			arrDivisaoPalavras =getDivisaoPalavrasExecao(valorParam);
		}else {
			arrDivisaoPalavras =getDivisaoPalavrasOrdinarias(valorParam);
		}

		retorno.add(arrDivisaoPalavras[0]);
		if (arrDivisaoPalavras[1]!="") {
			retorno.addAll(converterCamelCase(arrDivisaoPalavras[1])); 
		}
		return retorno ;
	}








}
