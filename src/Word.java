public class Word implements Comparable<Word>{
    String word;
    int score;

    public Word(String word) {
        this.word = word;
        this.score = findScore();
    }

    private int findScore() {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            switch (word.charAt(i)) {
                case 'a', 'e', 'i', 'o', 'u', 'l', 'n', 's', 't', 'r' -> score++;
                case 'd', 'g' -> score += 2;
                case 'b', 'c', 'm', 'p' -> score += 3;
                case 'f', 'h', 'v', 'w', 'y' -> score += 4;
                case 'k' -> score += 5;
                case 'j', 'x' -> score += 8;
                case 'q', 'z' -> score += 10;
            }
        }
        return score;
    }

    @Override
    public String toString() {
        return word + ", " + score;
    }

    @Override
    public int compareTo(Word word) {
        return -Integer.compare(score, word.score);
    }
}
