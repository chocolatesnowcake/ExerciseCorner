# ExerciseCorner
程式練習



---
## Java 程式練習
### * 邏輯練習
**1. 99乘法表**

**2. 產生成績亂數後分類。**

(使用)
*     90-100 → 甲、80-90 → 乙

**3. 猜數字遊戲 0A0B**

乙：7865

甲：3A0B

乙：7860

甲：4A0B

直到甲方說出4A0B才算乙方真的猜中甲方的數字！

**4. 畫星星**

```java
*         |       *        |    * * * * *    |    *****     
**        |      * *       |     * * * *     |     ****
***       |     * * *      |      * * *      |      ***
****      |    * * * *     |       * *       |       **
*****     |   * * * * *    |        *        |        *
```

**5. 計算出使用者輸入日期加上天數，並印出原本日期 和 加上天數日期**
```java
// * Point: 用 SimpleDateFormat 格式化

SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

// String 轉 Date
String source = scanner.next();
Date date = dateFormatter.parse(source);

// 計算使用者輸入日期 source 加上 使用者輸入天數 dateNum
Calendar calendar = new GregorianCalendar();
calendar.setTime(date);
calendar.add(Calendar.DATE, dateNum);

Date newDate = calendar.getTime();

// Date 轉 String 
String result = dateFormatter.format(newDate);
```
***
### * 集合與方法回傳練習

**1. 大樂透要排序、不重復、如果個位數的話前面補 0**
```java
// * Point: String 的 format 方法 (採用 printf 樣式的格式字符串)
// 參考: https://www.hxstrive.com/article/942.htm
String.format("%02d", 9);
```

**2. 身分證驗證器**

**3. 身分證產生器**

**4. 使用 TreeSet 產生大樂透**

**5. 使用第一個題目回傳 string 陣列，把裡面的資料放入 arrayList 裡面**

**6. 使用第四個題目回傳 TreeSet 陣列，把裡面的資料放入 HashMap 裡面**

---
## JDBC 
### * CRUD練習
```java
// * Point: 連接 oracle 資料庫後，對資料進行增查改刪操作。
// 資料庫連接操作範例 ( 示範 Read ) :

// Class.forName 註冊驅動程式 
Class.forName("oracle.jdbc.driver.OracleDriver");

// 透過 url、user、password 建立連線
String url = "jdbc:oracle:thin:@localhost:1521:XE";
String user = "system";
String password = "oracle";

Connection conn = DriverManager.getConnection(url, user, password);

// Statement 可以向資料庫發送要執行的 SQL語句。
Statement stmt = getConnection().createStatement(); 
            
String sql = "SELECT SNO,SNAME,SBDAY,SSEX,SMAIL,SPWD FROM STUDENT";

// ResultSet 用來存取查詢結果後的資料庫數據
ResultSet rs = stmt.executeQuery(sql);

List<Student> studentList = new ArrayList<>();
            
// 將 ResultSet 查詢後的數據包成 Java 物件後放入 List 回傳。
while(rs.next()) {
    Student student = new Student();
    student.setSno(rs.getString("SNO"));
    student.setSname(rs.getString("SNAME"));
    student.setSbday(rs.getDate("SBDAY"));
    student.setSsex(rs.getInt("SSEX"));
    student.setSmail(rs.getString("SMAIL"));
    student.setSpwd(rs.getString("SPWD"));
            	
    studentList.add(student);
}
```