package tpExercice3;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// gramaires 
        String[] testCases = {
            "cdcbc", "bcdcbcb", "cbdcbdcbc", "ccdcbcdcbcdcbbcr", "cdcbbb", "cdcb", ""
        };

        for (String ch : testCases) {
            System.out.println("\nTest de la chaîne : " + (ch.isEmpty() ? "ε (vide)" : ch));

            // initiali tm
            TokenManager tm = new TokenManager(ch);

            // initial parseur
            ParseurG parseur = new ParseurG(tm);

            try {
                parseur.parse();
                System.out.println(ch + " est valide");
            } catch (RuntimeException exp) {
                System.out.println(ch + " n'est pas valide");
            }
        }

	}

}
