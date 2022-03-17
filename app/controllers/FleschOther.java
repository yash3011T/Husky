package controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FleschOther {
	
	public static double[]  calculateScore(String textToScore) {
    double nsentences = 0.0;
    double nwords = 0.0;
    double nsyllables = 0.0;
    double double_array[] = new double[100];

    if (textToScore != null) {
        String[] sentences = textToScore.split("\\.");
        String[] words;

        for (int i = 0; i < sentences.length; i++) {
            words = sentences[i].split("\\s+");
            nsentences++;
            for (int j = 0; j < words.length; j++) {
            	nwords++;
            	nsyllables += countSyllables(words[j]);
            }
        }

        if (nsentences > 0 && nwords > 0 && nsyllables > 0) {
        	int c=0;

    		double_array[c+1]=(0.39 * (nwords / nsentences)) + (11.8 * (nsyllables / nwords)) - 15.59;
    		double_array[c]=206.835 - (84.6*(nsyllables / nwords)) - (1.015*nwords / nsentences);
         
        }
    }

    return double_array;
}

/**
 * Method counting the number of syllables
 * 
 * @param word The word tested for its syllables
 * @return the number of syllables in the word
 */
private static int countSyllables(String words) {
    int count = 0;
    words = words.toLowerCase();

    if (words.length() > 0 && words.charAt(words.length() - 1) == 'e') {
        if (silente(words)) {
            String newword = words.substring(0, words.length() - 1);
            count = count + countit(newword);
        } else {
            count++;
        }
    } else {
        count = count + countit(words);
    }
    return count;
}

private static boolean silente(String word) {
    word = word.substring(0, word.length() - 1);

    Pattern yup = Pattern.compile("[aeiouy]");
    Matcher m = yup.matcher(word);

    if (m.find()) {
        return true;
    } else
        return false;
}

private static int countit(String word) {
    int count = 0;
    Pattern splitter = Pattern.compile("[^aeiouy]*[aeiouy]+");
    Matcher m = splitter.matcher(word);

    while (m.find()) {
        count++;
    }
    return count;
}

}

