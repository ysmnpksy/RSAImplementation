import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<User> userList = new ArrayList<>();

    public static void main(String[] args){
        loadFile();
        menu();
    }

    public static void loadFile(){
        File file = new File("Users.txt");
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            userList = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ignored){}

    }

    public static void saveFile(){
        try{
            FileOutputStream fos = new FileOutputStream("Users.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(userList);
            oos.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void menu(){
        Scanner in = new Scanner(System.in);
        int n;
        System.out.println("MENU");
        System.out.println("----");
        do{
            //printing all options
            System.out.println("""
                    Select an item between 1 and 4 and press enter
                    1. Create User
                    2. Encrypt Message
                    3. Decrypt Message
                    4. Quit"""
            );

            //getting user input
            n = in.nextInt();

            //switch
            switch(n){
                case 1 -> createUser();
                case 2 -> encryptMenu();
                case 3 -> decryptMenu();
            }
        }
        while(n!=4);
    }

    public static void createUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the key holder:");
        String name = in.nextLine();
        userList.add(new User(name));

        saveFile();
    }

    public static void encryptMenu(){
        boolean found = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the message recipient:");
        String name = in.nextLine();

        for (User user : userList){
            if (user.getUsername().equals(name)){
                found = true;
                System.out.println("Enter the message to be encrypted:");
                String message = in.nextLine();
                BigInteger c = Encrypt.encryptMessage(message, user.getKeys().getN(), user.getKeys().getE());
                System.out.println("The encrypted message is: ");
                System.out.println(c);
                break;
            }
        }
        if (!found){
            System.out.println("User not found. Try again.");
        }
    }

    public static void decryptMenu(){
        boolean found = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = in.nextLine();

        for (User user : userList){
            if (user.getUsername().equals(name)){
                found = true;
                System.out.println("Enter the message to be decrypted:");
                String message = in.nextLine();
                String m = Decrypt.decryptMessage(message, user.getKeys().getD(), user.getKeys().getN());
                System.out.println("The decrypted message is: ");
                System.out.println(m);
                break;
            }
        }
        if (!found){
            System.out.println("User not found. Try again.");
        }
    }
}