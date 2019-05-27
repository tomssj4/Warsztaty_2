package Prog;

import Classes.Exercise;
import Dao.ExerciseDao;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog2 {
    public static void main(String[] args) {
        try {
            Scanner scann = new Scanner(System.in);
            ExerciseDao exerciseDao = new ExerciseDao();
            do {
                exerciseDao.findAllExercises();
                String add = "add";
                String edit = "edit";
                String delete = "delete";
                String quit = "quit";
                System.out.println(String.format("Wybierz jedna z opcji:\n   > %s - dodanie zadania\n   > %s - edcyja zadania\n   " +
                        "> %s - usuniecie zadania\n   > %s - zakonczenie programu", add, edit, delete, quit));
                String scan = scann.next();
                if (scan.equals(add)) {
                    addingExercise();

                } else if (scan.equals(edit)) {
                    edditingExercise();

                } else if (scan.equals(delete)) {
                    deletingExercise();

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

    private static void addingExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.print("Podaj tytul: ");
        String title = nextScanString();
        System.out.print("Podaj opis: ");
        String description = nextScanString();
        Exercise exercise = new Exercise(title, description);
        exerciseDao.create(exercise);
        System.out.println("Zadanie zostalo dodane.");

    }

    private static void edditingExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        try {
            System.out.print("Podaj id uzytkownika, ktorego dane chcesz zmienic: ");
            int id = nextScanInt();
            System.out.print("Podaj tytul: ");
            String title = nextScanString();
            System.out.print("Podaj opis: ");
            String description = nextScanString();
            Exercise exercise = new Exercise(id, title, description);
            exercise.setTitle(title);
            exercise.setDescription(description);
            exerciseDao.update(exercise);
            System.out.println("Dane zadania zostaly zmienione.");
        } catch (InputMismatchException a) {
            System.out.println("Podaj liczbe!");
        }
    }

    private static void deletingExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        try {
            System.out.print("Podaj id zadania, ktore chcesz usunac: ");
            int id = nextScanInt();
            exerciseDao.delete(id);
            System.out.println("Zadanie zostalo usuniete.");
        } catch (InputMismatchException b) {
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
