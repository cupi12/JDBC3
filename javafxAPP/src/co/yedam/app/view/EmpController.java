package co.yedam.app.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.yedam.app.model.EmpDAO;
import co.yedam.app.model.EmpDO;
import co.yedam.app.model.ObserverEmpDO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EmpController implements Initializable {
	@FXML
	TextField txtEmployeeId;
	@FXML
	TextField txtLastName;
	@FXML
	TextField txtEmail;
	@FXML
	TextField txtHireDate;
	@FXML
	TextField txtJobId;
	@FXML // 테이블 뷰
	TableView<EmpDO> tvEmp;
	@FXML
	TableColumn<ObserverEmpDO, String> colEmployeeId;
	@FXML
	TableColumn<ObserverEmpDO, String> colLastName;
	@FXML
	TableColumn<ObserverEmpDO, String> colEmail;
	@FXML
	TableColumn<ObserverEmpDO, String> colHireDate;
	@FXML
	TableColumn<ObserverEmpDO, String> colJobId;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colEmployeeId.setCellValueFactory(new PropertyValueFactory<ObserverEmpDO, String>("employeeId"));
		colLastName.setCellValueFactory(new PropertyValueFactory<ObserverEmpDO, String>("colLastName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<ObserverEmpDO, String>("Email"));
		colHireDate.setCellValueFactory(new PropertyValueFactory<ObserverEmpDO, String>("HireDate"));
		colJobId.setCellValueFactory(new PropertyValueFactory<ObserverEmpDO, String>("JobId"));

	}

	@FXML
	public void empInsert(ActionEvent actionEvent) {
		// DAO
		// 텍스트 필드값을 읽어서 DO 객체에 담음
		EmpDO emp = new EmpDO();
		emp.setEmployeeId(txtEmployeeId.getText()); //
		emp.setLastName(txtLastName.getText());
		emp.setEmail(txtEmail.getText());
		emp.setHireDate(txtHireDate.getText());
		emp.setJobId(txtJobId.getText());

		// DAO 등록
		EmpDAO dao = new EmpDAO();
		dao.insert(emp);
		System.out.println("등록처리됨");
	}

	@FXML
	public void empUpdate(ActionEvent actionEvent) {
		System.out.println("수정처리됨");
	}

	@FXML // 단건조회
	public void empSelect(ActionEvent actionEvent) {
		// 조회할 사번을 DO 객체에 담음
		EmpDO emp = new EmpDO();
		emp.setEmployeeId(txtEmployeeId.getText());
		// 단건 조회
		EmpDAO dao = new EmpDAO();
		EmpDO result = dao.selectOne(emp);
		// 텍스트 필드에 표시
		txtEmployeeId.setText(result.getEmployeeId());
		txtLastName.setText(result.getLastName());
		txtEmail.setText(result.getEmail());
		txtHireDate.setText(result.getHireDate());
		txtJobId.setText(result.getJobId());

		System.out.println("조회처리됨");
	}

	@FXML // 전체조회 버튼 클릭 이벤트 핸들러
	public void empSelectAll(ActionEvent actionEvent) {
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpDO> list = dao.selectAll();
		tvEmp.setItems(FXCollections.observableArrayList(list));
	}
	
	@FXML //tableview 마우스 클릭이벤트, 클릭한 행의 정보를 텍스트필드에 표시
	public void empInfo(MouseEvent moseEvent) {
		EmpDO empDO = tvEmp.getSelectionModel().getSelectedItem();
		txtEmployeeId.setText(empDO.getEmployeeId());
		txtLastName.setText(empDO.getLastName());
		txtEmail.setText(empDO.getEmail());
		txtHireDate.setText(empDO.getHireDate());
		txtJobId.setText(empDO.getJobId());
	}
	@FXML
	protected void delEmp(ActionEvent event) {
        EmpDO empDO = tvEmp.getSelectionModel().getSelectedItem();
        ObservableList<EmpDO> data = tvEmp.getItems(); //getItem = 전체 데이터 다 가져옴

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("삭제 메시지");
        alert.setHeaderText("칼럼을 삭제하려합니다.");
        alert.setContentText("정말 삭제하시겠습니까?");

        Optional<ButtonType>result = alert.showAndWait();  //showAndWait = 창을 보여줌
        if(result.get() == ButtonType.OK) {
        	EmpDAO dao = new EmpDAO();
        	//DB에서 삭제
        	dao.delete(empDO);
        	//tableview 에서 삭제
        	data.remove(empDO);
        }
	}

}
