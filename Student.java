/* Write a Java class Student (or similar class) to represent a student object:
 * String name represents the name of a student 
 * String course represents the course that the student studies
 * String mobile  represents the contact number of the student 
 * char gender represents the gender of the student
 * add any other properties to meet your requirements. Add a constructor and appropriate get or set methods to the class if necessary.*/
package progassignment;

public class Student {

    private String name, course, mobile, adminNumber;
    private char gender;

    public Student() {
    }//default constructor

    public Student(String n, String c, String m, char g, String AN) {
        name = n;
        course = c;
        mobile = m;
        gender = g;
        adminNumber = AN;
    }//overloaded constructor

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getMobile() {
        return mobile;
    }

    public char getGender() {
        return gender;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setName(String N) {
        name = N;
    }

    public void setCourse(String C) {
        course = C;
    }

    public void setMobile(String M) {
        mobile = M;
    }

    public void setGender(char G) {
        gender = G;
    }

    public void setAdminNumber(String AN) {
        adminNumber = AN;
    }
}//end of class
