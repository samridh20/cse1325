package mdi;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    public void addMenuItem(MenuItem item) {
        menuItems.add(item);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItems.size(); i++) {
            sb.append(i + 1).append(") ").append(menuItems.get(i).getDescription()).append("\n");
        }
        return sb.toString();
    }

    public void run(int selection) {
        if (selection > 0 && selection <= menuItems.size()) {
            menuItems.get(selection - 1).getAction().run();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    public static int getInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        return sc.nextInt();
    }
}