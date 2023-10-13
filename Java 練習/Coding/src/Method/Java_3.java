package Method;

public class Java_3 {
	/* 身份證字號: 英文字母 *1 + 數字 *9
	 * 第一位英文字母將對照一組二位數字，將所得二位數字加剩餘的九位數字前八位由左至右做乘法計算：
第1位*1、第2位*9、第3位*8、第4位*7 ... 第8位*1
	 * 第1位*1、第2位*9 ... 第8位*1
	 * 最後一位數因為是檢查碼，故不需計算
	 * 將這十組乘積相加總
	 * 將上式所得之總和除以十求餘數
	 * 用 10 減掉上式所得的餘數，即得檢查碼。
	 */
	public static void main(String[] args) {
		Java_3 start = new Java_3();
		String idNumber = start.idCardProduce();
		
		System.out.println(idNumber);
	}
	
	public String idCardProduce() {
		// 先隨機產生身份證字號: 英文字母 *1 + 數字 *9
		String checkHead = "ABCDEFGHJKLMNPQRSTUVWXYZIO";
		
		int[] numberArray = new int[checkHead.length()];
		for(int i=0; i<checkHead.length(); i++) {
			numberArray[i] = (i+10);
		}
		
		char[] idNumber = new char[10];
		String cardNum = "";
		
		// 先產生英文字母
		int index = (int)(Math.random()*checkHead.length());
		idNumber[0] = checkHead.charAt((char)index);
		
		
		// 再產生數字

		//先產生 1 或 2
		int firstNum = (int)Math.random()*3+49;
			
		idNumber[1] = (char)firstNum;
		
		//再產生 7 碼數字接在後面
		for(int i=0; i<7; i++) {
			idNumber[i+2] = (char)(int)(Math.random()*10+48);
		}
		
		// 最後產生檢查碼接在最後
		String firstLetter = null;
		
		for(int i=0; i<numberArray.length; i++) {
			if(idNumber[0] == checkHead.charAt(i)) {
				firstLetter = String.valueOf(numberArray[i]);
			}
		}
		
		for(int i=1; i<9; i++) {
			firstLetter = firstLetter + idNumber[i];
		}
		
		int total = 0;
		
		for(int i=0; i<9; i++) {
			total = total + ((int)firstLetter.charAt(i+1)-48)*(9-i);
		}
		total = total + ((int)firstLetter.charAt(0)-48);
		idNumber[9] = (char)((10-total%10)%10+48);
		
		for(int i=0; i<idNumber.length; i++) {
			cardNum = cardNum + idNumber[i];
		}
		
		return cardNum;
	}
	
}
