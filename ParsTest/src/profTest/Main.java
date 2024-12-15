package profTest;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ch = "bcde";
		// L'analyseur lexical
		TokenManager tm = new TokenManager(ch);
		
		// L'analyseur syntaxique
		ParseurG1 parseur = new ParseurG1(tm);
		try {
			parseur.parse();
			System.out.println(ch + " est valide");
		}
		catch(RuntimeException exp) {
			System.out.println(ch + " n'est pas valide");
		}
		
		
	}

}
