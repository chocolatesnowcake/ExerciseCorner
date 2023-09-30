package Logic;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Day1_logic_5 {

	public static void main(String[] args) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入日期 (請依照格式yyyy-MM-dd輸入)");
		String source = scanner.next();
		
		System.out.println("請輸入要加上的天數");
		int dateNum = scanner.nextInt();
		
		Date newDate = Computation(source, dateNum);
		
		String result = dateFormatter.format(newDate);
		
		System.out.print("原本日期為" + source + "，加上天數後日期更新為 "+result);
		scanner.close();
	}
	
	public static Date Computation(String source, int dateNum) {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		
		
		Date date = null;
		
		try {
			date = dateFormatter.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dateNum);
		
		Date newDate = calendar.getTime();

		return newDate;
	}

}
