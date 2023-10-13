package Method;

import java.util.HashMap;
import java.util.TreeSet;

public class Java_6 {

		public static void main(String[] args) {
			Java_4 java4 = new Java_4();
				
			TreeSet<String> lotto = java4.playLottoSet();
			
			
			Java_6 java6 = new Java_6();
		
			HashMap<Integer, String> map = java6.playLottoMap(lotto);
			
			for(Integer i:map.keySet()) {
				System.out.print(map.get(i) + " ");
			}
		}
		
		public HashMap<Integer, String> playLottoMap(TreeSet<String> lotto) {
			HashMap<Integer, String> map = new HashMap<>();
			
			for(int i=1; i<=lotto.size(); i++) {
				for(String s: lotto) {
					//避免在 map 中放入重複的值，因此先透過 if 進行判斷
					if(!map.containsValue(s)){
						map.put(i, s);
					}
				}
			}
			return map;
		}
}
