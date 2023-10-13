package Method;

import java.util.ArrayList;

public class Java_5 {
	// 排序號碼、號碼不重複，個位數補 0
	public static void main(String[] args) {
		Java_1 java1 = new Java_1();
		String[] lotto = java1.playLotto();	
		
		Java_5 java5 = new Java_5();
		ArrayList<String> lottoArray = java5.playLottoList(lotto);
		
		for(String s : lottoArray) {
			System.out.print(s + " ");
		}
	}
	
	public ArrayList<String> playLottoList(String[] lotto){
		ArrayList<String> lottoArray = new ArrayList<>();
		
		for(String s : lotto) {
			lottoArray.add(s);
		}
		
		return lottoArray;
	}
}
