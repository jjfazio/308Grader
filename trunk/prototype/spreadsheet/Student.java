    package spreadsheet;

    import java.util.Map;

    import javafx.beans.property.MapProperty;
    import javafx.beans.property.SimpleMapProperty;
    import javafx.beans.property.SimpleStringProperty;
    import javafx.beans.property.StringProperty;
    import javafx.collections.ObservableMap;


    /**
     * A student is composed of a first name, last name, username, id, email,
     * dateOfBirth, a collection of courses enrolled, a phone number, and a grade
     * level.  Students will belong to a student roster in the SpreadsheetCourse class.
     * Each student also contains a collection of grades that are linked to specific assignments.
     */

    public class Student {
       StringProperty userName;
       StringProperty firstName;
       String middleName;
       StringProperty lastName;
       StringProperty id;
       StringProperty major;
       StringProperty gradeLevel; //probably should be an enum
       String email;
       String phoneNumber;
       MapProperty<String, Integer> grades;


       public Student(String userName, String firstName, String middleName, String lastName,
             String id, String major, String gradeLevel, String email, String phoneNumber) {

          this.userName = new SimpleStringProperty(userName);
          this.firstName = new SimpleStringProperty(firstName);
          this.middleName = middleName;
          this.lastName = new SimpleStringProperty(lastName);
          this.id = new SimpleStringProperty(id);
          this.major = new SimpleStringProperty(major);
          this.gradeLevel = new SimpleStringProperty(gradeLevel);
          this.email = email;
          this.phoneNumber = phoneNumber;
       }


       public StringProperty userNameProperty() {
          return userName;
       }


       public void setUserName(StringProperty userName) {
          this.userName = userName;
       }


       public StringProperty firstNameProperty() {
          return firstName;
       }


       public void setFirstName(StringProperty firstName) {
          this.firstName = firstName;
       }


       public String getMiddleName() {
          return middleName;
       }


       public void setMiddleName(String middleName) {
          this.middleName = middleName;
       }


       public StringProperty lastNameProperty() {
          return lastName;
       }


       public void setLastName(StringProperty lastName) {
          this.lastName = lastName;
       }


       public StringProperty idProperty() {
          return id;
       }


       public void setId(StringProperty id) {
          this.id = id;
       }


       public StringProperty majorProperty() {
          return major;
       }


       public void setMajor(StringProperty major) {
          this.major = major;
       }


       public StringProperty gradeLevelProperty() {
          return gradeLevel;
       }


       public void setGradeLevel(StringProperty gradeLevel) {
          this.gradeLevel = gradeLevel;
       }


       public String getEmail() {
          return email;
       }


       public void setEmail(String email) {
          this.email = email;
       }


       public String getPhoneNumber() {
          return phoneNumber;
       }


       public void setPhoneNumber(String phoneNumber) {
          this.phoneNumber = phoneNumber;
       }


       public MapProperty<String, Integer> getGrades() {
          return grades;
       }


       public void setGrades(MapProperty<String, Integer> grades) {
          this.grades = grades;
       }


    }

