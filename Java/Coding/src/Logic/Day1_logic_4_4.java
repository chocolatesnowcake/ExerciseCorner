package Logic;

public class Day1_logic_4_4 {
	public static void main(String[] args) {
		for(int i=0; i<5; i++) {
			for(int k=5; k>(5-i); k--) {
				System.out.print(" ");
			}
			for(int j=5; j>i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
