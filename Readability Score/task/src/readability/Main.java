package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordSequence {
    String inputString;

    public WordSequence(String inputString) {
        this.inputString = inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public int getCountWord() {
        return inputString.split("\\s+").length;
    }

    public int getCharacters() {
        String[] outputString = inputString.split(" ");
        int count = 0;
        for (String s : outputString) {
            count += s.length();
        }
        return count;
    }
}

class Score {
    int words;
    int sentences;
    int syllables;
    int polysyllables;
    int characters;

    public Score(int words, int sentences, int syllables, int polysyllables, int characters) {
        this.words = words;
        this.sentences = sentences;
        this.syllables = syllables;
        this.polysyllables = polysyllables;
        this.characters = characters;
    }

    public void ari() {
        String formattedDouble = String.format("%.2f", returnScore());
        System.out.println("Automated Readability Index: " + formattedDouble +
                " (about " + returnAge(returnScore()) + " year olds).");
    }

    public void fk() {
        String formattedDouble = String.format("%.2f", returnFK());
        System.out.println("Flesch–Kincaid readability tests: " + formattedDouble +
                " (about " + returnAge(returnFK()) + " year olds).");
    }

    public void smog() {
        String formattedDouble = String.format("%.2f", returnSMOG());
        System.out.println("Simple Measure of Gobbledygook: " + formattedDouble +
                " (about " + returnAge(returnSMOG()) + " year olds).");
    }

    public void cl() {
        String formattedDouble = String.format("%.2f", returnCL());
        System.out.println("Coleman–Liau index: " + formattedDouble +
                " (about " + returnAge(returnCL()) + " year olds).");
    }

    public float getAverageAge() {
        return (float) (returnAge(returnScore()) + returnAge(returnFK()) +
                returnAge(returnSMOG()) + returnAge(returnCL())) / (float) 4;
    }

    private float returnScore() {
        return (float) ((4.71 * ((float) characters / (float) words)) + (0.5 * ((float) words / (float) sentences)) - 21.43);
    }

    private float returnFK() {
        float first = (float) words / sentences;
        float second = (float) syllables / words;
        return (float) (0.39 * first + 11.8 * second - 15.59);
    }

    private float returnSMOG() {
        return (float) (1.043 * Math.sqrt(polysyllables * 30 / (float) sentences) + 3.1291);
    }

    private float returnCL() {
        return (float) ((float) 0.0588 * (float) characters / words * 100 - 0.296 * (float) sentences / words * 100 - 15.8);
    }

    private int returnAge(float score) {
        int inputScore = (int) Math.ceil(score);
        switch (inputScore) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 9;
            case 4:
                return 10;
            case 5:
                return 11;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 14;
            case 9:
                return 15;
            case 10:
                return 16;
            case 11:
                return 17;
            case 12:
                return 18;
            case 13:
            case 14:
                return 24;
            default:
                return -1;
        }
    }

}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        String inputString = scanner.nextLine();
        scanner.close();

        int countSentences = 0;

        countSentences += inputString.split("!").length - 1;
        countSentences += inputString.split("\\.").length - 1;
        countSentences += inputString.split("\\?").length - 1;

        final String s = inputString.charAt(inputString.length() - 1) + "";

        if (!s.equals("!") ||
                !s.equals("\\.") ||
                !s.equals("\\?")) {
            countSentences++;
        }

        countSentences++;

        WordSequence[] wordSequence = new WordSequence[countSentences];

        StringBuilder tempString = new StringBuilder("");

        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {
            if ((inputString.charAt(i) + "").equals("!") ||
                    (inputString.charAt(i) + "").equals(".") ||
                    (inputString.charAt(i) + "").equals("?")) {
                if (i != inputString.length() - 1) {
                    wordSequence[count] = new WordSequence(tempString.toString());
                    tempString = new StringBuilder("");
                    count++;
                }
            } else {
                tempString.append(inputString.charAt(i));
            }
        }

        wordSequence[wordSequence.length - 1] = new WordSequence(tempString.toString());

        count = 0;

        for (int i = 0; i < wordSequence.length; i++) {
            try {
                count += wordSequence[i].getCountWord();
            } catch (NullPointerException ex) {

            }
        }

        int words = inputString.split(" ").length;
        int sentences = countSentences;
        int characters = returnCountWord(inputString);
        int syllables = countSyllables(inputString);
        int polysyllables = countPolysyllables(inputString);

        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        Scanner scanner1 = new Scanner(System.in);
        String answer = scanner1.nextLine();
        System.out.println("");
        Score score = new Score(words, sentences, syllables, polysyllables, characters);
        switch (answer) {
            case "ARI":
                score.ari();
                break;
            case "FK":
                score.fk();
                break;
            case "SMOG":
                score.smog();
                break;
            case "CL":
                score.cl();
                break;
            case "all":
                score.ari();
                score.fk();
                score.smog();
                score.cl();
                break;
            default:
                break;
        }
        System.out.println("\nThis text should be understood in average by " + score.getAverageAge() + " year olds.");
    }

    public static void returnEasyHard(int inputInt) {
        if (inputInt > 10) {
            System.out.println("HARD");
        } else {
            System.out.println("EASY");
        }
    }

    public static int returnCountWord(String inputString) {
        return inputString.length() - inputString.split(" ").length + 1;
    }

    public static int returnCharacters(WordSequence[] wordSequence, int countSentences) {
        int count = 0;
        for (int i = 0; i < wordSequence.length; i++) {
            count += wordSequence[i].getCharacters();
        }
        return count + countSentences - 1;
    }

    public static int countSyllables(String inputString) {
        String cleanText = cleanLine(inputString);
        String[] word = cleanText.split(" ");
        int syllables = 0;
        for (String w : word) {
            if (w.length() > 0) {
                syllables += Syllabify.syllable(w);
            }
        }
        return syllables;
    }

    private static String cleanLine(String line) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c < 128 && Character.isLetter(c)) {
                buffer.append(c);
            } else {
                buffer.append(' ');
            }
        }
        return buffer.toString().toLowerCase();
    }

    public static int countPolysyllables(String s) {
        int count = 0;
        String[] inputString = s.split(" ");
        for (int i = 0; i < inputString.length; i++) {
            if (countSyllables(inputString[i]) >= 3) {
                count++;
            }
        }
        return count;
    }

}

class Syllabify {

    private static final Pattern VOWELS = Pattern.compile("[^aeiouy]+");

    private static final String[] staticSubMatches = {"cial", "tia", "cius", "cious", "giu", "ion", "iou"};
    private static final Pattern[] regexSubMatches = {
            Pattern.compile(".*sia$"),
            Pattern.compile(".*.ely$"),
            Pattern.compile(".*[^td]ed$")
    };

    private static final String[] staticAddMatches = {"ia", "riet", "dien", "iu", "io", "ii", "microor"};
    private static final Pattern[] regexAddMatches = {
            Pattern.compile(".*[aeiouym]bl$"),
            Pattern.compile(".*[aeiou]{3}.*"),
            Pattern.compile("^mc.*"),
            Pattern.compile(".*ism$"),
            Pattern.compile(".*isms$"),
            Pattern.compile(".*([^aeiouy])\\1l$"),
            Pattern.compile(".*[^l]lien.*"),
            Pattern.compile("^coa[dglx]..*"),
            Pattern.compile(".*[^gq]ua[^auieo].*"),
            Pattern.compile(".*dnt$")
    };

    public static int syllable(String word) {

        word = word.toLowerCase();
        if (word.equals("w")) {
            return 2;
        }
        if (word.length() == 1) {
            return 1;
        }
        word = word.replaceAll("'", " ");

        if (word.endsWith("e")) {
            word = word.substring(0, word.length() - 1);
        }

        String[] phonems = VOWELS.split(word);

        int syl = 0;
        for (int i = 0; i < staticSubMatches.length; i++) {
            if (word.contains(staticSubMatches[i])) {
                syl -= 1;
            }
        }
        for (int i = 0; i < regexSubMatches.length; i++) {
            if (regexSubMatches[i].matcher(word).matches()) {
                syl -= 1;
            }
        }
        for (int i = 0; i < staticAddMatches.length; i++) {
            if (word.contains(staticAddMatches[i])) {
                syl += 1;
            }
        }
        for (int i = 0; i < regexAddMatches.length; i++) {
            if (regexAddMatches[i].matcher(word).matches()) {
                syl += 1;
            }
        }

        for (int i = 0; i < phonems.length; i++) {
            if (phonems[i].length() > 0) {
                syl++;
            }
        }

        if (syl == 0) {
            syl = 1;
        }

        return syl;
    }

}
