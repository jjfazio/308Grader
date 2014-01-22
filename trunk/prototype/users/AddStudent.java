package users;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Kevin
 */
public class AddStudent extends Application {

    public AddStudent(){
        start(new Stage());
    }

    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Add Student");
        VBox mainBox = new VBox(15);

        HBox hbox1 = new HBox(40);
        VBox vbox1 = new VBox(15);
        vbox1.setPrefWidth(280.0);
        VBox vbox2 = new VBox(20);

        //Set padding for the right and left panels to leave space around the
        // dialogs border
        vbox1.setPadding(new Insets(15, 15, 15, 15));
        vbox2.setPadding(new Insets(15, 15, 15, 15));

        hbox1.getChildren().add(vbox1);
        hbox1.getChildren().add(vbox2);

        mainBox.getChildren().add(hbox1);

        HBox firstName = new HBox(5);
        HBox middleName = new HBox(5);
        HBox lastName = new HBox(5);
        HBox username = new HBox(5);
        HBox id = new HBox(5);
        HBox major = new HBox(5);
        HBox email = new HBox(5);


        vbox1.getChildren().add(firstName);
        vbox1.getChildren().add(middleName);
        vbox1.getChildren().add(lastName);
        vbox1.getChildren().add(username);
        vbox1.getChildren().add(id);
        vbox1.getChildren().add(major);
        vbox1.getChildren().add(email);

        VBox courses = new VBox();
        Label courseLabel = new Label("Course(s) Enrolled: ");
        ListView<String> courseList = new ListView<String>();
        ScrollBar courseScroll = new ScrollBar();
        courseScroll.setOrientation(Orientation.VERTICAL);

        //Properly size the list of courses and add the label and list to
        // the courses vertical box.  Top box on right half of dialog.
        courseList.setPrefWidth(250);
        courseList.setPrefHeight(70);
        courses.getChildren().add(courseLabel);
        courses.getChildren().add(courseList);

        // Button holders for Add Course and Delete Course buttons
        HBox btnHolder = new HBox();
        HBox leftBtnHolder = new HBox();
        HBox rightBtnHolder = new HBox();
        btnHolder.getChildren().add(leftBtnHolder);
        btnHolder.getChildren().add(rightBtnHolder);
        courses.getChildren().add(btnHolder);

        // Add Course and Delete Course buttons creation
        Button addCourseBtn = new Button();
        Button deleteCourseBtn = new Button();
        addCourseBtn.setText("Add Course");
        deleteCourseBtn.setText("Delete Course");

        // The action for the Add Course button
        // Will open the Add Course dialog
        addCourseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new AddCourse();
            }
        });

        //Add Course and Delete Course buttons added to the dialog
        leftBtnHolder.setPadding(new Insets(0, 30, 0, 30));
        rightBtnHolder.setPadding(new Insets(0, 30, 0, 30));
        leftBtnHolder.getChildren().add(addCourseBtn);
        rightBtnHolder.getChildren().add(deleteCourseBtn);

        //Create Phone and Grade Level labels and text fields
        Label phoneLabel = new Label("Phone Number: ");
        TextField phoneField = new TextField();
        firstName.getChildren().addAll(phoneLabel, phoneField);

        Label gradeLevelLabel = new Label("GradeLevel: ");
        TextField gradeLevelField = new TextField();
        firstName.getChildren().addAll(gradeLevelLabel, gradeLevelField);

        //Create horizontal boxes for phone and grade level fields
        HBox phone = new HBox(15);
        HBox gradeLevel = new HBox(15);

        //Add appropriate fields to phone and grade level hboxs
        phone.getChildren().add(phoneLabel);
        phone.getChildren().add(phoneField);
        gradeLevel.getChildren().add(gradeLevelLabel);
        gradeLevel.getChildren().add(gradeLevelField);

        //Add everything to the right side of the screen
        vbox2.getChildren().add(courses);
        vbox2.getChildren().add(phone);
        vbox2.getChildren().add(gradeLevel);


        Label firstNameLab = new Label("First Name: ");
        TextField firstTextField = new TextField();
        firstName.getChildren().addAll(firstNameLab, firstTextField);

        Label middleNameLab = new Label("Middle Name: ");
        TextField middleTextField = new TextField();
        middleName.getChildren().addAll(middleNameLab, middleTextField);

        Label lastNameLab = new Label("Last Name: ");
        TextField lastTextField = new TextField();
        lastName.getChildren().addAll(lastNameLab, lastTextField);

        Label usernameLab = new Label("Username: ");
        TextField usernameTextField = new TextField();
        username.getChildren().addAll(usernameLab, usernameTextField);

        Label idLab = new Label("ID: ");
        TextField idTextField = new TextField();
        id.getChildren().addAll(idLab, idTextField);

        //bottom hbox for Confirm Add and Cancel buttons
        HBox bottomBox = new HBox();


        //Create the actual Confirm Add and Cancel buttons
        Button confirmAddBtn = new Button();
        Button cancelBtn = new Button();
        confirmAddBtn.setText("Confirm Add");
        cancelBtn.setText("Cancel");

        //Close dialog with click of the "Cancel" button
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        //Close dialog with confirm add button click.
        //this is temporary
        confirmAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        //Add the buttons to the bottom box
        HBox leftBottomBox = new HBox();
        HBox rightBottomBox = new HBox();
        leftBottomBox.setAlignment(Pos.CENTER);
        leftBottomBox.getChildren().add(confirmAddBtn);
        vbox1.getChildren().add(leftBottomBox);
        rightBottomBox.setAlignment(Pos.CENTER);
        rightBottomBox.getChildren().add(cancelBtn);
        vbox2.getChildren().add(rightBottomBox);

        //Add bottom box to the main dialog

        Scene scene = new Scene(mainBox, 650, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
