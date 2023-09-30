package service;

import java.util.List;

import bean.Student;

public interface StudentService {
	public List<Student> selectStudent(); //查詢學生資料

	public Boolean insertStudent(Student student); //新增學生資料

	public Integer updateStudent(Student student); //修改學生資料

	public Boolean deleteStudent(String sno); //修改學生資料
}
