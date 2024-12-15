package test;
import java.util.Scanner;

public class RecursiveParse {

    private String input;
    private int position;

    public RecursiveParse(String input) {
        this.input = input + "#"; // Ajouter '#' pour marquer la fin de la chaîne
        this.position = 0;
    }

    private char currentChar() {
        return input.charAt(position); // Retourne le caractère actuel
    }

    private void match(char expected) throws Exception {
        if (currentChar() == expected) {
            position++;
        } else {
            throw new Exception("Erreur : attendu '" + expected + "', trouvé '" + currentChar() + "'");
        }
    }

    private void parseS() throws Exception {
        if (currentChar() == 'b') {
            match('b');
            parseS();
            match('b');
        } else if (currentChar() == 'c') {
            match('c');
            parseA();
            match('c');
        } else {
            throw new Exception("Erreur dans S : attendu 'b' ou 'c', trouvé '" + currentChar() + "'");
        }
    }

    private void parseA() throws Exception {
        if (currentChar() == 'b') {
            match('b');
            parseA();
            parseA();
        } else if (currentChar() == 'c') {
            match('c');
            parseA();
            parseS();
            parseA();
            match('b');
        } else if (currentChar() == 'd') {
            match('d');
            match('c');
            match('b');
        } else {
            throw new Exception("Erreur dans A : attendu 'b', 'c', ou 'd', trouvé '" + currentChar() + "'");
        }
    }

    public boolean parse() {
        try {
            if (input.equals("#")) {
                return true; // Chaîne vide est valide
            }
            parseS();
            if (currentChar() == '#') {
                return true; // La chaîne est valide si on arrive à la fin
            } else {
                throw new Exception("Erreur : des caractères supplémentaires restent après analyse.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] testCases = {
            "cdcbc", "bcdcbcb", "cbdcbdcbc", "ccdcbcdcbcdcbbcr", "cdcbbb", "cdcb", ""
        };

        System.out.println("Tester avec des chaînes prédéfinies :");
        for (String test : testCases) {
            System.out.println("\nTest de la chaîne : " + (test.isEmpty() ? "ε (vide)" : test));
            RecursiveParser parser = new RecursiveParser(test);
            boolean result = parser.parse();
            System.out.println("Résultat : " + (result ? "Valide" : "Invalide"));
        }

        System.out.println("\nEntrer une chaîne à tester (ou tapez 'exit' pour quitter) :");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            RecursiveParser parser = new RecursiveParser(input);
            boolean result = parser.parse();
            System.out.println("Résultat : " + (result ? "Valide" : "Invalide"));
        }

        scanner.close();
    }
}