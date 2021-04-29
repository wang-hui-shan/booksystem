package booksystem.ui;

public class mainTest implements Runnable{
    @Override
    public void run() {
        Login login = new Login();
        login.showUI();
    }
}
