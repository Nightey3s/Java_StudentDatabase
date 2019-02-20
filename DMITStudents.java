/*Write a Java class DMITStudents (or similar class) to represent the actions of the mini DMIT student record system. 
The class should have methods that will be invoked by the main method such as to read and validate user inputs, display student records, search, add or delete students, ….
, etc. In this class, when the program is started, you may create 3 Student objects and store in an array. (You are not allowed to use ArrayList).

Additional Features:
Store as file(database)
Validate for non-numeric input
Sorting of arrays
Add display admin number
implement photo display
edit student info 
search by course
Checking for duplicates
self expanding array
tabular format */
package progassignment;

import java.io.*; //includes io.BufferedWriter & io.FileWriter
import java.net.URL;
import javax.swing.*;//inclues .ImageIcon, .JOptionPane, .JTextArea
import javax.swing.table.DefaultTableModel;

/*Start of DMITStudents*/
public class DMITStudents {

    Student[] studentList = new Student[4];
    private String option, choice, searchChoice, searchName, searchAdNum, addName, edit;
    private boolean isDelete;

    /*Method to initialise arrays*/
    public void initialiseArrays() {
        for (int i = 0; i < studentList.length; i++) {
            studentList[i] = new Student();
        }//add an empty student object into the array

        studentList[0] = new Student("Grace Teo", "DIT", "91111111", 'F', "P1811111");
        studentList[1] = new Student("Kenny Tan", "DIT", "92222222", 'M', "P1822222");
        studentList[2] = new Student("Peter Low", "DIT", "93333333", 'M', "P1833333");
    }//end of initialiseArrays

    /*Method to expand an array*/
    public void expandArray() {//expands the array automatically w/o using arraylist
        int arraylength = studentList.length;
        Student[] newStudentList = new Student[arraylength + 1];
        for (int i = 0; i < studentList.length; i++) {
            newStudentList[i] = studentList[i];
        }
        newStudentList[arraylength] = new Student();
        studentList = newStudentList;
    }//end of expandArray

    /*Method to shrink array*/
    public void shrinkArray() {
        int arraylength = studentList.length;
        Student[] newStudentList = new Student[arraylength - 1];
        for (int i = 0; i < newStudentList.length; i++) {
            newStudentList[i] = studentList[i];
        }
        studentList = newStudentList;
    }//end of shrinkArray

    /*Method to print end message*/
    public void programTerminated() throws Exception {
        ImageIcon endofProgram = new ImageIcon(new URL("https://i.imgur.com/aLg1J54.png"));
        JOptionPane.showMessageDialog(null, "Program Terminated! \nThank you!", "End of program", JOptionPane.INFORMATION_MESSAGE, endofProgram);
    }//end of programTerminated

    /*Method to tell users to select an option if they click cancel*/
    public void plsenterOption() {
        JOptionPane.showMessageDialog(null, "Please select an option.");
    }//end of plsenterOption

    /*Method to tell users to enter a value*/
    public void plsenterValue() {
        JOptionPane.showMessageDialog(null, "Please enter a value.");
    }//end of plsenterValue

    /*Method for user option*/
    public void selection() throws Exception {
        do {

            do {
                option = JOptionPane.showInputDialog(null, "Enter your choice: \n\n1.Display database \n2.Search Database \n3.Delete \n4.Add to database \n5.Edit student info\n\nTo exit, type 'Exit'", "Selection", JOptionPane.INFORMATION_MESSAGE);
                if (option == null || (option != null && ("".equals(option)))) {
                    plsenterOption();
                }

            } while (option == null || (option != null && ("".equals(option))));
            switch (option) {
                case "1":
                    displayStudent();
                    break;
                case "2":
                    searchStudent();
                    break;
                case "3":
                    deleteStudent();
                    break;
                case "4":
                    addStudent();
                    break;
                case "5":
                    editStudent();
                    break;
                case "Exit":
                    programTerminated();
                    break;
                case "exit":
                    programTerminated();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "The option you have chosen is invalid", "Invalid Option", JOptionPane.ERROR_MESSAGE);
            }

        } while (!option.equalsIgnoreCase("Exit"));
    }//end of selection

    /*Method to display database in a table*/
    public void displayStudent() throws Exception {
        do {
            choice = JOptionPane.showInputDialog(null, "How would you like to display the datbase? \n1.Entire Database \n2.By Course", "Display Students", JOptionPane.QUESTION_MESSAGE);
            if (choice == null || (choice != null && ("".equals(choice)))) {
                plsenterOption();
            }
        } while (choice == null || (choice != null && ("".equals(choice))));

        String courseChoice;
        int num = 0;
        Object[][] rowData = {};
        Object[] columnNames = {"SN", "Name", "Admin No.", "Course", "Gender", "Mobile No."};

        DefaultTableModel listTableModel;
        listTableModel = new DefaultTableModel(rowData, columnNames);

        switch (choice) {
            case "1":
                for (int i = 0; i < studentList.length; i++) {
                    if (studentList[i].getName() != null) {
                        num++;
                        String rowString1 = num + "";
                        String rowString2 = studentList[i].getName();
                        String rowString3 = studentList[i].getAdminNumber();
                        String rowString4 = studentList[i].getCourse();
                        char rowString5 = studentList[i].getGender();
                        String rowString6 = studentList[i].getMobile();
                        listTableModel.addRow(new Object[]{rowString1, rowString2, rowString3, rowString4, rowString5, rowString6});

                    } else;
                }//for             
                break;
            case "2":
                courseChoice = JOptionPane.showInputDialog(null, "Enter the course you would like to display by", "Course Selection", JOptionPane.QUESTION_MESSAGE);
                for (int i = 0; i < studentList.length; i++) {
                    if (studentList[i].getName() != null && studentList[i].getCourse().equalsIgnoreCase(courseChoice)) {
                        num++;
                        String rowString1 = num + "";
                        String rowString2 = studentList[i].getName();
                        String rowString3 = studentList[i].getAdminNumber();
                        String rowString4 = studentList[i].getCourse();
                        char rowString5 = studentList[i].getGender();
                        String rowString6 = studentList[i].getMobile();
                        listTableModel.addRow(new Object[]{rowString1, rowString2, rowString3, rowString4, rowString5, rowString6});
                    } else;
                }//for
        }
        //Create and store display in a table
        JTable listTable;
        listTable = new JTable(listTableModel);
        listTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        listTable.setCellEditor(null);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(listTable));
        frame.setVisible(true);
        frame.pack();
        frame.setSize(700, 200);

        //Store database in a text file 
        BufferedWriter bfw = new BufferedWriter(new FileWriter("DMIT Students Database.txt"));
        for (int i = 0; i < listTable.getColumnCount(); i++) {
            bfw.write(listTable.getColumnName(i));
            bfw.write("\t");
        }

        for (int i = 0; i < listTable.getRowCount(); i++) {
            bfw.newLine();
            for (int j = 0; j < listTable.getColumnCount(); j++) {
                bfw.write((String) (String.valueOf(listTable.getValueAt(i, j))));
                bfw.write("\t");;
            }
        }
        bfw.close();
    }//end of displayStudent

    /*Method to search for student*/
    public void searchStudent() throws Exception {
        do {
            searchChoice = JOptionPane.showInputDialog(null, "How would you like to search for the student?\n 1.By Name \n2.By Admin Number \n\n Type '0' to exit");
            if (searchChoice == null || (searchChoice != null && ("".equals(searchChoice)))) {
                plsenterOption();
            }
        } while (searchChoice == null || (searchChoice != null && ("".equals(searchChoice))));
        String printSearch = "";
        boolean searchFound = false;
        ImageIcon StudentPicture = new ImageIcon(new URL("https://i.imgur.com/2NhbLKc.png"));//uses online icon
        switch (searchChoice) {
            case "1":
                do {
                    searchName = JOptionPane.showInputDialog(null, "Enter the name of sutdent you wish to search for \n\nTo exit type '0'", "Search Student", JOptionPane.QUESTION_MESSAGE);
                    if (searchName == null || (searchName != null && ("".equals(searchName)))) {
                        plsenterValue();
                    }
                } while (searchName == null || (searchName != null && ("".equals(searchName))));
                if (!"0".equals(searchName)) {
                    for (int i = 0; i < studentList.length; i++) {
                        if (studentList[i].getName() != null && studentList[i].getName().equalsIgnoreCase(searchName)) {
                            printSearch += "Name:\t" + studentList[i].getName()
                                    + "\nAdminNumber:\t" + studentList[i].getAdminNumber()
                                    + "\nCourse:\t" + studentList[i].getCourse()
                                    + "\nGender:\t" + studentList[i].getGender()
                                    + "\nMobile:\t" + studentList[i].getMobile();
                            searchFound = true;
                        } else;
                    }
                    if (searchFound == false && !"0".equals(searchName)) {
                        JOptionPane.showMessageDialog(null, "The student name: " + searchName + " cannot be found", "Name not found in database", JOptionPane.ERROR_MESSAGE);
                    }
                }

                break;
            case "2":
                do {
                    searchAdNum = JOptionPane.showInputDialog(null, "Enter the admin number you wish to search for \n\nTo exit type '0'");
                    if (searchAdNum == null || (searchAdNum != null && ("".equals(searchAdNum)))) {
                        plsenterValue();
                    }
                } while (searchAdNum == null || (searchAdNum != null && ("".equals(searchAdNum))));
                if (!"0".equals(searchAdNum)) {
                    for (int i = 0; i < studentList.length; i++) {
                        if (studentList[i].getName() != null && studentList[i].getAdminNumber().equalsIgnoreCase(searchAdNum)) {
                            printSearch += "Name:\t" + studentList[i].getName()
                                    + "\nAdminNumber:\t" + studentList[i].getAdminNumber()
                                    + "\nCourse:\t" + studentList[i].getCourse()
                                    + "\nGender:\t" + studentList[i].getGender()
                                    + "\nMobile:\t" + studentList[i].getMobile();
                            searchFound = true;
                        } else;
                    }
                    if (searchFound == false && !"0".equals(searchAdNum)) {
                        JOptionPane.showMessageDialog(null, "The admin number: " + searchAdNum + " cannot be found", "Admin Number not found in database", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case "0":
                break;
            default:
                JOptionPane.showMessageDialog(null, "The option you have chosen is invalid", "Invalid Option", JOptionPane.ERROR_MESSAGE);

        }

        if (searchFound == true) {
            JOptionPane.showMessageDialog(null, new JTextArea(printSearch), "Student Details", JOptionPane.INFORMATION_MESSAGE, StudentPicture);
        }
    }//end of searchStudent

    /*Method to delete a student from the database*/
    public void deleteStudent() throws Exception {
        do {
            searchName = JOptionPane.showInputDialog(null, "Enter the name of student you wish to delete \n\nType '0' to exit", "Delete Student", JOptionPane.QUESTION_MESSAGE);
            if (searchName == null || (searchName != null && ("".equals(searchName)))) {
                plsenterOption();
            }
        } while (searchName == null || (searchName != null && "".equals(searchName)));

        switch (searchName) {
            case "0":
                break;
            default:
                isDelete = false;
                ImageIcon deleted = new ImageIcon(new URL("https://image.ibb.co/e04mwo/627249_delete3_512.png"));

                for (int i = 0; i < studentList.length; i++) {
                    if (studentList[i].getName() != null && studentList[i].getName().equalsIgnoreCase(searchName)) {

                        studentList[i] = new Student();
                        isDelete = true;
                    } else;
                }//for
                for (int i = 0; i < studentList.length - 1; i++) {
                    if (studentList[i].getName() == null) {
                        studentList[i] = studentList[i + 1];
                        studentList[i + 1] = new Student();
                    } else;
                }//for2 [move the students up in the array]
                if (isDelete) {
                    JOptionPane.showMessageDialog(null, "The student: " + searchName + " has been deleted", "Student Deleted", JOptionPane.INFORMATION_MESSAGE, deleted);
                    shrinkArray();
                }
                if (isDelete == false) {
                    JOptionPane.showMessageDialog(null, "The student: " + searchName + " cannot be found!", "Name not found", JOptionPane.ERROR_MESSAGE);
                }

        }

    }//end of deleteStudent

    /*Method to add a student to the database*/
    public void addStudent() throws Exception {
        expandArray();
        boolean duplicate = false;
        do {
            addName = JOptionPane.showInputDialog(null, "Enter student's name to add \n\n Type '0' to exit", "Add Student", JOptionPane.QUESTION_MESSAGE);
            if (!"0".equals(addName)) {
                char[] charNames = addName.toCharArray();
                for (int i = 0; i < charNames.length; i++) {
                    charNames[i] = Character.toLowerCase(charNames[i]);
                }
                //Make first char a cap
                charNames[0] = Character.toUpperCase(charNames[0]);

                //Capitalize each letter after a space
                for (int j = 1; j < charNames.length; j++) {
                    if (charNames[j - 1] == ' ') {
                        charNames[j] = Character.toUpperCase(charNames[j]);
                    }
                }
                String capsAddName = new String(charNames);

                duplicate = false;
                int counter = 0;
                for (int i = 0; studentList[i].getName() != null; i++) {
                    counter++;
                }
                for (int i = 0; i < studentList.length; i++) {
                    if (addName.equalsIgnoreCase(studentList[i].getName())) {
                        duplicate = true;
                    } else;
                }
                int i = counter;

                String AN, C, M, gen;
                if (duplicate == false) {
                    do {
                        AN = JOptionPane.showInputDialog(null, "Enter student's admin number").toUpperCase();
                        if (AN.length() != 8) {
                            JOptionPane.showMessageDialog(null, "Admin Number should start with a letter, followed by 7 numbers.", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (AN.length() != 8);

                    C = JOptionPane.showInputDialog(null, "Enter the student's course").toUpperCase();
                    do {
                        M = JOptionPane.showInputDialog(null, "Enter the student's number");
                        if (M.length() != 8) {
                            JOptionPane.showMessageDialog(null, "Mobile Number should be an 8 digit number.", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (M.length() != 8);
                    gen = JOptionPane.showInputDialog(null, "Enter the student's gender").toUpperCase();
                    char genChar = gen.charAt(0);

                    studentList[i] = new Student(capsAddName, C, M, genChar, AN);
                    JOptionPane.showMessageDialog(null, "The student: " + capsAddName + " has been successfully added to the database.", capsAddName + " added to the database", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (duplicate == true) {
                JOptionPane.showMessageDialog(null, "The student: " + addName + " is already in the database", "Duplicate Entry", JOptionPane.WARNING_MESSAGE);
            }
        } while ((duplicate == true));
    }//end of addStudent

    /*Edit a student's particulars*/
    public void editStudent() throws Exception {
        ImageIcon edited = new ImageIcon(new URL("https://i.imgur.com/0RuQCuT.png"));
        do {
            edit = JOptionPane.showInputDialog(null, "Enter the student's name to edit info \n\nType '0' to exit", "Edit Student", JOptionPane.QUESTION_MESSAGE);
            if (edit == null && (edit != null || "".equals(edit))) {
                plsenterValue();
            }
        } while (edit == null && (edit != null || "".equals(edit)));

        String editChoice = "";
        if (!"0".equals(edit)) {
            for (int i = 0; i < studentList.length; i++) {
                if (edit.equalsIgnoreCase(studentList[i].getName())) {
                    editChoice = JOptionPane.showInputDialog(null, "Enter the info you want to edit of: " + edit + "\n1.Name\n2.Admin Number\n3.Course\n4.Gender\n5.Mobile No.\n\nType 0 to exit", "Info Edit", JOptionPane.QUESTION_MESSAGE);

                    String changeName = "";
                    String changeAdminNum = "";
                    String changeCourse = "";
                    String changeGender = "";
                    String changeMobileNum = "";

                    switch (editChoice) {
                        case "1":
                            changeName = JOptionPane.showInputDialog(null, "Enter new name for student " + edit);
                            char[] charNames = changeName.toCharArray();
                            for (i = 0; i < charNames.length; i++) {
                                charNames[i] = Character.toLowerCase(charNames[i]);
                            }
                            //Make first char a cap
                            charNames[0] = Character.toUpperCase(charNames[0]);

                            //Capitalize each letter after a space
                            for (int j = 1; j < charNames.length; j++) {
                                if (charNames[j - 1] == ' ') {
                                    charNames[j] = Character.toUpperCase(charNames[j]);
                                }
                            }
                            String capsAddName = new String(charNames);
                            for (i = 0; i < studentList.length; i++) {
                                if (studentList[i].getName() != null && studentList[i].getName().equalsIgnoreCase(edit)) {
                                    studentList[i].setName(capsAddName);
                                } else;
                            }
                            break;
                        case "2":
                            do {
                                changeAdminNum = JOptionPane.showInputDialog(null, "Enter new admin nuimber for student: " + edit).toUpperCase();
                                if (changeAdminNum.length() != 8) {
                                    JOptionPane.showMessageDialog(null, "Mobile Number should be an 8 digit number.", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
                                }
                            } while (changeAdminNum.length() != 8);
                            studentList[i].setAdminNumber(changeAdminNum);
                            break;
                        case "3":
                            changeCourse = JOptionPane.showInputDialog(null, "Enter new course for student: " + edit).toUpperCase();
                            studentList[i].setCourse(changeCourse);
                            break;
                        case "4":
                            changeGender = JOptionPane.showInputDialog(null, "Enter new gender for student: " + edit).toUpperCase();
                            char changeG = changeGender.charAt(0);
                            studentList[i].setGender(changeG);
                            break;
                        case "5":
                            do {
                                changeMobileNum = JOptionPane.showInputDialog(null, "Enter new mobile nuimber for student: " + edit);
                                if (changeMobileNum.length() != 8) {
                                    JOptionPane.showMessageDialog(null, "Mobile Number should be an 8 digit number.", "Incorrect Input", JOptionPane.WARNING_MESSAGE);
                                }
                            } while (changeMobileNum.length() != 8);
                            studentList[i].setMobile(changeMobileNum);
                            break;
                        case "0": //to exit
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "The option you have chosen is not valid");
                    }
                    JOptionPane.showMessageDialog(null, "The student's detail(s) have been changed", "Info edited", JOptionPane.INFORMATION_MESSAGE, edited);
                }
            }//for
        }
    }//end of editStudent

}//end of class
