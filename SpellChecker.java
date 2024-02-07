
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if(str.length()==1)
		{
			return "";
		}
		return str.substring(1);
		}


	public static int levenshtein(String word1, String word2) {
		String lowerC1= word1.toLowerCase();
		String lowerC2=word2.toLowerCase();

		int lword1= word1.length();
		int lword2= word2.length();

		if(lword2==0)
		{
			return lword1;
		}
		if(lword1==0)
		{
			return lword2;
		}
		if(lowerC1.charAt(0)==lowerC2.charAt(0))
		{
			return levenshtein(tail(lowerC1), tail(lowerC2));
		}
		else
		{
			
			return 1+Math.min(Math.min(levenshtein(tail(lowerC1),lowerC2),
			levenshtein(lowerC1,tail(lowerC2))),levenshtein(tail(lowerC1), tail(lowerC2)));
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i=0; i<dictionary.length; i++)
		{
			dictionary[i]=in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min= word.length();
		String minimal=word;

		for(int i=0; i<dictionary.length;i++)
		{
			int distance= levenshtein(word, dictionary[i]);
			if(distance<=threshold)
			{
				if(distance<min)
				{
					min=distance;
					minimal=dictionary[i];
				}
			}
		}
		return minimal;
	}
	
}
