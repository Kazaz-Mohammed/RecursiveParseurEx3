package profTest;

public class ParseurG1 {
	private TokenManager tm;
	private char tc; // token courant
	
	private void avancer() {
		tc = tm.suivant();
	}
	
	private void consommer (char attendu) {
		if(tc == attendu)
			avancer();
		else
			throw new RuntimeException("Errur: attendu," + attendu + " mais " + tc + " a ete trouve");
	}
	
	public ParseurG1(TokenManager tm) {
		this.tm = tm;
		avancer();
	}
	
	// S--> BD
	private void S() {
		B(); 
		D();
	}
	
	// B --> bB |c
	private void B() {
		if(tc == 'b') {
			consommer('b');
			B();
		}
		else if(tc == 'c')
			consommer('c');
		else
			throw new RuntimeException("Erreur: " + tc);
	}
	
	//D --> de
	private void D() {
		consommer('d');
		consommer('e');
	}
	
	public void parse() {
		S();
		if(tc != '#')
			throw new RuntimeException("Errer: " + tc);
	}
	

}
