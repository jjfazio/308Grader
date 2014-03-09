package controller.file;

import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.AssignView;

public class ViewAssignsAsController
{
    /** Instance of the Gradebook */
    Gradebook gradebook;
    
    @FXML
    private Parent root;
    
    @FXML
    private RadioButton points;
    
    @FXML
    private RadioButton percentages;
    
    @FXML
    private RadioButton symbols;
    
    @FXML
    private void initialize()
    {
        this.gradebook = Gradebook.getInstance();
    }
    
    @FXML
    private void changeView()
    {
        AssignView view = AssignView.POINTS;;
        
        if (percentages.isSelected())
            view = AssignView.PERCENTAGES;

        if (symbols.isSelected())
            view = AssignView.SYMBOLS;
        
        gradebook.getCurrentCourse().setAssignView(view);
        close();
        
    }
    
    /**
     * Called when the user clicks on the "Cancel" button
     * Closes the Create Class dialog
     */
    @FXML
    private void cancel() {
        close();
    }
    
    private void close() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
