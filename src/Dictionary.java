import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Dictionary {
    ArrayList<ArrayList<String>> WordList;
    String currentChars;

    public Dictionary(String currentLetters) throws IOException {
        this.currentChars = currentLetters;
        WordList = getReducedList(currentLetters);
    }

    private ArrayList<ArrayList<String>> getReducedList(String currentChars) throws IOException {
        ArrayList<ArrayList<String>> totalList = getAllWords();
        ArrayList<ArrayList<String>> reducedList = new ArrayList<>();
        for (ArrayList<String> strings : totalList) {
            char firstChar = strings.get(0).charAt(0);
            if (currentChars.indexOf(firstChar) != -1) {
                reducedList.add(strings);
            }
        }

        return reducedList;
    }

    private ArrayList<ArrayList<String>> getAllWords() throws IOException {
        File file = new File("C:/Users/sehar/IdeaProjects/ScrabbleCheater/src/WordList");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String word;
        char firstChar = 'a';
        ArrayList<String> wordsOfAlphabet = new ArrayList<>();
        ArrayList<ArrayList<String>> totalList = new ArrayList<>();

        while ((word = br.readLine()) != null) {
            if (word.charAt(0) == firstChar) {
                wordsOfAlphabet.add(word);
            } else {
                totalList.add(wordsOfAlphabet);
                wordsOfAlphabet = new ArrayList<>();
                wordsOfAlphabet.add(word);
                firstChar = word.charAt(0);
            }
        }

        totalList.add(wordsOfAlphabet);

        return totalList;
    }

    public ArrayList<Word> getValidWords() {
        ArrayList<Word> validWords = new ArrayList<>();
        for (ArrayList<String> list : WordList) {
            for (String word : list) {
                if (word.length() > currentChars.length()) {
                    continue;
                }
                StringBuilder currentCharSb = new StringBuilder(currentChars);
                boolean addFlag = true;
                for (int i = 0; i < word.length(); i++) {
                    String charToFind = String.valueOf(word.charAt(i));
                    int indexOfCharInSb;
                    if ((indexOfCharInSb = currentCharSb.indexOf(charToFind)) == -1) {
                        addFlag = false;
                        break;
                    }
                    currentCharSb.deleteCharAt(indexOfCharInSb);
                }
                if (addFlag) {
                    validWords.add(new Word(word));
                }

            }
        }

        Collections.sort(validWords);
        return validWords;
    }



    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Dictionary d = new Dictionary("hyadise");
        ArrayList<Word> valid = d.getValidWords();
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.printf("main executed in %d ms\n", time);

    }

}
