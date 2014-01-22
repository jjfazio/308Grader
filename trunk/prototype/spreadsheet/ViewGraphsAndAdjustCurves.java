/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import java.util.Arrays; 
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.scene.chart.PieChart;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author erikowen
 */
public class ViewGraphsAndAdjustCurves extends Application {
    private Button btn;
    private VBox verticalLayout;
    private HBox horizontalTitleLayout;
    private HBox horizontalOptionsLayout;
    private HBox horizontalGraphLayout;
    private VBox verticalViewGraphOptions;
    private VBox verticalAdjustCurveOptions;
    private Label viewGraphLabel;
    private CheckBox barChartCheckBox;
    private CheckBox pieChartCheckBox;
    private Label viewGraphGranularityLabel;
    private HBox horizontalGranularityGroup;
    private RadioButton onePercentGranularity;
    private RadioButton tenPercentGranularity;
    private Label adjustCurvesLabel;
    private HBox horizontalAddPercentageCurveLayout;
    private Label addPercentageCurveLabel;
    private TextField userSpecifiedCurve;
    private Label percentLabel;
    private Label orLabel;
    private Button addCustomCurveButton;
    private Button savedCurvedGradesButton;
    private Label pageTitle;
    private Stage customCurveStage;
    
    public ViewGraphsAndAdjustCurves(){
       start(new Stage());
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
    
    @Override
    public void start(Stage primaryStage) {
        initLayout(primaryStage);
        initOnClickListeners();
    }
    
    private void initLayout(Stage primaryStage) {
        initializeTitle();
        initializeGraphOptionsBox();
        initializeCurveOptionsBox();
        initializeBarChart();
        initializePieChart();
        
        this.horizontalTitleLayout.setAlignment(Pos.CENTER);
        this.horizontalTitleLayout.getChildren().add(this.pageTitle);
        
        this.horizontalOptionsLayout.setPadding(new Insets(0, 50, 50 , 50));
        this.horizontalOptionsLayout.setAlignment(Pos.CENTER);
        this.horizontalOptionsLayout.setSpacing(150);
        this.horizontalOptionsLayout.getChildren().add(this.verticalViewGraphOptions);
        this.horizontalOptionsLayout.getChildren().add(this.verticalAdjustCurveOptions);
        
        this.horizontalGraphLayout.setAlignment(Pos.CENTER);
        
        this.verticalLayout.getChildren().add(this.horizontalTitleLayout);
        this.verticalLayout.setPadding(new Insets(10,10,10,10));
        this.verticalLayout.getChildren().add(this.horizontalOptionsLayout);
        this.verticalLayout.getChildren().add(this.horizontalGraphLayout);
        
        
        StackPane root = new StackPane();
        root.getChildren().add(verticalLayout);
        Scene scene = new Scene(root, 800, 700);
        
        primaryStage.setTitle("Graph & Curve Adjustment View");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }
    
    private void initializeTitle() {
        this.horizontalTitleLayout = new HBox(5);
        this.pageTitle = new Label("Quiz 4 Graph & Curve Adjustment");
        this.pageTitle.setFont(new Font(40.0));
        this.pageTitle.setAlignment(Pos.TOP_CENTER);
    }
    
    private void initializeGraphOptionsBox() {
        this.onePercentGranularity = new RadioButton("1%");
        this.tenPercentGranularity = new RadioButton("10%");
        this.tenPercentGranularity.setSelected(true);
        this.viewGraphGranularityLabel = new Label("Interval Granularity:");
        this.horizontalGranularityGroup = new HBox();
        this.barChartCheckBox = new CheckBox("Bar Chart");
        this.barChartCheckBox.setSelected(true);
        this.pieChartCheckBox = new CheckBox("Pie Chart");
        this.pieChartCheckBox.setSelected(true);
        this.viewGraphLabel = new Label("View Grades As...");
        this.verticalViewGraphOptions = new VBox(3);
        this.verticalAdjustCurveOptions = new VBox(3);
        this.horizontalOptionsLayout = new HBox(10);
        this.verticalLayout = new VBox(10);
        
        this.horizontalGranularityGroup.setSpacing(5);
        this.horizontalGranularityGroup.getChildren().add(this.onePercentGranularity);
        this.horizontalGranularityGroup.getChildren().add(new Label(" Or "));
        this.horizontalGranularityGroup.getChildren().add(this.tenPercentGranularity);
        
        this.verticalViewGraphOptions.setStyle("-fx-border-color: #000000;");
        this.verticalViewGraphOptions.setSpacing(15);
        this.verticalViewGraphOptions.setPrefSize(200, 100);
        //this.verticalViewGraphOptions.setStyle("-fx-background-color: #D6D1CE;");
        this.verticalViewGraphOptions.getChildren().add(this.viewGraphLabel);
        this.verticalViewGraphOptions.getChildren().add(this.barChartCheckBox);
        this.verticalViewGraphOptions.getChildren().add(this.pieChartCheckBox);
        this.verticalViewGraphOptions.getChildren().add(this.viewGraphGranularityLabel);
        //this.verticalViewGraphOptions.getChildren().add(this.onePercentGranularity);
        //this.verticalAdjustCurveOptions.getChildren().add(this.tenPercentGranularity);
        this.verticalViewGraphOptions.getChildren().add(this.horizontalGranularityGroup);
        this.verticalViewGraphOptions.setPadding(new Insets(20,20,20,20));
        this.verticalViewGraphOptions.setAlignment(Pos.TOP_LEFT);
        //this.verticalViewGraphOptions.setMinSize(100, 100);
    }
    
    private void initializeCurveOptionsBox() {
        this.adjustCurvesLabel = new Label("Adjust Curve");
        this.addPercentageCurveLabel = new Label("Add % Curve:");
        this.userSpecifiedCurve = new TextField("0");
        this.userSpecifiedCurve.setPrefWidth(30);
        this.percentLabel = new Label("%");
        this.orLabel = new Label("Or...");
        this.addCustomCurveButton = new Button("Add Custom Curve");
        this.savedCurvedGradesButton = new Button("Save Curved Grades");
        this.savedCurvedGradesButton.setDisable(true);
        
        this.horizontalAddPercentageCurveLayout = new HBox(10);
        this.horizontalAddPercentageCurveLayout.getChildren().add(this.addPercentageCurveLabel);
        this.horizontalAddPercentageCurveLayout.getChildren().add(this.userSpecifiedCurve);
        this.horizontalAddPercentageCurveLayout.getChildren().add(this.percentLabel);
        
        this.verticalAdjustCurveOptions.setStyle("-fx-border-color: #000000;");
        this.verticalAdjustCurveOptions.setSpacing(15);
        this.verticalAdjustCurveOptions.setAlignment(Pos.CENTER);
        
        this.verticalAdjustCurveOptions.getChildren().add(this.adjustCurvesLabel);
        this.verticalAdjustCurveOptions.getChildren().add(this.horizontalAddPercentageCurveLayout);
        this.verticalAdjustCurveOptions.getChildren().add(this.orLabel);
        this.verticalAdjustCurveOptions.getChildren().add(this.addCustomCurveButton);
        this.verticalAdjustCurveOptions.getChildren().add(this.savedCurvedGradesButton);
        this.verticalAdjustCurveOptions.setPrefSize(200, 100);
    }
    
    private void initializeBarChart() {
        this.horizontalGraphLayout = new HBox(10);
        final String[] grade = {"10 - 59%", "60 - 69%%", "70 - 79%", "80 - 89%", "90 - 100+%"};
        final CategoryAxis yAxis = new CategoryAxis();
        final NumberAxis xAxis = new NumberAxis();
        final BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);

        bc.setTitle("Quiz 4 Grade Distribution Bar Chart");
        yAxis.setLabel("Grade");
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(grade)));

        XYChart.Series<Number,String> series1 = new XYChart.Series<Number,String>();

        series1.setName("Students");
        series1.getData().add(new XYChart.Data<Number,String>(4, grade[0]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[1]));
        series1.getData().add(new XYChart.Data<Number,String>(14, grade[2]));
        series1.getData().add(new XYChart.Data<Number,String>(8, grade[3]));
        series1.getData().add(new XYChart.Data<Number,String>(6, grade[4]));
        
        bc.getData().add(series1);
        
        this.horizontalGraphLayout.getChildren().add(bc);
    }
    
    private void initializePieChart() {
            final PieChart pc = new PieChart(FXCollections.observableArrayList(
            new PieChart.Data("A", 18),
            new PieChart.Data("B", 24),
            new PieChart.Data("C", 41),
            new PieChart.Data("D", 6),
            new PieChart.Data("F", 11)
        ));
        // setup chart
        pc.setId("pieChart");
        pc.setTitle("Quiz 4 Grade Type Distribution Pie Chart");
        pc.setLabelsVisible(true);
        
        this.horizontalGraphLayout.getChildren().add(pc);
    }
    
    private void initOnClickListeners() {
        this.addCustomCurveButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                customCurveStage = new Stage();
                //final Stage stage = new Stage();
                Group rootGroup = new Group();
                
                initCustomCurveAdjustment(rootGroup);
                
                Scene scene = new Scene(rootGroup, 700, 700);
                
                customCurveStage.setScene(scene);
                customCurveStage.centerOnScreen();
                customCurveStage.show();
                //stage.setScene(scene);
                //stage.centerOnScreen();
                //stage.show();
            }
        });
    }
    
    private VBox verticalCustomCurveLayout;
    private Label customCurveLabel;
    private HBox horizontalCustomCurveButtonsLayout;
    private Button cancelButton;
    private Button applyCurveButton;
    
    private void initCustomCurveAdjustment(Group rootGroup) {
        this.verticalCustomCurveLayout = new VBox(5);
        this.verticalCustomCurveLayout.setPadding(new Insets(40, 55, 40, 40));
        this.customCurveLabel = new Label("Custom Curve Adjuster");
        this.customCurveLabel.setAlignment(Pos.TOP_CENTER);
        this.customCurveLabel.setFont(new Font(50.0));
        this.horizontalAddPercentageCurveLayout = new HBox(5);
        this.cancelButton = new Button("Cancel");
        this.addCustomCurveButton = new Button("Apply Curve");
        this.addCustomCurveButton.setDisable(true);
        
        this.horizontalAddPercentageCurveLayout.getChildren().add(this.cancelButton);
        this.horizontalAddPercentageCurveLayout.getChildren().add(this.addCustomCurveButton);
        
        this.verticalCustomCurveLayout.getChildren().add(this.customCurveLabel);
        initCustomCurveBarChart();
        this.verticalCustomCurveLayout.getChildren().add(new Label("This page will be more finely tuned in feature milestones."));
        this.verticalCustomCurveLayout.getChildren().add(this.horizontalAddPercentageCurveLayout);
        
        initCustomCurveListeners();
        
        rootGroup.getChildren().add(verticalCustomCurveLayout);
    }
    
    private void initCustomCurveBarChart() {
        final String[] grade = {"50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "100+"};
        final CategoryAxis yAxis = new CategoryAxis();
        final NumberAxis xAxis = new NumberAxis();
        final BarChart<Number,String> bc = new BarChart<Number,String>(xAxis,yAxis);

        bc.setTitle("Quiz 4 Grade Distribution Bar Chart");
        yAxis.setLabel("Grade");
        yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(grade)));

        XYChart.Series<Number,String> series1 = new XYChart.Series<Number,String>();

        series1.setName("Students");
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[0]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[1]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[2]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[3]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[4]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[5]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[6]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[7]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[8]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[9]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[10]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[11]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[12]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[13]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[14]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[15]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[16]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[17]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[18]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[19]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[20]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[21]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[22]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[23]));
        series1.getData().add(new XYChart.Data<Number,String>(4, grade[24]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[25]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[26]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[27]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[28]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[29]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[30]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[31]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[32]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[33]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[34]));
        series1.getData().add(new XYChart.Data<Number,String>(4, grade[35]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[36]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[37]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[38]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[39]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[40]));
        series1.getData().add(new XYChart.Data<Number,String>(3, grade[41]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[42]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[43]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[44]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[45]));
        series1.getData().add(new XYChart.Data<Number,String>(2, grade[46]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[47]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[48]));
        series1.getData().add(new XYChart.Data<Number,String>(1, grade[49]));
        series1.getData().add(new XYChart.Data<Number,String>(0, grade[50]));
        
        bc.getData().add(series1);
        
        this.verticalCustomCurveLayout.getChildren().add(bc);
    }
    
    private void initCustomCurveListeners() {
        this.cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                customCurveStage.close();
            }
        });
    }
    
}

