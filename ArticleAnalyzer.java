// import java.io.File;
import java.awt.event.WindowAdapter;
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
        Matcher lm =l.matcher(jsonLine); //parameter - line of text
        String lt = lm.find() ? lm.group(1) : ""; //extract the destined part

        Pattern h = Pattern.compile("\"headline\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher hm =h.matcher(jsonLine); //parameter - line of text
        String ht = hm.find() ? hm.group(1) : ""; //extract the destined part
        
        Pattern c = Pattern.compile("\"category\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher cm =c.matcher(jsonLine); //parameter - line of text
        String ct = cm.find() ? cm.group(1) : ""; //extract the destined part

        Pattern d = Pattern.compile("\"short_description\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher dm =d.matcher(jsonLine); //parameter - line of text
        String dt = dm.find() ? dm.group(1) : ""; //extract the destined part

        Pattern a = Pattern.compile("\"authors\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher am =a.matcher(jsonLine); //parameter - line of text
        String at = am.find() ? am.group(1) : ""; //extract the destined part

        Pattern t = Pattern.compile("\"date\":\\s*\"([^\"]+)\"");  //regex to extract words
        Matcher tm =t.matcher(jsonLine); //parameter - line of text
        String tt = tm.find() ? tm.group(1) : ""; //extract the destined part

        result=new Article(lt, ht, ct, dt, at, tt);
        return result;
    }
    //remove stop words from Description
    public String removeStopWords(String text){
        // String result = "";
        for (String stop : stopWords) {
            text = text.replaceAll("\\b"+stop+"\\b", "");
        }
        return text;
    }

    public static void main(String[] args) {
        ArticleAnalyzer analyzer = new ArticleAnalyzer();
        ArrayList<String> lines = FileOperator.getStringList("data.txt");
        String line = lines.get(0);
        Article a = analyzer.parseJson(line);
        String clean = analyzer.removeStopWords(a.getDescription());
        a.setDescription(clean);
        analyzer.addArticle(a);
        System.out.println(a);
        // for (String line : lines) {
        //     Article a = analyzer.parseJson(line);
        //     String clean = analyzer.removeStopWords(a.getDescription());
        //     a.setDescription(clean);
        //     analyzer.addArticle(a);
        //     System.out.println(a);
        // }
    }

}
