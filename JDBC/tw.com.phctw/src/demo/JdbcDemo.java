package demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class JdbcDemo {
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	StudentService studentService = new StudentServiceImpl();
	
	public static void main(String[] args) {
		JdbcDemo jdbcdemo = new JdbcDemo();
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 1. 查詢學生資料
		List<Student> studentList = jdbcdemo.getStudentList();
		
		String json = "";
		
		for(Student student:studentList) {
			try {
				json = objectMapper.writeValueAsString(student);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			System.out.println(json);
		}
		System.out.print("共" + studentList.size() + "筆資料，查詢成功");
		
//		// 2. 新增學生資料
//		Date date = null;
//		
//		try {
//			date = dateFormatter.parse("2023-09-08");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		Student student = new Student();
//		student.setSno("test2");
//		student.setSname("王小明");
//		student.setSbday(date);
//		student.setSmail("test2@GMAIL.COM");
//		student.setSsex(1);
//		student.setSpwd("123456");
//		
//		Boolean result = jdbcdemo.insertStudent(student);
//		String insertJson = "";
//		
//		if(result) {
//			try {
//				insertJson = objectMapper.writeValueAsString(student);
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(result);
//		System.out.println(insertJson);
//		
//		// 3. 修改學生資料
//		Date updateDate = null;
//		try {
//			updateDate = dateFormatter.parse("2023-09-08");
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		Student updateStudent = new Student();
//		updateStudent.setSno("test2");
//		updateStudent.setSname("王小明");
//		updateStudent.setSbday(updateDate);
//		updateStudent.setSmail("update@GMAIL.COM");
//		updateStudent.setSsex(1);
//		updateStudent.setSpwd("update123");
//		
//		int change = jdbcdemo.updateStudent(updateStudent);
//		System.out.println(change + "筆資料，修改成功");
//		
//		String updateJson = "";
//		
//		if(change != 0) {
//			try {
//				updateJson = objectMapper.writeValueAsString(updateStudent);
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println(updateJson);
//		
		// 4. 刪除學生資料		
		Boolean deleteResult = jdbcdemo.deleteStudent("test2");
		System.out.println(deleteResult);
	}
	
    public List<Student> getStudentList() {

        return studentService.selectStudent();
    }
    
    public Boolean insertStudent(Student student) {

        return studentService.insertStudent(student);
    }
    
    public Integer updateStudent(Student student) {
    	
    	return studentService.updateStudent(student);
    }; 
    
    public Boolean deleteStudent(String sno) {
		
		return studentService.deleteStudent(sno);
	}
}