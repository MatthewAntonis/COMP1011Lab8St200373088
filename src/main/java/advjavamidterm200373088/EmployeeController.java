package advjavamidterm200373088;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;

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

    private EmployeeModel employeeModel;

    public void initialize() {
        employeeModel = new EmployeeModel();
        loadEmployeeData();
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
}
