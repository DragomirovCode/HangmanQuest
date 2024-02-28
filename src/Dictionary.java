import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dictionary {
     private File fileURL = new File("src/words.txt");
     private Map<String, String> dictionary = new HashMap<>();

     public Dictionary(){
          addWordsToDictionary();
     }

     private void addWordsToDictionary(){
          try {
               Scanner scanner = new Scanner(fileURL);
               while(scanner.hasNextLine()){
                    String word = scanner.nextLine();
                    String[] splitResult = word.split(", ");
                    if(splitResult.length == 2){
                         String key = splitResult[0].trim();
                         String value = splitResult[1].trim();
                         dictionary.put(key,value);
                    }
               }
               scanner.close();
          } catch (FileNotFoundException e) {
               System.out.println("Файл не найден.");
               throw new RuntimeException(e);
          }
     }

     public String generateRandomWord(){
          Random random = new Random();
          List<String> keys = new ArrayList<>(dictionary.keySet());
          String randomKeys = keys.get(random.nextInt(keys.size()));
          return randomKeys;
     }
     public Map<String, String> getDictionary() {
          return this.dictionary;
     }
}
