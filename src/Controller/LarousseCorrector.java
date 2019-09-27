package Controller;

import java.text.Normalizer;

import Model.Model;

public class LarousseCorrector {
	public static String LarousseCorrectorRun(String textDecrypted, Model model) {
		String result = "";
		
		
		textDecrypted = textDecrypted.replace(",", " ");
		textDecrypted = textDecrypted.replace(".", " ");
		textDecrypted = textDecrypted.replace("!", "");
		textDecrypted = textDecrypted.replace("?", "");

		textDecrypted = textDecrypted.replace("'", " ");
		textDecrypted = textDecrypted.replace("  ", " ");

		textDecrypted = stripAccents(textDecrypted);
		textDecrypted = textDecrypted.toLowerCase();
		String[] textDecryptedCut = textDecrypted.split(" ");
		
		for(int i = 0; i < textDecryptedCut.length; i++) {
			if(model.selectWord(textDecryptedCut[i]) != textDecryptedCut[i]) {
				//System.out.println("iiiiiiii" + textDecryptedCut[i]);
				textDecryptedCut[i] = model.selectWordWithError(textDecryptedCut[i]);
				//System.out.println("ooooooooooo : " + textDecryptedCut[i]);
			}
		}
		result = String.join(" ", textDecryptedCut);
		return result;
	}
	
	public static String stripAccents(String s) {
		s = Normalizer.normalize(s, Normalizer.Form.NFD);
		s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return s;
	}
}
