import java.io.FileNotFoundException;
import java.util.*;

/**
 The class WordFinder is designed to find word composed of the given letters in the specified dictionary
 and sort them based on the scores.
 */
public class WordFinder {
   public static void main(String[] args){
      String fileName = args.length>0 ? args[0] : "sowpods.txt";  // A dictionary filename can be given in args;
      try {                                                       // By default, the dictionary name is "sowpods.txt" when no input exists for dictionary file.
         AnagramDictionary dictionary = new AnagramDictionary(fileName); // Generate an AnagramDictionary instance based on the dictionary.
         System.out.println("Type . to quit.");
         Scanner scanner = new Scanner(System.in);
         while (true) {
            System.out.print("Rack? ");
            String lettersRaw = scanner.nextLine();   // Get the input letters.
            if (lettersRaw.equals(".")) { // Exit when "." is entered.
               break;
            } else {
               stringProcessor strPro = new stringProcessor(lettersRaw); // Preprocess the raw letters
               String unique = strPro.getString();                       // Get unique chars in a string
               int[] mult = strPro.getArray();                           // Get corresponding counts in an array
               ArrayList<String> wordSets = Rack.getAllSubsets(unique, mult);  // Get all subsets of different words
               TreeMap<String, Integer> sortedWords = new TreeMap<>(new ScoreComparator()); //Generate a treeMap sortedWords ordered by score
               for(String word: wordSets){
                  ArrayList<String> anagramSet = dictionary.getAnagramsOf(word); //Get all anagrams of a word from the dictionary
                  if(!anagramSet.isEmpty()){
                     for(String s: anagramSet) {
                        sortedWords.put(s, ScoreTable.getWordScore(s)); // Record the pair (anagram, scores) in the treeMap sortedWords
                     }
                  }
               }
               printInstructionsAndWords(sortedWords, lettersRaw);   // Print the instructions and results (listed words and their scores)
            }
         }
      }
      catch (FileNotFoundException e1) {     // This happens when the dictionary file cannot be found.
            System.out.println("ERROR: Dictionary file \""+removeBracket(e1.getMessage())+"\" does not exist.");
            System.out.println("Exiting program.");
      }
      catch (IllegalDictionaryException e2) {   // This happens when the dictionary file has at least a a duplicate word.
         System.out.println("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + e2.getMessage());
         System.out.println("Exiting program.");
      }
   }

   /**
    Remove the content in the bracket (also remove the bracket) of a string.
    @param s input string
    @return string without the brackets or contents inside.
    */
   private static String removeBracket(String s){
      String s1 = s.replaceAll("\\(.*?\\)", "");
      return s1.substring(0, s1.length()-1);
   }

   /**
    Print instructions and all strings found with their corresponding scores.
    @param treeMap containing all strings found and corresponding scores.
    @param letters original input letters.
    */
   private static void printInstructionsAndWords(TreeMap<String, Integer> treeMap, String letters){
      int number = treeMap.size(); // Number of words found.
      System.out.print("We can make " + number + " words from \""+ letters + "\"\n");
      if(number == 0){
         return;  // Exit the method when no word is found in the dictionary.
      } else {
         System.out.println("All of the words with their scores (sorted by score):");
         for (String word : treeMap.keySet()) { // Print all words and scores in treeMap.
            System.out.print(treeMap.get(word));
            System.out.print(": ");
            System.out.println(word);
         }
      }
   }


   /**
    Define a comparator for the treeMap by comparing the score.
    If the scores are the same, then compare the alphabetical order.
    */
   public static class ScoreComparator implements Comparator<String> {
      @Override
      public int compare(String s1, String s2) {
         int score1 = ScoreTable.getWordScore(s1);
         int score2 = ScoreTable.getWordScore(s2);
         if(score2 == score1){
            return s1.compareTo(s2);   //If the scores are the same, then compare the alphabetical order.
         }else {
            return Integer.compare(score2, score1);   // Compare the scores to decide the order in the treeMap.
         }
      }
   }
}
