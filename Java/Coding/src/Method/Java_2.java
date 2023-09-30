package Method;

import java.util.Scanner;

public class Java_2 {

	public static void main(String[] args) {
		/* 身份證字號首字為英文字母
		 * 第一位英文字母將對照一組二位數字，將所得二位數字加剩餘的九位數字前八位由左至右做乘法計算：
　第1位*1、第2位*9、第3位*8、第4位*7 ... 第8位*1
		 * 第1位*1、第2位*9 ... 第8位*1
		 * 最後一位數因為是檢查碼，故不需計算
		 * 將這十組乘積相加總
		 * 將上式所得之總和除以十求餘數
		 * 用 10 減掉上式所得的餘數，即得檢查碼。
		 */
	Java_2 idCard = new Java_2();
	
	String result = idCard.idCardVerification();
	System.out.println(result);
			
	}
	
	public String idCardVerification() {
		String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO"; // 字母代號對照表
		
		int[] numberArray = new int[checkHead.length()];
		for(int i=0; i<checkHead.length(); i++) {
			numberArray[i] = (i+10);
		}
		
		while(true) {
			Scanner scanner = new Scanner(System.in);
		
			// 檢查首字是否為現有身分證英文字母
			System.out.println("請輸入身份證字號：");
			String cardNumber = scanner.next().toUpperCase();
			boolean check = false;
		
			for(int i=0; i<checkHead.length(); i++) {
				if(cardNumber.charAt(0) == checkHead.charAt(i)) {
					check = true;
				}
			}
			
			// 確認檢查碼是否正確
			String firstLetter = null;
			
			// 先把首位英文字母轉換為數字
			for(int i=0; i<checkHead.length(); i++) {
				if(cardNumber.charAt(0) == checkHead.charAt(i)) {
					firstLetter = String.valueOf(numberArray[i]);
				}
			}
			
			//再開始計算乘積並加總
			int total = 0;
			int length = cardNumber.length();
			for(int i=1; i<length-1; i++) {
				// 因為 charAt 抓出來的是ASCII 對應的數字，所以 1 會變成 49(要先扣掉48)
				total = total + (length-1-i)*(int)(cardNumber.charAt(i)-48);
			}
			
			total = total + (int)(firstLetter.charAt(0)-48)+ 9*(int)(firstLetter.charAt(1)-48);

			// 因為也有檢查碼為 10 (因此身分證碼最後碼為 0 的情況)，
			// 因此都再做一次取個位數的步驟後，再放入判斷式判斷是否相等		
			int checkNumber = (10 - total%10) % 10;
			int cardCheckNum = (int)(cardNumber.charAt(9)-48) % 10;
			
			if(checkNumber != cardCheckNum){
				check = false;
			}
			
			scanner.close();
			
			// 通知最終結果
			if(check) {
				return "身份證字號驗證通過";
			}else {
				return "身分證驗證失敗，請重新輸入";
			}
		}
	}
}
