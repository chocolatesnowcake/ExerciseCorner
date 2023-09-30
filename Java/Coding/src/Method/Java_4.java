package Method;

import java.util.Random;
import java.util.TreeSet;

public class Java_4 {
	Random random = new Random();
	// 個位數補 0
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Java_4 start = new Java_4();
		
		TreeSet<String> lotto = start.playLottoSet();
		
		for(String s : lotto) {
			System.out.println(s);
		}
	}
	
	public TreeSet<String> playLottoSet() {
		TreeSet<String> lotto = new TreeSet<>();
		
		String lottoNum = "";
		
		// 產生 6 個號碼(0~99之間的號碼)
		while(lotto.size() < 6) {
			lottoNum = String.format("%02d", random.nextInt(100));
		lotto.add(lottoNum);
		}
		return lotto;
	}
}
