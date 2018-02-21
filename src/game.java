import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class game{
    game(){
        System.out.println("You will have to guess the name of the movie");
        System.out.println("You get 10 chances to guess it correctly," +
                "one alphabet at a time");
    }
    private int i;
    private String dash; // stores dashed representation of movie name
    private String nameComp; // stores a copy of the original movie name
    private String name; // store the name of the movie
    private String[] names = new String[25];// stores the names of the movies read from the txt file
    private String correct = ""; // stores all the letters entered by the user
    private String wrong = ""; // stores the wrong guesses made by the user

    private int wrongIndex = 0; // keeps track of all the wrong attempts



    /*
        function to determine if the user has won game or not
     */


    public boolean gameStatus(){
        if(nameComp.equals(dash))
            return true;
        else return false;
    }


    /*
         returns the name of the string with spaces removed
     */

    public String printName(){
         i = (int) (Math.random() * 25);
         name = names[i];
         nameComp = names[i];
         return name;
    }


    /*
        Reads the names of the movies from the text file.
     */

    public void chooseMovie() {
        File fl = new File("movies.txt"); // reads text file with the help of File class
        try {
            Scanner sc = new Scanner(fl); // initialize the scanner class to read the file

            while (sc.hasNextLine()) {
                /*
                    store the string from the txt file in "string line"
                    one line at a time.
                 */
                String line = sc.nextLine();
                line= line.replace(" ", "");
                line=line.replace("'", "");
                names[i] = line;
                i++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("File does not exist");
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Array out of bound");
        }
    }


    /*
        changes the name of the movie from the alpha numerical to dashed
     */

    public void dashed(){

        dash = name.replaceAll("[a-zA-Z]", "_");
        System.out.println("You are guessing " + dash);
    }


    public void check(char user){

        String ipchk = "" + user;

         /*
            checks if the user input valid chars
          */

        if(ipchk.matches("[^A-Za-z0-9]")){
            System.out.println("Enter valid letters and numbers only");
        }

        if((!(!(nameComp.indexOf(user) == -1))) && (!(!(correct.indexOf(user) == -1)))){
            wrongIndex++;
            wrong = wrong+ " "+ user;
        }

       correct = correct +" " +user;

        if (!(name.indexOf(user) == -1)) {

            String cc = "" + user;

            for(int s = 0; s<name.length(); s++){
                char c = name.charAt(s);
                int index = name.indexOf(cc);
                if(c == user) {

                    String xx = "" + c;
                    char dashes[] = dash.toCharArray();
                    dashes[index] = c;
                    dash = String.valueOf(dashes);
                    name = name.replaceFirst(xx, ".");
                }
            }
            name = name.replaceAll(cc, "_");
        }

        System.out.println("You are guessing " + dash);
        System.out.println(("You have guessed "+ wrongIndex + " wrong letters"));
        System.out.println("Wrong Letters "+wrong);

    }

    /*
        keeps on checking if game is won or lost
     */

    public void paraCheck(){

        if(wrongIndex >= 10 ){
            System.out.println("10 wrong guesses. You lost.");
            System.exit(0);
        }


        if (gameStatus()) {
            System.out.println("You win!!");
            System.exit(0);
        }
    }

}
