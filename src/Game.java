import java.util.*;

public class Game {

    private Dictionary dictionary = new Dictionary();
    private PersonState personState;
    private Scanner scanner = new Scanner(System.in);
    private static boolean gameRunning = false;
    private String originalWord = dictionary.generateRandomWord();

    private Set<Character> guessedLetters = new HashSet<>();

    private void greet(){
        System.out.println("Выберите один из предложенных вариантов");
        System.out.println("1 - играть, 2 - выйти");
        while(true){
            String agreement = scanner.nextLine();
            if(agreement.equals("1")){
                System.out.println("Отлично! Начнём же!");
                gameRunning = true;
                break;
            } else if (agreement.equals("2")) {
                System.out.println("Хорошо, давай в следующий раз!");
                break;
            } else {
                System.out.println("Пожалуйста, выберите один из предложенных вариантов");
            }
        }
    }
    public void launchGame() {
        greet();
        if (gameRunning) {
            String encodedWord = encodeWord(originalWord);
            System.out.println(encodedWord);
            int attemptCount = 5;
            while (attemptCount > 0) {
                System.out.println("Кол-во попыток: " + attemptCount);
                System.out.println("Попробуйте угадать слово. Введите букву");
                provideHint();
                String lettersInput = scanner.next();
                if (lettersInput.length() == 1 && containsCyrillic(lettersInput)) {
                    char lowerCaseInput = Character.toLowerCase(lettersInput.charAt(0));
                    if (!Character.isLetter(lettersInput.charAt(0))) {
                        System.out.println("Можно вводить только буквы");
                        continue;
                    }
                    if (guessedLetters.contains(lowerCaseInput)) {
                        System.out.println("Вы уже вводили эту букву, попробуйте другую");
                        continue;
                    } else {
                        guessedLetters.add(lowerCaseInput);
                    }
                    if (memorizeEncryptedPositions().containsKey(lowerCaseInput)) {
                        System.out.println("Вы угадали букву");
                        for (int i = 0; i < originalWord.length(); i++) {
                            if (originalWord.charAt(i) == lowerCaseInput) {
                                encodedWord = decryptWord(encodedWord, lowerCaseInput, i);
                            }
                        }
                        System.out.println(encodedWord);
                    } else {
                        System.out.println("Вы не угадали букву");
                        attemptCount--;
                        personState = PersonState.getStateByCode(attemptCount);
                        personState.printMap();
                    }
                    if (validateGuess()) {
                        System.out.println("Поздравляем! Вы отгадали слово: " + originalWord);
                        gameRunning = false;
                        break;
                    }
                    if (attemptCount == 0) {
                        System.out.println("Вы не отгадали слово. Загаданное слово было: " + originalWord);
                        gameRunning = false;
                        break;
                    }
                }else{
                    System.out.println("Можно вводить только один символ(букву) и он должен быть на кириллицы");
                }
            }
            offerPlayAgain();
        }
    }
    private boolean containsCyrillic(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC) {
                return true;
            }
        }
        return false;
    }
    private void offerPlayAgain(){
        while(true) {
            originalWord = dictionary.generateRandomWord();
            guessedLetters.clear();
            launchGame();

            System.out.println("Хотите сыграть ещё раз? (да/нет)");
            String playAgain = scanner.nextLine().trim().toLowerCase();

            if (playAgain.equals("да")) {
                // Продолжаем игру
            } else if (playAgain.equals("нет")) {
                System.out.println("Спасибо за игру! До свидания!");
                break;
            }
        }
    }

    private String decryptWord(String encryptedWord, char letter, int index){
        char[] chars = encryptedWord.toCharArray();
        chars[index] = letter;
        return new String(chars);
    }

    private String encodeWord(String word){
        char[] charArray = new char[word.length()];
        for(int i = 0; i < word.length(); i++){
           charArray[i] = '*';
        }
        String encodedWord = new String(charArray);
        return encodedWord;
    }

    private void provideHint(){
        System.out.println("Подсказка: " + dictionary.getDictionary().get(originalWord));
    }

    private Map<Character, List<Integer>> memorizeEncryptedPositions(){
        Map<Character, List<Integer>> letterPositions = new HashMap<>();
        for(int i = 0; i < originalWord.length(); i++){
            char letter = originalWord.charAt(i);
            if(letterPositions.containsKey(letter)){
                letterPositions.get(letter).add(i);
            }else{
                List<Integer> positions = new ArrayList<>();
                positions.add(i);
                letterPositions.put(letter, positions);
            }
        }
        return letterPositions;
    }

    private boolean validateGuess(){
        for(int i = 0; i < originalWord.length(); i++){
            if(!guessedLetters.contains(originalWord.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
