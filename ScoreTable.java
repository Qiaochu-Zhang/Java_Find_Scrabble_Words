import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;


/**
 A ScoreTable to get the score of a specific letter or word.
 */
public class ScoreTable {

   /**
    Get the score of a letter.
    @param letter  the letter to get the score.
    @return an integer of the score
    */
   public static int getLetterScore(char letter){
      char letterLower = Character.toLowerCase(letter); // Higher case or lower case of a letter has the same score.
      // The charListX below represents the char list that can get a score of "X".
      List<Character> charList1 = Arrays.asList('a', 'e', 'i', 'o', 'u', 'l', 'n', 's', 't', 'r');
      List<Character> charList2 = Arrays.asList('d', 'g');
      List<Character> charList3 = Arrays.asList('c', 'b', 'm', 'p');
      List<Character> charList4 = Arrays.asList('f', 'h', 'v', 'w', 'y');
      List<Character> charList5 = Arrays.asList('k');
      List<Character> charList8 = Arrays.asList('j','x');
      List<Character> charList10 = Arrays.asList('q', 'z');
      if(charList10.contains(letterLower)){
         return 10;
      }
      else if(charList8.contains(letterLower)){
         return 8;
      }
      else if(charList5.contains(letterLower)){
         return 5;
      }
      else if(charList4.contains(letterLower)){
         return 4;
      }
      else if(charList3.contains(letterLower)){
         return 3;
      }
      else if(charList2.contains(letterLower)){
         return 2;
      }
      else if(charList1.contains(letterLower)){
         return 1;
      }
      return 0;
   }

   /**
    Get the score of a word.
    @param word  the word to get the score.
    @return an integer of the score
    */
   public static int getWordScore(String word){
      int score = 0;
      for(char c: word.toCharArray()){
         score += getLetterScore(c);
      }
      return score;
   }
}
