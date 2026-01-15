public class Article {
    private String link;
    private String headline;
    private String category;
    private String description;
    private String author;
    private String date;
    public Article (String link, String headline, String category, String description, String author, String date) {
        this.link = link;
        this.headline = headline;
        this.category = category;
        this.description = description;
        this.author = author;
        this.date = date;
    }
    public String toString() {
        return "link="+link+"\n"+headline+"\n"+category+"\n"+description+"\n"+author+", "+date;
    }
    public String getLink(){return link;}
    public String getHeadline(){return headline;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public String getAuthor(){return author;}
    public String getDate(){return date;}

    // public void setLink(String link){this.link=link;}
    // public void setHeadline(String headline){this.headline=headline;}
    // public void setDescription(String description){this.description=description;}
    // public void setCategory(String category){this.category=category;}
    // public void setAuthor(String author){this.author=author;}
    // public void setDate(String date){this.date=date;}
    public static void main(String[] args) {
        Article a = new Article("https://www.nytimes.com/2026/01/13/arts/television/ricky-gervais-mortality-stand-up-golden-globe-win.html", "Did the Worst Netflix Special of the Year Win the Golden Globe?", "Comedy", "That “Ricky Gervais: Mortality” won for best comedy sends a message about the lack of respect given to stand-up.", "Jason Zinoman", "January 13th, 2026");
        System.out.println(a);
        // System.out.println(a.getDescription());
    }
}