import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
     private File fileURL = new File("src/words.txt");
     private Map<String, String> dictionary = new HashMap<>();

     public Dictionary(){
          addAWord();
     }

     private void addAWord(){
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

     public Map<String, String> getDictionary() {
          return this.dictionary;
     }
}
