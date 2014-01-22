package spreadsheet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MyGradingSpreadsheet  {
   private TableView<Student> table;
   final ObservableList<Student> data = FXCollections.observableArrayList(
         new Student("jfazio", "James", "Joseph", "Fazio", "5654", "SE", "Jr",
               "james@thefazios.com", "5555555"),
         new Student("eowens", "Erik", "Joshua", "Owens", "7893", "SE", "Jr",
               "eowen@gmail.com", "555555"),
         new Student("kbackers", "Kevin", "Bball", "Backers", "7633", "CS", "Sr",
               "kbackers@gmail.com", "14514515"));
   
   public MyGradingSpreadsheet() {
      init();
   }

   private void init() {
       
       table = new TableView<Student>();
       table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       
       //"UserName Column
       TableColumn<Student, String> userNameCol = new TableColumn<Student, String>();
       userNameCol.setText("Username");
       userNameCol.setMinWidth(100);
       userNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("userName"));

       //"First Name" column
       TableColumn<Student, String> firstNameCol = new TableColumn<Student, String>();
       firstNameCol.setText("First");
       firstNameCol.setMinWidth(100);
       firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
       
       //"Last Name" column
       TableColumn<Student, String> lastNameCol = new TableColumn<Student, String>();
       lastNameCol.setText("Last");
       lastNameCol.setMinWidth(100);
       lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
       
       //"ID" column
       TableColumn<Student, String> idCol = new TableColumn<Student, String>();
       idCol.setText("ID");
       idCol.setMinWidth(100);
       idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
       
       //"Major" column
       TableColumn<Student, String> majorCol = new TableColumn<Student, String>();
       majorCol.setText("Major");
       majorCol.setMinWidth(50);
       majorCol.setCellValueFactory(new PropertyValueFactory<Student, String>("major"));
       
       //"Grade Level" column
       TableColumn<Student, String> gradeLevelCol = new TableColumn<Student, String>();
       gradeLevelCol.setText("Grade Level");
       gradeLevelCol.setMinWidth(100);
       gradeLevelCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gradeLevel"));

//       //Set cell factory for cells that allow editing
//       Callback<TableColumn, TableCell> cellFactory =
//               new Callback<TableColumn, TableCell>() {
//
//                   public TableCell call(TableColumn p) {
//                       return new EditingCell();
//                   }
//               };
//       userNameCol.setCellFactory(cellFactory);
//       firstNameCol.setCellFactory(cellFactory);
//       lastNameCol.setCellFactory(cellFactory);
//       idCol.setCellFactory(cellFactory);
//       majorCol.setCellFactory(cellFactory);
//       gradeLevelCol.setCellFactory(cellFactory);

       //Set handler to update ObservableList properties. Applicable if cell is edited
      // updateObservableListProperties(userNameCol, firstNameCol, lastNameCol,
      //       idCol, majorCol, gradeLevelCol);

       table.setItems(data);
       //Enabling editing
       table.setEditable(true);
       table.getColumns().addAll(userNameCol, firstNameCol, lastNameCol,
             idCol, majorCol, gradeLevelCol); 
       
   }

// private void updateObservableListProperties(TableColumn emailCol, TableColumn firstNameCol,
//           TableColumn lastNameCol) {
//       //Modifying the email property in the ObservableList
//       emailCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {           
//           @Override public void handle(CellEditEvent<Person, String> t) {
//               ((Person) t.getTableView().getItems().get(
//                       t.getTablePosition().getRow())).setEmail(t.getNewValue());
//           }
//       });
//       //Modifying the firstName property in the ObservableList
//       firstNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {          
//           @Override public void handle(CellEditEvent<Person, String> t) {
//               ((Person) t.getTableView().getItems().get(
//                       t.getTablePosition().getRow())).setFirstName(t.getNewValue());
//           }
//       });
//       //Modifying the lastName property in the ObservableList
//       lastNameCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {           
//           @Override public void handle(CellEditEvent<Person, String> t) {
//               ((Person) t.getTableView().getItems().get(
//                       t.getTablePosition().getRow())).setLastName(t.getNewValue());
//           }
//       });
  

   // EditingCell - for editing capability in a TableCell
   private static class EditingCell extends TableCell<Student, String> {
       private TextField textField;

       public EditingCell() {
       }
      
       @Override public void startEdit() {
           super.startEdit();

           if (textField == null) {
               createTextField();
           }
           setText(null);
           setGraphic(textField);
           textField.selectAll();
       }
      
       @Override public void cancelEdit() {
           super.cancelEdit();
           setText((String) getItem());
           setGraphic(null);
       }
      
       @Override public void updateItem(String item, boolean empty) {
           super.updateItem(item, empty);
           if (empty) {
               setText(null);
               setGraphic(null);
           } else {
               if (isEditing()) {
                   if (textField != null) {
                       textField.setText(getString());
                   }
                   setText(null);
                   setGraphic(textField);
               } else {
                   setText(getString());
                   setGraphic(null);
               }
           }
       }

       private void createTextField() {
           textField = new TextField(getString());
           textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
           textField.setOnKeyReleased(new EventHandler<KeyEvent>() {                
               @Override public void handle(KeyEvent t) {
                   if (t.getCode() == KeyCode.ENTER) {
                       commitEdit(textField.getText());
                   } else if (t.getCode() == KeyCode.ESCAPE) {
                       cancelEdit();
                   }
               }
           });
       }

       private String getString() {
           return getItem() == null ? "" : getItem().toString();
       }
   } 
   
   public TableView<Student> getTableView() {
      return this.table;
   }
}
