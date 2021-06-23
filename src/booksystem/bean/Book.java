package booksystem.bean;

/**
 * 书籍类（Bean）
 * 属性 bookid bookname bookauthor booktheme bookstatus
 */
public class Book {
    private int bookid;
    private String bookname;
    private String bookauthor;
    private String booktheme;
    private int bookstatus;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public String getBooktheme() {
        return booktheme;
    }

    public void setBooktheme(String booktheme) {
        this.booktheme = booktheme;
    }

    public int getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(int bookstatus) {
        this.bookstatus = bookstatus;
    }
}
