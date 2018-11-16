package com.oup.integration.sps.acad.startitle.dozar.converters;

import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.ibm.icu.text.RuleBasedNumberFormat;

public class NumberFormatCustomConverter {

	static Pattern bound = Pattern.compile("\\b(?=\\w)");

	public Double stringToDouble(String input) {
		if (StringUtils.isBlank(input)) {
			return 0D;
		} else if (StringUtils.endsWith(input, "-")) {
			return Double.parseDouble("-" + input.substring(0, input.length() - 1));
		} else {
			return Double.parseDouble(input);
		}

	}

	public String wordOrdinal(Integer input) {

		if (input.intValue() < 0) {
			return "a";
		} else {

			RuleBasedNumberFormat enFormatter = new RuleBasedNumberFormat(Locale.ENGLISH,
					RuleBasedNumberFormat.SPELLOUT);

			//System.out.println("The Input Number is  :::  " + Integer.parseInt(input));

			String word = enFormatter.format(input, "%spellout-ordinal");

			word = toUppercase(word);
			if ((word.contains("-") || word.contains(" "))) {
				word = toUppercase(word);

			}

			return word + " Edition";

		}
	}

	public String digitOrdinal(Integer input) {

		if (input.intValue()<0) {
			return "a";
		} else {

			RuleBasedNumberFormat enFormatter2 = new RuleBasedNumberFormat(Locale.ENGLISH,
					RuleBasedNumberFormat.ORDINAL);

			return enFormatter2.format(input, "%digits-ordinal") + " Ed";
		}
	}

	public String appendPrefix(Integer input) {

		String input2=input+"";
		
		if (StringUtils.isBlank(input2.trim())) {
			return "n";
		} else {

			return "n"+input;
		}
	}
	
	public String appendPrefixPsychology(String input) {

		if (StringUtils.isBlank(input)) {
			return "t";
		} else {

			return "t"+input;
		}
	}
	
	
	
	private static String firstLetterUpper(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	};

	public static String toUppercase(String input) {
		return bound.splitAsStream(input).map(NumberFormatCustomConverter::firstLetterUpper)
				.collect(Collectors.joining());
	}

}
