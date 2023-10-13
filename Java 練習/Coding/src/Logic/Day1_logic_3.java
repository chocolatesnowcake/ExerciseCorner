package Logic;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Day1_logic_3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		// 1120906 剩這個亂數產生題目
		
		Random random = new Random();
		
		while(true) {
			// 利用 Set 特性拒絕取重複的數字
			
			HashSet<Integer> set = new HashSet();
			
			while(set.size() <= 4) {
				for(int i=0; i<10; i++) {
				// 隨機產生 0~9 的數字
				set.add(random.nextInt(10));
				}
			}
			
			int[] result = new int[4];
			
			int index = 0;
			
			for(int i : set) {
				if(index >= 4) {
					break;
				}
				result[index++] = i;
			}
			
			for(int i=0; i<result.length; i++) {
				System.out.print(result[i] + " ");
				}System.out.println();
			
			while(true) {
				int[] answer = new int[4];
				
				System.out.println("請在 0~9 的範圍內輸入 4 個數字 (輸入後以Enter鍵做區隔)");
				
				for(int i=0; i<answer.length; i++) {
					answer[i] = scanner.nextInt();
				}
				
				// 確認是否輸入重複數字
				int count = 0;
				for(int q=0; q<answer.length; q++) {
					for(int s=answer.length-1; s>q; s--) {
						if(answer[s] == answer[q]) {
							count++;
						}
					}
					if(count != 0) {
						System.out.println("輸入了重複數字，請重新開始");
						break;
					}
				}
				
				
				// 比對數字是否相同
				int countA = 0;
				int countB = 0;			
				
				for(int j=0; j<4; j++) {
					// 先確認是否有陣列位置相同的正確數字 -> 計入A
					if(answer[j] == result[j]) {
						countA++;
					}else {
						// 再確認是否有陣列位置不相同，但有相同數字 -> 計入B
						for(int m=0; m<4; m++) {
							if(answer[j] == result[m]) {
								countB++;
							}
						}
					}
				}
				System.out.println(countA + "A" + countB + "B");
				
				// 最後猜中 4A 即結束繼續猜數字
				if(countA == 4) {
					System.out.println("猜中正確數字!!");
					scanner.close();
					break;
				}
			}
		}
	}
}
