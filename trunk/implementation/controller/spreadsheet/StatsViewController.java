package controller.spreadsheet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.assignments_categories.Assignment;
import model.gradebook.Gradebook;
import model.spreadsheet.SpreadsheetCourse;
import model.spreadsheet.Statistics;
import model.users.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the communication between
 * the statistics model class and the statistics
 * view class
 *
 * @author Kevin Feutz
 */
public class StatsViewController
{
    @FXML
    /** Table that represents the Spreadsheet */
    private TableView<Statistics> table;

    @FXML
    /** First column containing the name of the statistic */
    private TableColumn<Statistics, String> statNameColumn;

    /** The current spreadsheet used to display statistics */
    SpreadsheetCourse currentSpreadsheet;

    /** The list of students belonging to the current spreadsheet */
    List<Student> spreadsheetRoster;

    /** The list of assignments belonging to the current spreadsheet */
    List<Assignment> spreadsheetAssignments;

    /** The list of statistic names */
    ObservableList<Statistics> statistics;

    @FXML
    ArrayList<TableColumn<String, String>> assignmentColumns;

    public void initialize()
    {
        Gradebook currentGradebook = Gradebook.getInstance();
        this.currentSpreadsheet = currentGradebook.getCurrentCourse();
        this.spreadsheetAssignments = currentSpreadsheet.getCategoryContainer()
            .getRoot().getAssignments();
        this.spreadsheetRoster = currentSpreadsheet.getStudentRoster();
        this.assignmentColumns = new ArrayList<TableColumn<String, String>>();
        this.statistics = FXCollections.observableArrayList();
        createStatNamesColumn();
    }

    /**
     * This method creates the first column of the table
     */
    public void createStatNamesColumn()
    {
        statistics.add(new Statistics("Mean", spreadsheetRoster));
        statistics.add(new Statistics("Median", spreadsheetRoster));
        statistics.add(new Statistics("Range", spreadsheetRoster));
        statistics.add(new Statistics("Graded", spreadsheetRoster));
        this.statNameColumn.setCellValueFactory(new PropertyValueFactory<Statistics, String>("statName"));
        table.setItems(statistics);
        addColumns();
    }

    /**
     * This method adds a column for each assignment belonging
     * to the current spreadsheet course
     */
    public void addColumns()
    {
        for(Assignment currentAssignment : spreadsheetAssignments)
        {
            TableColumn<Statistics, String> currentColumn = new TableColumn<Statistics, String>(currentAssignment.getName());
            assignmentColumns.add(new TableColumn<String, String>(currentAssignment.getName()));
            currentColumn.setCellValueFactory(new StatCallBack(currentAssignment));
            table.getColumns().add(currentColumn);
        }
    }

    /**
     * CallBack for the stat columns for each assignment
     * @author jamesfazio
     *
     */
    private class StatCallBack implements Callback<TableColumn.CellDataFeatures<Statistics, String>, ObservableValue<String>> {
        public Assignment currentAssignment;

        public StatCallBack(Assignment currentAssignment)
        {
            this.currentAssignment = currentAssignment;
        }

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Statistics, String> param)
        {
            if(param.getValue().getStatName().equals("Mean"))
            {
                double meanValue = param.getValue().calcMean(currentAssignment);
                return new SimpleStringProperty(String.format("%.1f %%",meanValue));
            }
            else if(param.getValue().getStatName().equals("Graded")){
                int numGraded = param.getValue().calcNumAssignmentsGraded(currentAssignment);
                return new SimpleStringProperty(String.format("%d", numGraded));
            }
            else if(param.getValue().getStatName().equals("Median")){
                double median = param.getValue().calcMedian(currentAssignment);
                return new SimpleStringProperty(String.format("%.1f %%", median));
            }
            else if(param.getValue().getStatName().equals("Range")){
                double range = param.getValue().calcRange(currentAssignment);
                return new SimpleStringProperty(String.format("%.1f %%", range));
            }
            else {
                return new SimpleStringProperty(String.format("%d", 0));
            }
        }
    }
}
