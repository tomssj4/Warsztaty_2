package Prog;

import Classes.User;
import Dao.UserDao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog1 {
    public static void main(String[] args) {
        try {
            Scanner scann = new Scanner(System.in);
            UserDao userDao = new UserDao();
            do {
                userDao.findAllUsers();
                String add = "add";
                String edit = "edit";
                String delete = "delete";
                String quit = "quit";
                System.out.println(String.format("Wybierz jedna z opcji:\n   > %s - dodanie uzytkownika\n   > %s - edcyja uzytkownika\n   " +
                        "> %s - usuniecie uzytkownika\n   > %s - zakonczenie programu", add, edit, delete, quit));
                String scan = scann.next();
                if (scan.equals(add)) {
                    addingUser();

                } else if (scan.equals(edit)) {
                    edditingUser();

                } else if (scan.equals(delete)) {
                    deletingUser();

                } else if (scan.equals("quit")) {
                    break;
                } else {
                    System.out.println("Wpisz poprawne polecenie!");
                }
                scann.close();
            } while (true);
            System.out.println("Koniec programu.");
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }


    }

    private static void addingUser() {
        UserDao userDao = new UserDao();
        System.out.println("Podaj ID grupy uzytownika: ");
        int groupId = nextScanInt();
        System.out.print("Podaj username: ");
        String username = nextScanString();
        System.out.print("Podaj email: ");
        String email = nextScanString();
        System.out.print("Podaj haslo: ");
        String password = nextScanString();
        User user = new User(groupId, username, email, password);
        userDao.create(user);
        System.out.println("Uzytkownik zostal dodany.");

    }

    private static void edditingUser() {
        UserDao userDao = new UserDao();
        try {
            System.out.print("Podaj id uzytkownika, ktorego dane chcesz zmienic: ");
            User user = userDao.read(nextScanInt());
            System.out.println("Podaj nowe ID grupy uzytownika: ");
            user.setGroup_id(nextScanInt());
            System.out.print("Podaj nowy username: ");
            user.setUserName(nextScanString());
            System.out.print("Podaj nowy email: ");
            user.setEmail(nextScanString());
            System.out.print("Podaj nowe haslo: ");
            user.setPassword(nextScanString());
            userDao.update(user);
            System.out.println("Dane uzytkownika zostaly zmienione.");
        } catch (InputMismatchException a) {
            System.out.println("Podaj liczbe!");
        }
    }

    private static void deletingUser() {
        UserDao userDao = new UserDao();
        try {
            System.out.print("Podaj id uzytkownika, ktorego chcesz usunac: ");
            int id = nextScanInt();
            userDao.delete(id);
            System.out.println("Uzytkownik zostal usuniety.");
        } catch (InputMismatchException b) {
            System.out.println("Podaj liczbe!");
        }
    }

    private static String nextScanString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();

    }

    private static int nextScanInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
