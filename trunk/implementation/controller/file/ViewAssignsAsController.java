package controller.file;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import model.gradebook.Gradebook;
import model.spreadsheet.AssignView;

/**
 * Controller for the View Assignments As view. Allows the user to change the
 * assignment columns to display the grades in different modes.
 * @author jamesfazio
 *
 */
public class ViewAssignsAsController
{
    /** Instance of the Gradebook */
    Gradebook gradebook;
    
    /** Root of the view */
    @FXML
    private Parent root;
    
    /** Points option */
    @FXML
    private RadioButton points;
    
    /** Percentages option, editing can not be made with this selection */
    @FXML
    private RadioButton percentages;
    
    /** Symbols option, editing can not be made with this selection */
    @FXML
    private RadioButton symbols;
    
    @FXML
    private void initialize()
    {
        this.gradebook = Gradebook.getInstance();
        setUpRadios();
    }
    
    /**
     * Called when confirm is selected, changes the viewing mode for the
     * spreadsheet, the spreadsheet will be updated.
     */
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
     * Select the correct radio button
     */
    private void setUpRadios()
    {
        AssignView view = gradebook.getCurrentCourse().getAssignView();
        
        if (view == AssignView.PERCENTAGES)
            percentages.setSelected(true);
        
        if (view == AssignView.SYMBOLS)
            symbols.setSelected(true);
    }
    
    /**
     * Called when the user clicks on the "Cancel" button
     * Closes the Create Class dialog
     */
    @FXML
    private void cancel() {
        close();
    }
    
    /**
     * Close the window
     */
    private void close() {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
