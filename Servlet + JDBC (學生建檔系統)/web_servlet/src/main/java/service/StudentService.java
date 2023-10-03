package service;

import java.util.List;
import model.Student;

public interface StudentService {
	public List<Student> selectStudent(); //查詢學生資料

	public List<Integer> insertStudent(Integer insertNum); //新增學生資料
}
