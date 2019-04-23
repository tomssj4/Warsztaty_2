import java.util.InputMismatchException;
import java.util.Scanner;

public class Prog3 {
    public static void main(String[] args) {
        try {
            Scanner scann = new Scanner(System.in);
            GroupDao groupDao = new GroupDao();
            do {
                groupDao.findAllExercises();
                String add = "add";
                String edit = "edit";
                String delete = "delete";
                String quit = "quit";
                System.out.println(String.format("Wybierz jedna z opcji:\n   > %s - dodanie zadania\n   > %s - edcyja zadania\n   " +
                        "> %s - usuniecie zadania\n   > %s - zakonczenie programu", add, edit, delete, quit));
                String scan = scann.next();
                if (scan.equals(add)) {
                    addingGroup();

                } else if (scan.equals(edit)) {
                    edditingGroup();

                } else if (scan.equals(delete)) {
                    deletingGroup();

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

    private static void addingGroup(){
        GroupDao groupDao = new GroupDao();
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

    private static void deletingGroup(){
        GroupDao groupDao = new GroupDao();
        try {
            System.out.print("Podaj id grupy, ktora chcesz usunac: ");
            int id = nextScanInt();
            groupDao.delete(id);
            System.out.println("Grupa zostala usunieta.");
        } catch (InputMismatchException b) {
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
}
