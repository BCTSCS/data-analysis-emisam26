
import java.util.ArrayList;

public class TextProcessor {
    private ArrayList<String> textList;   // The list of text from the file
    /*
     * Constructor to create a TextProcessor with the specified filename
     */
    public TextProcessor(ArrayList<String> lines) {
      setTextList(lines);
    }
    /*
    * Changes the textList to the newTextList
    */
   public void setTextList(ArrayList<String> newTextList) {
    textList = newTextList;
   }
   
    /*
   * Finds and removes all occurrences of each stop word from textList
   */
  public void removeStopWords(ArrayList<String> stopWords) {

    for (String stop:stopWords){
      for (int i=0; i<textList.size(); i++){
        String line = textList.get(i);
        // //middle
        // line = line.replaceAll(" "+stop+" ", " ");
        // // beggning
        // line = line.startsWith(stop)?line.replace(stop, ""):line;
        // //end
        // line = line.replace(" "+stop, "\n");

        // Handle word at the beginning of line
            if (line.startsWith(stop + " ")) {
                line = line.substring(stop.length() + 1);
            }
            
            // Handle word in the middle (with spaces on both sides)
            line = line.replaceAll(" " + stop + " ", " ");
            
            // Handle word at the end of line
            if (line.endsWith(" " + stop)) {
                line = line.substring(0, line.length() - stop.length() - 1);
            }
            
            // Handle case where line contains only the stop word
            if (line.equals(stop)) {
                line = "";
            }
        
        // Clean up multiple spaces that may have been created
        line = line.replaceAll("\\s+", " ").trim();

        textList.set(i, line);
        }
      }
  }
   /*
   * Returns a String containing the text in textList
   */
  public String toString() {
    String text = "";

    for (String value : textList) {
      text = text + value + "\n";
    }

    return text;
  }

    public static void main(String[] args) {
        ArrayList<String> stopwords = FileOperator.getStringList("stopwords.txt");
        ArrayList<String> posts = FileOperator.getStringList("posts.txt");
        TextProcessor t = new TextProcessor(posts);
        t.removeStopWords(stopwords);
        System.out.println(t);
    }
    
}