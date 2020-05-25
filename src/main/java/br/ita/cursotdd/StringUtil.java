package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

	public static List<String> converterCamelCase(String valorParam) {

		if (valorParam == null || valorParam.trim().length() == 0) {
			return new ArrayList<String>();
		}
		String valor = formatarString(valorParam);

		return Stream.of(valor).collect(Collectors.toList());
	}
}
