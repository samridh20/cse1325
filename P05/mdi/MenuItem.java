package mdi;

public class MenuItem{
    private String menuText;
    private Runnable menuResponse;

    public MenuItem(String menuText, Runnable menuResponse){
        this.menuText = menuText;
        this.menuResponse = menuResponse;
    }

    @Override
    public String toString(){
        return menuText;
    }

    public void run(){
        menuResponse.run();
    }
}