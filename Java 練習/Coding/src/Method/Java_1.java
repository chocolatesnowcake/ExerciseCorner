package Method;

import java.util.Random;

public class Java_1 {
	Random random = new Random();
	
	// 排序號碼、號碼不重複，個位數補 0
	public static void main(String[] args) {
		Java_1 java = new Java_1();
		
		String[] lotto = java.playLotto();
		
		for(int i=0; i<lotto.length; i++) {
			System.out.println(lotto[i]);
		}
	}
	
	public String[] playLotto() {
		int[] number = new int[6];
		
		while(true) {
			for(int i=0; i<number.length; i++) {
				// 產生 0~99 的數字
				number[i] = random.nextInt(100);
			}
			
			int check = 0;
			
			// 檢查號碼是否重複
			for(int i=0; i<number.length; i++) {
				for(int j=i+1; j<number.length; j++) {
					if(number[i] == number[j]) {
						check++;
					}
				}
			}
			if(check == 0) {
				break;
			}else {
				continue;
			}
		}
		
		// 排序號碼 (由小到大)
		int temp = 0;
		for(int i=0; i<number.length; i++) {
			for(int j=0; j<number.length-1; j++) {
				if(number[j] > number[j+1]) {
					temp = number[j];
					number[j] = number[j+1];
					number[j+1] = temp;
				}	
			}
		}
		
		String[] lotto = new String[number.length];
		
		// 個位數補 0
		for(int j=0; j<lotto.length; j++) {
			lotto[j] = String.format("%02d", Integer.valueOf(number[j]));
		}
		return lotto;
	}
}
