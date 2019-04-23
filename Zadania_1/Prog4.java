import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog4 {
    public static void main(String[] args) {
        try {
            Scanner scann = new Scanner(System.in);
            SolutionDao solutionDao = new SolutionDao();
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
                    System.out.print("Podaj id uzytkownikow: ");
                    int userId = scann.nextInt();
                    exerciseDao.findAllExercises();
                    System.out.print("Podaj id zadania: ");
                    int exerciseId = scann.nextInt();
                    addingSolution(userId, exerciseId);

                } else if (scan.equals(view)) {
                    edditingGroup();

                } else if (scan.equals("quit")){
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

    private static void addingSolution(int userId, int exerciseId){
        SolutionDao solutionDao = new SolutionDao();

        System.out.print("Podaj nazwe: ");
        String name = nextScanString();
        Group group = new Group(name);
        groupDao.create(group);
        System.out.println("Grupa zostala dodana.");

    }

    private static void edditingGroup(){
        GroupDao groupDao = new GroupDao();
        try {
            System.out.print("Podaj id uzytkownika, ktorego dane chcesz zmienic: ");
            int id = nextScanInt();
            System.out.print("Podaj nazwe: ");
            String name = nextScanString();
            Group group = new Group(id, name);
            group.setName(name);
            groupDao.update(group);
            System.out.println("Dane grupy zostaly zmienione.");
        } catch (InputMismatchException a) {
            System.out.println("Podaj liczbe!");
        }
    }

    private static String nextScanString (){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();

    }
    private static int nextScanInt (){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    private static int[] userIdList (){

    }
}
