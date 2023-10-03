package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import dao.StudentDao;
import model.Student;

public class StudentServiceImpl implements StudentService{
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	StudentDao studentDao = new StudentDao();
	Random random = new Random();
	Calendar calendar = Calendar.getInstance();
	static int waterNum = 0;
	
	@Override
	public List<Student> selectStudent() {
		
		return studentDao.selectStudent();
	}

	@Override
	public List<Integer> insertStudent(Integer insertNum) {
		List<Integer> resultList = new ArrayList<>();
		List<Student> studentList = new ArrayList<>();

		String sno = "";
		String sname = "";
		
		Date end = new Date();
		long date = 0;
		Date birthdate;
		
		Integer ssex=0;
		String smail = "";
		String spwd = "";
		
		String[] lastName = {"陳","林","黃","張","李","王","吳","劉","蔡","楊"};
		String[] firstName = {"一","二","三","四","五","六","七","八","九","十","家","豪","淑","芬","承","恩","典","雅","如","俊","傑","志","明","美","玲","凌","心","欣","詠","晴"};
		String english = "abcdefgfijklmnopqrstuvwxyz";
		String number = "0123456789";
		
		int success = 0;
		int fail = 0;
		
		for(int i=0; i<insertNum; i++) {
			Student student = new Student();
			sname = lastName[random.nextInt(lastName.length-1)] + firstName[random.nextInt(firstName.length-1)] + firstName[random.nextInt(firstName.length-1)];
			student.setSname(sname);
			
			try {
				Date start;
				start = dateFormatter.parse("1911-01-01");
				date = random(start.getTime(), end.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			birthdate = new Date(date);
			student.setSbday(birthdate);
			
			ssex = random.nextInt(1);
			student.setSsex(ssex);
			char[] randomEn = new char[5];
			char[] randomNum = new char[5];
			for(int j=0; j<randomEn.length; j++) {
				randomEn[j] = english.charAt(random.nextInt(english.length()-1)); 
				randomNum[j] = number.charAt(random.nextInt(number.length()-1)); 
			}
			smail = String.valueOf(randomEn) + String.valueOf(randomNum) + "@gmail.com";
			student.setSmail(smail);
			char[] randomSpwd = new char[10];
			for(int j=0; j<10; j++) {
				randomSpwd[j] = english.charAt(random.nextInt(english.length()-1));
			}
			spwd = String.valueOf(randomSpwd);
			student.setSpwd(spwd);
				
			sno = generate().subSequence(0, 10).toString();
			student.setSno(sno);
			
			studentList.add(student);
		}	
		
		success = studentDao.insertStudent(studentList);
		fail = studentList.size() - success;
		
		resultList.add(success);
		resultList.add(fail);
		
		return resultList;
	}
	
	public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

	private long random(long begin, long end) {
		long result = begin + random.nextLong(end - begin);
		
		return result;
	}
}
