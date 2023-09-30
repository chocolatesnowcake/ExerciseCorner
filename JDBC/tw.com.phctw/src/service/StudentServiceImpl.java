package service;

import java.util.List;

import bean.Student;
import dao.StudentDao;

public class StudentServiceImpl implements StudentService{
	StudentDao studentDao = new StudentDao();

	@Override
	public List<Student> selectStudent() {
		
		return studentDao.selectStudent();
	}

	@Override
	public Boolean insertStudent(Student student) {
		
		return studentDao.insertStudent(student);
	}

	@Override
	public Integer updateStudent(Student student) {
		
		return studentDao.updateStudent(student);
	}

	@Override
	public Boolean deleteStudent(String sno) {
		
		return studentDao.deleteStudent(sno);
	}

	

}
