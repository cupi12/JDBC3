package co.yedam.app.model;

import javafx.beans.property.SimpleStringProperty;

public class ObserverEmpDO {
	public SimpleStringProperty employeeid = new SimpleStringProperty();
	public SimpleStringProperty lastName = new SimpleStringProperty();
	public SimpleStringProperty Email = new SimpleStringProperty();
	public SimpleStringProperty hireDate = new SimpleStringProperty();
	public SimpleStringProperty jobId = new SimpleStringProperty();

	public String getEmployeeid() {
		return employeeid.get();
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid.set(employeeid);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getEmail() {
		return Email.get();
	}

	public void setEmail(String email) {
		this.Email.set(email);
	}

	public String getHireDate() {
		return hireDate.get();
	}

	public void setHireDate(String hireDate) {
		this.hireDate.set(hireDate);;
	}

	public String getJobId() {
		return jobId.get();
	}

	public void setJobId(String jobId) {
		this.jobId.set(jobId);
	}
}
