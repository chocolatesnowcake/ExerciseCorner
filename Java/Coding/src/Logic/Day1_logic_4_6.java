package Logic;

public class Day1_logic_4_6 {
	public static void main(String[] args) {
		// 先印出上層 5 列
		for(int i=1; i<=5; i++) {
			for(int j=0; j<(5-i); j++) {
				System.out.print(" ");
			}
			for(int k=0; k<i; k++) {
				System.out.print("* ");
			}
			System.out.println();
		}
		
		// 再印出下層 4 列
		for(int l=4; l>=0; l--) {
			for(int m=0; m<=(4-l); m++) {
				System.out.print(" ");
			}
			for(int n=0; n<l; n++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
}
