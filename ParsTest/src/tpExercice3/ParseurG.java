package tpExercice3;

public class ParseurG {

	private TokenManager tm;
    private char tc; // Token courant

    private void avancer() {
        tc = tm.suivant();
    }

    private void consommer(char attendu) {
        if (tc == attendu)
            avancer();
        else
            throw new RuntimeException("Erreur : attendu '" + attendu + "' mais '" + tc + "' a été trouvé");
    }

    public ParseurG(TokenManager tm) {
        this.tm = tm;
        avancer(); // initial 1er token
    }

    // S --> bSb | cAc
    private void S() {
        if (tc == 'b') {
            consommer('b');
            S();
            consommer('b');
        } else if (tc == 'c') {
            consommer('c');
            A();
            consommer('c');
        } else {
            throw new RuntimeException("Erreur dans S : caractère inattendu '" + tc + "'");
        }
    }

    // A --> bAA | cASAb | dcb
    private void A() {
        if (tc == 'b') {
            consommer('b');
            A();
            A();
        } else if (tc == 'c') {
            consommer('c');
            A();
            S();
            A();
            consommer('b');
        } else if (tc == 'd') {
            consommer('d');
            consommer('c');
            consommer('b');
        } else {
            throw new RuntimeException("Erreur dans A : caractère inattendu '" + tc + "'");
        }
    }

    public void parse() {
        S();
        if (tc != '#') // Si la fin de la chaine n'est pas atteinte
            throw new RuntimeException("Erreur : des caractères supplémentaires restent après analyse.");
    }
	
}
