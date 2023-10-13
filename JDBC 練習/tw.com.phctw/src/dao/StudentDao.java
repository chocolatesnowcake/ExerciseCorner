package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class StudentDao {
	
	private Connection conn = null;
	
	public void init() {
		if(this.conn != null) return;

        try {
            // 註冊驅動程式
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String user = "system";
            String password = "oracle";
            
            // 建立連線
            conn = DriverManager.getConnection(url, user, password);
            
        } catch(SQLException e) {
            conn = null;
            System.out.println(e.getMessage());
        } catch(ClassNotFoundException e) {
            conn = null;
            System.out.println(e.getMessage());
        }
	}
	
	public Connection getConnection() {
        return this.conn;
    }
	
	public List<Student> selectStudent(){
		init();
		
        try {
            Statement stmt = getConnection().createStatement(); 
            
            String sql = "SELECT SNO,SNAME,SBDAY,SSEX,SMAIL,SPWD FROM STUDENT";
            
            ResultSet rs = stmt.executeQuery(sql);

            List<Student> studentList = new ArrayList<>();
            
            // 取得每一筆資訊
            while(rs.next()) {
            	Student student = new Student();
            	student.setSno(rs.getString("SNO"));
            	student.setSname(rs.getString("SNAME"));
            	student.setSbday(rs.getDate("SBDAY"));
            	student.setSsex(rs.getInt("SSEX"));
            	student.setSmail(rs.getString("SMAIL"));
            	student.setSpwd(rs.getString("SPWD"));
            	
            	studentList.add(student);
            }
            return studentList;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
	}
	
	public Boolean insertStudent(Student student) {
		init();
		
		Boolean result = false;
		
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = "";
			date = dateFormatter.format(student.getSbday());
			
			String sql = "INSERT INTO STUDENT VALUES ( ? , ? ,TO_DATE( ? ,'YYYY-MM-DD'), ? , ? , ? )";
			
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setString(1, student.getSno());
			stmt.setString(2, student.getSname());
			stmt.setString(3, date);
			stmt.setInt(4, student.getSsex());
			stmt.setString(5, student.getSmail());
			stmt.setString(6, student.getSpwd());
			
			stmt.executeQuery();
			
			result = true;
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Integer updateStudent(Student student) {
		init();
		
		int changeRow = 0;
		
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			String date = "";
			date = dateFormatter.format(student.getSbday());
			
			String sql = "UPDATE student SET SNAME = ?, SBDAY = TO_DATE( ? ,'YYYY-MM-DD'), SSEX = ?, SMAIL = ?, SPWD = ? WHERE SNO = ?";
			
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setString(1, student.getSname());
			stmt.setString(2, date);
			stmt.setInt(3, student.getSsex());
			stmt.setString(4, student.getSmail());
			stmt.setString(5, student.getSpwd());
			stmt.setString(6, student.getSno());
			
			changeRow = stmt.executeUpdate();
			
			return changeRow;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return changeRow;
	};
	
	public Boolean deleteStudent(String sno) {
		init();
		
		Boolean result = false;
		
		try {
			String sql = "DELETE FROM student WHERE SNO = ?";
			
			PreparedStatement stmt = getConnection().prepareStatement(sql);
			
			stmt.setString(1, sno);
			
			stmt.executeUpdate();
			
			result = true;
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
