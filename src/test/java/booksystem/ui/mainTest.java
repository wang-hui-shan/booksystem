package booksystem.ui;

class mainTest implements Runnable{
    @Override
    public void run() {
        Login l = new Login();
        l.showUI();
    }
}