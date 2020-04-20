package co.yedam.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDAO {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = null;

	// 등록
	public void insert(EmpDO emp) {
		try {
			// 1. connect(DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");
			// 2.statement(SQL 구문 준비)
			String sql = "insert into Employees (employee_id,last_name,email, hire_date,job_id) values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3.execute
			pstmt.setString(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getHireDate());
			pstmt.setString(5, emp.getJobId());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 4.조회 결과

	}// end of insert()
		// 수정

	// 단건 조회
	public EmpDO selectOne(EmpDO emp) {
		EmpDO empDO = new EmpDO();
		try {
			// 1. connect(DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");
			// 2.statement(SQL 구문 준비)
			String sql = "select * from employees where employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3.execute
			pstmt.setString(1, emp.getEmployeeId());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) { //결과처리
				empDO.setEmployeeId(rs.getString("employee_id"));
				empDO.setLastName(rs.getString("last_name"));
				empDO.setEmail(rs.getString("email"));
				empDO.setHireDate(rs.getString("hire_date"));
				empDO.setJobId(rs.getString("job_id"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empDO;
	}// end of selectOne()
		// 삭제

	public void delete(EmpDO emp) {
		try {
			// 1. connect(DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");
			// 2.statement(SQL 구문 준비)
			String sql = "delete from employees where employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3.execute
			pstmt.setString(1, emp.getEmployeeId());
			int r = pstmt.executeUpdate(sql);
			System.out.println(r + "건 삭제됨");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5.close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 4.조회 결과
	}// end of delete()
	
	public ArrayList<EmpDO> selectAll()  {//전체조회
		ArrayList<EmpDO> list = new ArrayList<EmpDO>();
		try {
			//1. connect(DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr");			
			//2. statement(SQL 구문 준비)
			String sql = "select * from employees order by employee_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//3. execute
			ResultSet rs = pstmt.executeQuery();
			//4. 결과 처리
			while(rs.next()) {
				EmpDO empDO = new EmpDO();
				empDO.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				empDO.setLastName(rs.getString("last_name"));
				empDO.setEmail(rs.getString("email"));
				empDO.setHireDate(rs.getString("hire_date"));
				empDO.setJobId(rs.getString("job_id"));
				list.add(empDO);
			}			
		} catch (SQLException e) {
			e.printStackTrace();   
		} finally {
			try {				
				conn.close();	
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	} 

}
