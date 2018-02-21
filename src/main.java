import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        game ng = new game(); //initialize game class
        ng.chooseMovie();
        ng.printName();
        ng.dashed();
        while (true) {
            System.out.println("Guess a letter: ");
            Scanner cc = new Scanner(System.in);
            char usr = cc.next().toLowerCase().charAt(0);
            ng.check(usr);
            ng.paraCheck();
        }
    }
}