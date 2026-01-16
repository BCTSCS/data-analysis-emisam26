import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleAnalyzer {

    private ArrayList<String> stopWords; //load from FileOperators
    private ArrayList<Article> articles; //load from FileOperators json 

    public ArticleAnalyzer(){
        stopWords = FileOperator.getStringList("stopwords.txt");
        System.out.println("Total stop words: " + stopWords.size());

        articles = new ArrayList<>();
        System.out.println("Total articles: " + articles.size());
    }

    public void addStopWord(String word){
        stopWords.add(word);
    }

    public void addArticle(Article article){
        articles.add(article);
    }
    //use Pattern and matcher to create
    public Article parseJson(String jsonLine){
        Article result;
        Pattern l = Pattern.compile("\"link\":\\s*\"([^\"]+)\"");  //regex to extract words
        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");
        Pattern desc = Pattern.compile("\"short_description\":\\s*\"([^\"]+)\"");
        Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");
        Pattern dat = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");
        
        Matcher lm =l.matcher(jsonLine); //parameter - line of text
        Matcher hm =h.matcher(jsonLine);
        Matcher cm =c.matcher(jsonLine);
        Matcher descm =desc.matcher(jsonLine);
        Matcher am =a.matcher(jsonLine);
        Matcher datem =dat.matcher(jsonLine);

        String link = lm.find() ? lm.group(1) : ""; //extract the destined part
        String headline = hm.find() ? hm.group(1) : ""; 
        String category = cm.find() ? cm.group(1) : ""; 
        String description = descm.find() ? descm.group(1) : ""; 
        String author = am.find() ? am.group(1) : ""; 
        String date = datem.find() ? datem.group(1) : ""; 

        String new_description = removeStopWords(description);

        // result = new Article(link, "", "", "", "", "");
        result = new Article(link, headline, category, new_description, author, date);
        return result;
    }
    //remove stop words from Description
    public String removeStopWords(String text){
        String result = "";
        String[] words = text.split(" ");
        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!stopWords.contains(cleanWord)) {
                result += word + " ";
            }
        }
        return result.trim();
    }

    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> lines = FileOperator.getStringList("data.txt");
        // String line = lines.get(0);
        for (String line : lines) {
            Article a = analyzer.parseJson(line);
            System.out.println(a);
        }
    }

}
