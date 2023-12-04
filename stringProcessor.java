import java.util.*;

/**
 The Class stringProcessor is to preprocess the string to get the unique character string (unique)
 and the corresponding Integer array to record the number of each character (mult)
 as the constructor function input of the Rack class later.
 */
public class stringProcessor {

   /**
    Representation invariant:
    * The Treemap charMap contains non-null (or non-Empty) characters as keys, each mapped to a positive integer
    * representing the count of occurrences of that character in the input string.
    */

   private final TreeMap<Character, Integer> charMap; // Use a treeMap to map the char to its counts in the string

   /**
    Construct a stringProcessor to process the input string into a treeMap which maps each char to its counts.
    @param string input string for processing
    */
   public stringProcessor(String string) {
      charMap = new TreeMap<>();
      int length = string.length();
      for (int i = 0; i < length; i++) {
         char a = string.charAt(i); // Pick the ith position of the string as a
         if (charMap.containsKey(a)) {
            charMap.put(a, charMap.get(a) + 1); //Add the count by one if the char has existed in the keySet of charMap
         } else {
            charMap.put(a, 1);   // Add a new pair if the char has not existed in the keySet of charMap
         }
      }
   }

   /**
    Get the counts of the corresponding char String as array for the input string.
    @return an int[] array of counts.
    */
   public int[] getArray(){
      return intSetToArray();
   }

   /**
    Get the String of char for the input string.
    @return a string of chars.
    */
   public String getString(){
      return charSetToString();
   }

   /**
    Convert an integer set to array.
    @return an int[] array.
    */
   private int[] intSetToArray(){
      Collection<Integer> values = charMap.values();
      int size = values.size();
      int[] array = new int[size];
      int i = 0;
      for(char a: charMap.keySet() ){
         array[i] = charMap.get(a);
         i++;
      }
      return array;
   }

   /**
    Convert a char set to String.
    @return a string of chars.
    */
   private String charSetToString() {
      StringBuilder stringBuilder = new StringBuilder();
      for (Character character : charMap.keySet()) {
         stringBuilder.append(character);
      }
      return stringBuilder.toString();
   }
}
