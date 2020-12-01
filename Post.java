package Social;
public interface Post {
    /*
    @OVERVIEW: Post è un tipo di dato astratto immutabile. Viene definito da:
    - un autore;
    - un testo la cui lunghezza appartiene all'intervallo [1, 140];
    - un ID univoco;
    - un timestamp che indica la data e l'ora di pubblicazione del post.
    - un timeStampInMillis che indica i millisecondi che sono passati dal 1 Gennaio 1970
     */

    /*
     * @REQUIRES : true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce l'ID del post
     * @RETURNS: this.ID
     */
     String getID();

    /*
     * @REQUIRES : true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce l'autore del Post
     * @RETURNS: this.author
     */
     String getAuthor();

    /*
     * @REQUIRES : true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce il testo del Post
     * @RETURNS: this.text
     */
     String getText();

    /*
     * @REQUIRES : true
     * @MODIFIES: none
     * @THROWS: none
     * @EFFECTS: restituisce data e ora di pubblicazione del post
     * @RETURNS: this.timeStamp
     */
     String getTimeStamp();

    /*
    * @REQUIRES: true
    * @MODIFIES: none
    * @THROWS: none
    * @EFFECTS: restituisce una Stringa che rappresenta l'istanza del Post
    * @RETURNS: this.author + "\n" + this.timeStamp + "\n" + this.text
    * */
    @Override
     String toString();

    /*
    * @REQUIRES: o isInstanceOf MyPost == true
    * @MODIFIES: none
    * @THROWS: IllegalArgumentException
    * @EFFECTS: restituisce l'uguaglianza tra due istanze di tipo MyPost
    * @RETURNS: o.getID.equals(this.ID), un boolean
    * */
    @Override
     boolean equals(Object o);

    /*
    * @REQUIRES: o != null,
    * @MODIFIES: none
    * @THROWS: NullPointerException
    * @EFFECTS: restituisce un confronto tra le due istanze di MyPost
    * @RETURNS: return o.toString().compareTo(this.toString()), un Integer
    * */
     int compareTo(MyPost o);
}
