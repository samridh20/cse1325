package mdi;

public class MenuItem {
    private String description;
    private Runnable action;

    public MenuItem(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getAction() {
        return action;
    }
}