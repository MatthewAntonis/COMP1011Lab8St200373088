package advjavamidterm200373088;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class EmployeeController {

    @FXML
    private TableView<EmployeeModel.Employee> employeeTable;
    @FXML
    private TableColumn<EmployeeModel.Employee, Integer> idColumn;
    @FXML
    private TableColumn<EmployeeModel.Employee, String> nameColumn;
    @FXML
    private TableColumn<EmployeeModel.Employee, String> departmentColumn;
    @FXML
    private TableColumn<EmployeeModel.Employee, String> emailColumn;
    @FXML
    private TableColumn<EmployeeModel.Employee, Double> salaryColumn;
    @FXML
    private Label employeeCountLabel;
    @FXML
    private Label avgSalaryLabel;
    @FXML
    private Button deleteButton;
    @FXML
    private TextField selectedIdField;

    private EmployeeModel employeeModel;

    public void initialize() {
        employeeModel = new EmployeeModel();
        loadEmployeeData();

        // Initially disable the delete button
        deleteButton.setDisable(true);

        // Add a listener to the selection property of the table
        employeeTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            // When a new selection is made, enable the delete button and set the selected ID
            if (newSelection != null) {
                selectedIdField.setText(String.valueOf(newSelection.getId()));
                deleteButton.setDisable(false); // Enable the delete button
            } else {
                // If nothing is selected, clear the ID field and disable the delete button
                selectedIdField.setText("");
                deleteButton.setDisable(true); // Keep the delete button disabled
            }
        });
    }

    private void loadEmployeeData() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTable.setItems(FXCollections.observableArrayList(employeeModel.getAllEmployees()));

        int numberOfEmployees = employeeTable.getItems().size();
        double totalSalary = 0;
        for (EmployeeModel.Employee employee : employeeTable.getItems()) {
            totalSalary += employee.getSalary();
        }
        double avgSalary = (numberOfEmployees > 0) ? totalSalary / numberOfEmployees : 0;

        employeeCountLabel.setText("Number of Employees: " + numberOfEmployees);
        avgSalaryLabel.setText("Avg Salary: " + avgSalary);
    }

    @FXML
    private void handleDeleteEmployee() {
        EmployeeModel.Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            Alert confirmAlert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete this employee?", ButtonType.YES, ButtonType.NO);
            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    boolean isDeleted = employeeModel.deleteEmployeeById(selectedEmployee.getId());
                    if (isDeleted) {
                        loadEmployeeData();
                    } else {
                        showErrorAlert("Deletion Error", "Could not delete the selected employee.");
                    }
                }
            });
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
