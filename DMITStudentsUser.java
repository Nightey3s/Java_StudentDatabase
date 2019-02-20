/*Write a Java class DMITStudentsUser (or similar class) that contains the main() method for the entire application. 
The main() method is where your program will start to run. 
It calls the methods in DMITStudents class to read user inputs, display student records, search, add or delete students …etc.*/
package progassignment;

public class DMITStudentsUser {

    public static void main(String[] args) throws Exception {
        DMITStudents runMain = new DMITStudents();
        runMain.initialiseArrays();//initialise arrays
        runMain.selection();//run the program
    }//end of main
}//end of class