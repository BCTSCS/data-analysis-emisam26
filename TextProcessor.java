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
    /*ArrayList<String> removeStop = new ArrayList<>();
    //itterate through lines
    for (String text:textList){
      String line = text;
      //itterate through stop words
      for (String word:stopWords){
        if (text.contains(word)){
          int index = text.indexOf(word);
          int len = word.length;
          line = line.substring(0,index) + line.substring(index+len);
        }
      }
      //add to new list
      removeStop.add(line);
    }
    System.out.println(removeStop);*/

    for (String stop:stopWords){
      for (int i=0; i<textList.size(); i++){
        String line = textList.get(i);
        ArrayList<String> words = FileOperator.getWords(line);
        for (int j=0; j<words.size(); j++){
          if (words.get(j).equals(stop)){
            words.remove(j);
            j--;
          }
        }
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

    }
    
}
