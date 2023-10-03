package demo;

import java.text.SimpleDateFormat;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class JdbcDemo {
public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	
	StudentService studentService = new StudentServiceImpl();
	
	public static void main(String[] args) {
		StudentService studentService = new StudentServiceImpl();
		List<Integer> resultList = studentService.insertStudent(10);
		for(int i:resultList) {
			System.out.println(i + "筆");
		}
		
		List<Student> studentList = studentService.selectStudent();
		ObjectMapper objectmapper = new ObjectMapper();
		
		int count = 0;
		
		for(Student student:studentList) {
				try {
				String json = objectmapper.writeValueAsString(student);
				System.out.println(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}count++;
		}
		System.out.print("共查出" + count + "筆");
		
	}
}
