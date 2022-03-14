package model;

import java.io.IOException;
import java.lang.*;
import java.util.Scanner;

public class Flesch
{ 
	public int Index(String url) throws IOException {  
	  int num_Syllables = 0;
		int num_Words = 0;
		int num_Sentences = 0;
		int index = 0;
	  int ch;
	char prev = '~';
	boolean syllFound = false;
	for (int i=0;i<url.length();i++){
		char curr =  url.charAt(i);
		if(!isDigit(curr)){
			if(!isVowel(prev) && isVowel(curr)){
				num_Syllables++;
				syllFound = true;
			}
			else if(isPunct(curr)){
				num_Sentences++;
				num_Words++;
				if(!syllFound){
					num_Syllables++;
				}
			}
			else if(prev == 'e'){
				if(isPunct(curr) || isDelim(curr)){
					num_Syllables--;
				}
			}
			else if(isAlpha(prev) && isDelim(curr)){
				num_Words++;	
				if(!syllFound){
					num_Syllables++;
				}
			}	
			prev = curr;
		}
	}

		/* System.out.println("Words: " + num_Words);
		System.out.println("Syllables: " + num_Syllables);
		System.out.println("Sentences: " + num_Sentences);*/

		double flesch = computeFlesch(num_Syllables, num_Words, num_Sentences);
		double fleschKincaid = computeFleschKincaid(num_Syllables, num_Words, num_Sentences);
		
		
		System.out.println("Flesch: " + String.format("%.0f",flesch));
		System.out.println("Flesch-Kincaid: " + String.format("%.1f",fleschKincaid));
		return (int) flesch;
		}

		public static boolean isVowel(char letter){
			String vowels = "aeiouy";
			return(vowels.indexOf(letter) != -1);
		}

		public static boolean isDigit(char letter){
			String digit = "1234567890";
			return(digit.indexOf(letter) != -1);
		}

		public static boolean isAlpha(char letter){
			String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			return(alpha.indexOf(letter) != -1);
		}

		public static boolean isPunct(char letter){
			String punct = ".:;?!";
			return(punct.indexOf(letter) != -1);
		}

		public static boolean isDelim(char letter){
			String delim = " ,]";
			return(delim.indexOf(letter) != -1);
		}

		public static double computeFlesch(int num_Syllables, int num_Words, int num_Sentences){
			double alpha = (double)num_Syllables/(double)num_Words;
			double beta = (double)num_Words/(double)num_Sentences;
			//Index = 206.835 - 84.6Alpha - 1.015Beta
			return(206.835 - (84.6*alpha) - (1.015*beta));
		}
		public static double computeFleschKincaid(int num_Syllables, int num_Words, int num_Sentences){	
			double alpha = (double)num_Syllables/(double)num_Words;
			double beta = (double)num_Words/(double)num_Sentences;
			//Grade = 11.8alpha + 0.39beta - 15.59
			return( (11.8*alpha) + (0.39*beta) - 15.59);
		}

		
}