package Social;
import java.util.Calendar;

public class MyPost implements Post, Comparable<MyPost>{
    /*@REPINV:
    *         (id != null && != empty) &&
    *         (author != null && != empty) &&
    *         (text != null && != empty) &&
    *         (TimeSTamp != null && != empty)
    *         (timeStampInMilli >= 0)
    *         (1 <= text.len <= 140)
    */

    /*@ABSFUNCT Funzione α t.c.:
    * α(String ID) -> id univoco del post
    * α(String author) -> autore del post
    * α(String text) ->  stringa la cui lunghezza è compresa in [1, 140]
    * α(String timestamp) -> data e orario di pubblicazione del post
    * α(long timeInMillis) -> millisecondi dal 1 gennaio 1970
    *  */

    private final String ID;
    private final String text;
    private final String author;
    private final String timestamp;
    private final long timeStampInMillis;

    /*
    * @REQUIRES : (author != null && != empty) && (1 <= text.lenght <= 140) && (text != null)
    * @MODIFIES: this.text, this.ID, this.author, this.timestamp
    * @THROWS: NullPointerException, IllegalArgumentException
    * @EFFECTS: new instance of class "MyPost"
     */

    public MyPost (String text, String author) throws NullPointerException, IllegalArgumentException {

        if(author.isEmpty())
            throw new IllegalArgumentException("Author can not be empty");

        if(text.isEmpty())
            throw new IllegalArgumentException("Text can not be empty");

        if(text.length() > 140)
            throw new IllegalArgumentException("text is too long, max length is 140");

        this.text = text;
        this.author = author;
        timestamp = Calendar.getInstance().getTime().toString();
        timeStampInMillis = (int)Calendar.getInstance().getTimeInMillis();
        ID = Calendar.getInstance().getTimeInMillis() + "" + author;
    }

    public String getID(){
        return ID;
    }

    public String getAuthor(){
        return author;
    }

    public String getText(){
        return text;
    }

    public String getTimeStamp(){
        return timestamp;
    }

    @Override
    public boolean equals(Object o) throws IllegalArgumentException{
        if(!(o instanceof MyPost))
            throw new IllegalArgumentException("o is not an instance of MyPost");

        return (((MyPost) o).getID().equals(this.ID));
    }

    @Override
    public String toString(){
        return (this.author + "\n" + this.timestamp + "\n" + this.text);
    }

    public int compareTo(MyPost o) throws NullPointerException{
        if(o == null)
            throw new NullPointerException("not initialized");
        return o.toString().compareTo(this.toString());
    }

}