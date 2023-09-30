package Logic;

import java.util.Random;

public class Day1_logic_2 {

	public static void main(String[] args) {
		int[] score = new int[20];
		
		Random random = new Random();
		
		for(int i=0; i<score.length; i++) {
			//生成0~100隨機亂數
			score[i] = random.nextInt(101);
			switch((score[i]-1)/10) {
			case 9:
				System.out.println("甲");
			case 8:
				System.out.println("乙");
			case 7:
				System.out.println("丙");
			case 6:
				System.out.println("丁");
			case 5:
				System.out.println("戊");
			case 4:
				System.out.println("己");
			case 3:
				System.out.println("庚");
			case 2:
				System.out.println("辛");
			case 1:
				System.out.println("壬");
			case 0:
				System.out.println("癸");
			}
		}	
	}

}
