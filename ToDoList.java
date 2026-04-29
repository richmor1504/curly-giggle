import java.util.*;
import java.io.*;

public class ToDoList {

    static ArrayList<String> list = new ArrayList<String>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        loadFile();

        int choice = 0;

        while (choice != 4) {

            System.out.println("\nTo-Do List Menu");
            System.out.println("1. Show Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Quit");
            System.out.print("Choice: ");

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {

                if (list.size() == 0) {
                    System.out.println("No tasks yet.");
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ": " + list.get(i));
                    }
                }

            } else if (choice == 2) {

                System.out.print("Enter task: ");
                String t = input.nextLine();
                list.add(t);

            } else if (choice == 3) {

                System.out.print("Enter task number: ");
                int num = input.nextInt();

                if (num > 0 && num <= list.size()) {
                    list.remove(num - 1);
                    System.out.println("Task removed.");
                } else {
                    System.out.println("Invalid number.");
                }
            }
        }

        saveFile();

        System.out.println("Done.");
    }

    public static void loadFile() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("tasks.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("File not found, starting new list.");
        }
    }

    public static void saveFile() {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"));

            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i));
                bw.newLine();
            }

            bw.close();

        } catch (Exception e) {
            System.out.println("Error writing file.");
        }
    }
}
