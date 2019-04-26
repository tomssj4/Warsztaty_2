import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProgUser {
    public static void main(String[] args) {
        int idParameter = 2;
        try {
            Scanner scann = new Scanner(System.in);
            ExerciseDao exerciseDao = new ExerciseDao();
            do {
                String add = "add";
                String view = "view";
                System.out.println(String.format("Wybierz jedna z opcji:\n   > %s - dodawanie rozwiazan" +
                        " do uzytkownikow\n   > %s - przegladanie swoich rozwiazan", add, view));
                String scan = scann.next();
                if (scan.equals(add)) {
                    exerciseDao.findAllExercisesWhereNoSolution(idParameter);
                    System.out.print("Do ktorego zadania ma zostac dodane rozwiazanie? Wpisz ID zadania: ");
                    int exercieseId = nextScanInt();
                    if (exerciseDao.findAllExercisesIdWhereNoSolution(idParameter).contains(exercieseId)){
                        System.out.print("Napisz rozwiazanie zadania: ");
                        String exerciseDescription = nextScanString();
                        addingSolution(idParameter, exercieseId, exerciseDescription);
                    } else {
                        System.out.println("Rozwiazanie do tego zadania juz istnieje!");
                    }

                } else if (scan.equals(view)) {

                } else if (scan.equals("quit")) {
                    break;
                } else {
                    System.out.println("Wpisz poprawne polecenie!");
                }
            } while (true);
            System.out.println("Koniec programu.");
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }

    private static void addingSolution(int userId, int exerciseId, String description) {
        SolutionDao solutionDao = new SolutionDao();
        Date date = new Date();
        String dateOutput = String.format("%tF %<tT", date);
        Solution solution = new Solution(dateOutput, null, description, exerciseId, userId);
        solutionDao.create(solution);
        System.out.println("Rozwiazanie zostalo utworzone.");
}

private static void listOfExercisesIdWhereNoSolution (int userId){
    ExerciseDao exerciseDao = new ExerciseDao();
    exerciseDao.findAllExercisesWhereNoSolution(userId);
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
