// Name: Qiaochu Zhang
// USC NetID: qzhang40
// CS 455 PA4
// Fall 2023

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
   A dictionary of all anagram sets. 
   the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {

   /**
    Representation invariant:

    1. The AnagramDictionary contains a valid TreeMap "anagramMap" object to map the letters to the anagram words.
    2. The "anagramMap" TreeMap stores a collection of anagram sets where:
            Keys: Strings representing sorted characters (words sorted in alphabetical order).
            Values: ArrayLists containing anagrams (words with the same sorted characters).
    3. The TreeMap "anagramMap" must not be null and should be properly initialized.
    4. The words in the keySet of the treeMap are sorted in alphabetical order.
    5. The sorting of words into their corresponding anagram sets is case-sensitive.
    */

   private final HashMap<String, ArrayList<String>> anagramMap; // this treeMap is used to contain the string of letters
                                                                // as key and its corresponding anagrams as value (ArrayList)


   /**
      An anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {

      File file =new File(fileName);
      anagramMap = new HashMap<>();    // Initialize a treeMap to store the letters and anagrams
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
         String nextWord = scanner.nextLine();  // Scan the next word in the file
         String wordSorted = sortWord(nextWord);   // Sort the word in alphabetical order
         if (!anagramMap.containsKey(wordSorted)) {
            ArrayList<String> newStringList = new ArrayList<>();
            newStringList.add(nextWord);
            anagramMap.put(wordSorted, newStringList);   // Add a new pair when a new string of sorted letters does not exist before
         }
         else {
            ArrayList<String> wordString = anagramMap.get(wordSorted);
            if(!wordString.contains(nextWord)){
               wordString.add(nextWord);     // Add the word to the former arrayList in the Map valueSet
            } else {
               throw new IllegalDictionaryException(nextWord); // Find duplicate words
            }
         }
      }
   }


   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      Return an empty arrayList when the string does not have an anagram in the dictionary.
      @param string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String string) {
      String copyString = new String(string);
      if(copyString.isEmpty()){
         return new ArrayList<>();
      }
      else {
         String sortedString = sortWord(copyString);
         if(anagramMap.containsKey(sortedString)){
            return anagramMap.get(sortedString);   // Return an arrayList of words in the dictionary for the sortedString
         }
         else {
            return new ArrayList<>();
         }
      }
   }

   /**
    Sort a string in alphabetical order.
    @param s to process
    @return a sorted string
    */
   private String sortWord(String s){
      char[] charArray = s.toCharArray();
      Arrays.sort(charArray);
      return new String(charArray);
   }
}
