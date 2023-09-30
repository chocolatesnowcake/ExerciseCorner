package test;

import java.util.Random;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Random random = new Random();
		
		Scanner scanner = new Scanner(System.in);
		int insertNum = scanner.nextInt();
		
		String sno = "";
		String sname = "";
		String sbday = "";
		Integer ssex=0;
		String smail = "";
		String spwd = "";
		
		String[] lastName = {"陳","林","黃","張","李","王","吳","劉","蔡","楊"};
		String[] firstName = {"一","二","三","四","五","六","七","八","九","十","家","豪","淑","芬","承","恩","典","雅","如","俊","傑","志","明","美","玲","凌","心","欣","詠","晴"};
		String english = "abcdefgfijklmnopqrstuvwxyz";
		String number = "1234567890";
		
		for(int i=0; i<insertNum; i++) {
			Student student = new Student();
			sno = "a" + String.format("02%d", random.nextInt(1000));
			student.setSno(sno);
			sname = lastName[random.nextInt(lastName.length-1)] + firstName[random.nextInt(firstName.length-1)];
			student.setSname(sname);
			
			
			ssex = random.nextInt(1);
			student.setSsex(ssex);
			smail = String.valueOf(english.charAt(random.nextInt(english.length()-1)));
		}
	}	
}
