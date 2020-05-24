package br.ita.cursotdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {
	public static List<String> converterCamelCase(String valor) {
		
		if (valor==null || valor.trim().length()==0) {
			return new ArrayList<String>(); 
		}
		
		return Stream.of(valor.toLowerCase()).collect(Collectors.toList());
	}
}
