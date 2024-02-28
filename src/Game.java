import java.util.*;

public class Game {

    private Dictionary dictionary = new Dictionary();

    private Scanner scanner = new Scanner(System.in);
    private static boolean gameRunning = false;
    private String originalWord = dictionary.generateRandomWord();

    private Set<Character> guessedLetters = new HashSet<>();

    private void greet(){
        System.out.println("Привет! давай сыграем?");
        System.out.println("1 - давай, 2 - нет");
        while(true){
            String agreement = scanner.nextLine();
            if(agreement.equals("1")){
                System.out.println("Отлично! Насчём же!");
                gameRunning = true;
                return;
            } else if (agreement.equals("2")) {
                System.out.println("Хорошо, давай в следующий раз!");
                scanner.close();
                return;
            } else {
                System.out.println("Пожалуйста, выберите один из предложенных вариантов");
            }
        }
    }
    public void launchGame(){
        greet();
        if(gameRunning == true){
            String encodedWord = encodeWord(originalWord);
            System.out.println(encodedWord);
            int attemptCount = 5;
            while(attemptCount > 0){
                System.out.println("Кол-во попыток: " + attemptCount);
                System.out.println("Попробуйте угадать слово. Введите букву");
                char lettersInput = scanner.next().charAt(0);
                if(!Character.isLetter(lettersInput)){
                    System.out.println("Пожалуйста, введите букву");
                    continue;
                }
                if(guessedLetters.contains(lettersInput)){
                    System.out.println("Вы уже вводили эту букву, попробуйте другую");
                    continue;
                }else{
                    guessedLetters.add(lettersInput);
                }
                if(memorizeEncryptedPositions().containsKey(lettersInput)){
                    System.out.println("Вы угадали букву");
                    for (int i = 0; i < originalWord.length(); i++){
                        if(originalWord.charAt(i) == lettersInput){
                            encodedWord = decryptWord(encodedWord, lettersInput, i);
                        }
                    }
                    System.out.println(encodedWord);
                }else{
                    System.out.println("Вы не угадали букву");
                    attemptCount--;
                }
                if(validateGuess()){
                    System.out.println("Поздравляем! Вы отгадали слово: " + originalWord);
                    break;
                }
                if(attemptCount == 0){
                    System.out.println("Вы не отгадали слово. Загаданное слово было: " + originalWord);
                    break;
                }
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

    private Map<Character, List<Integer>> memorizeEncryptedPositions(){
        Map<Character, List<Integer>> letterPositions = new HashMap<>();
        for(int i = 0; i < originalWord.length(); i++){
            char letter = originalWord.charAt(i);
            if(letterPositions.equals(letter)){
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
