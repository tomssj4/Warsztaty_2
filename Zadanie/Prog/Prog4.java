package Prog;

import Classes.Solution;
import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog4 {
    public static void main(String[] args) {
        try {
            Scanner scann = new Scanner(System.in);
            UserDao userDao = new UserDao();
            ExerciseDao exerciseDao = new ExerciseDao();
            do {
                String add = "add";
                String view = "view";
                String quit = "quit";
                System.out.println(String.format("Wybierz jedna z opcji:\n   > %s - przypisywanie zadan" +
                        " do uzytkownikow\n   > %s - przegladanie rozwiazan danego uytkownika\n   " +
                        "> %s - zakonczenie programu", add, view, quit));
                String scan = scann.next();
                if (scan.equals(add)) {
                    userDao.findAllUsers();
                    int[] listOfId = usersIdList();
                    exerciseDao.findAllExercises();
                    System.out.print("Podaj id zadania: ");
                    int exerciseId = scann.nextInt();
                    addingSolution(listOfId, exerciseId);

                } else if (scan.equals(view)) {
                    viewSolutions();

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

    private static int[] usersIdList() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj id uzytkownikow (oddzielajac przecinkiem): ");
        String text = scanner.nextLine();
        String[] tab = null;
        tab = text.split(",");
        int[] idList = new int[tab.length];
        for (int i = 0; i < tab.length; i++) {
            idList[i] = Integer.parseInt(tab[i]);
        }
        return idList;
    }

    private static void addingSolution(int[] idList, int exerciseId) {
        SolutionDao solutionDao = new SolutionDao();
        Date date = new Date();
        String dateOutput = String.format("%tF %<tT", date);
        for (int id : idList) {
            Solution solution = new Solution(dateOutput, null, null, exerciseId, id);
            solutionDao.create(solution);
        }
        System.out.println("Obiekt/y zostal/y utworzony/e.");

    }

    private static void viewSolutions() {
        SolutionDao solutionDao = new SolutionDao();
        try {
            System.out.print("Podaj id uzytkownika, ktorego rozwiazania chcesz zobaczyc: ");
            int id = nextScanInt();
            solutionDao.findAllByUserId(id);
        } catch (InputMismatchException a) {
            System.out.println("Podaj liczbe!");
        }
    }

    private static String nextScanString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }

    private static int nextScanInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
