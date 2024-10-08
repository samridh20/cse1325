package mdi;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu{
    private ArrayList<MenuItem> items = new ArrayList<>();

    public void addMenuItem(MenuItem item){
        items.add(item);
    }

    public void run(int itemNumber){
        if (itemNumber == 0) {
            System.out.println("Exiting...");
            items.get(itemNumber).run();
        } else if (itemNumber > 0 && itemNumber <= items.size()){
            items.get(itemNumber).run();
        } else {
            System.out.println("Invalid selection.");
        }
    }

    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder(); 
        for (int i = 0; i < items.size(); i++){
            sb.append(i).append(") ").append(items.get(i)).append("\n");  // Number the other menu items
        }
        return sb.toString();
    }

    public static int getInt(String prompt){
        System.out.print(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String getString(String prompt){
        System.out.print(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}