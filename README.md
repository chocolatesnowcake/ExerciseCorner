# ExerciseCorner
程式練習

https://hackmd.io/@QAg8aC4wQNm57FIDB0eiOQ/B1M1y4Bla

---
## Java 程式練習
###  邏輯練習
**1. 99乘法表**

**2. 產生成績亂數後分類。**


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

###  集合與方法回傳練習

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
## 資料庫練習
使用 Oracle 資料庫進行練習。
### * SPROC
#### 預存程序 Stored procedures 概念描述
Procedures是一種可以被重複使用的基本單位，包含了封裝特定過程的業務邏輯 (例如要如何轉換資料、進行運算與儲存等)。

Stored Procedure 是在大型數據庫系統中，一組為了完成特定功能的SQL 語句集，存儲在數據庫中，經過第一次編譯後調用不需要再次編譯，用戶通過指定存儲過程的名字並給出參數（如果該存儲過程帶有參數）來執行它。

資料參考： https://medium.com/jimmy-wang/oracle%E5%9F%BA%E6%9C%AC%E4%BF%AE%E7%B7%B4-pl-sql-stored-procedures-and-functions-73b235631880 、 https://www.dotblogs.com.tw/Leon-Yang/2020/11/20/164738 、 https://www.jianshu.com/p/4499814ed9ec

#### 游標 Cursor 概念描述
Cursor 是PL/SQL內建的pointer，可用來擷取整個資料集合，並進行逐筆資料存取。

用於臨時存儲一個查詢返回的多行數據，通過遍歷遊標，可以逐行訪問處理該結果集的數據。
遊標的使用方式：聲明 —> 打開 —> 讀取 —> 關閉。

資料參考： https://medium.com/jimmy-wang/oracle%E5%9F%BA%E6%9C%AC%E4%BF%AE%E7%B7%B4-pl-sql-cursor-fa0cd013692

#### EXCEPTION 異常狀況處理 概念描述
PL/SQL 程式中發現的任何錯誤都會停止執行程式。 EXCEPTION 區段來設限及從錯誤中回復。

如果在執行 陳述式時發生錯誤，則會將控制權傳遞給 EXCEPTION 區段。 
會搜尋 WHEN 子句，以找出符合所發生錯誤的第一個異常狀況。 如果找到相符項，則會執行對應的 handler-statement ，並將控制傳遞至 END 之後的陳述式。 如果找不到相符的項目，則程式會停止執行。

資料參考： https://www.ibm.com/docs/zh-tw/db2/11.1?topic=plsql-exception-handling 、 https://blog.csdn.net/onebigday/article/details/80848022 、 https://www.cnblogs.com/wzqjy/p/7810701.html


*題目說明：*
```java
建立一個名為GET_DETAILS的SPROC，以課程代號為輸入參數，找出明細並為學生評分
90以上 = A
80~89 = B
70~79 = C
60~69 = D
59分以下=E
再將產生的明細資料寫入DETAILS,如發生異常請將錯誤寫入LOG

P.S1 每一次運行前先清空DETAILS的資料
P.S2 請結合CURSOR & EXCEPTION 用法
```
*實作結果：*
```java
create or replace PROCEDURE GET_DETAILS (CLASSNUM IN SCORE_DATA.CNO%TYPE)
  AS
    CURSOR C_SCOREDATA(LOW_SCORE NUMBER, HIGH_SNORE NUMBER) IS SELECT CNAME, TNAME, SNAME, SCORE FROM SCORE_DATA WHERE CNO = CLASSNUM AND SCORE BETWEEN LOW_SCORE AND HIGH_SNORE;
    CNAME2 SCORE_DATA.CNAME%TYPE;
    TNAME2 SCORE_DATA.TNAME%TYPE;
    SNAME2 SCORE_DATA.SNAME%TYPE;
    SCORE2 SCORE_DATA.SCORE%TYPE;
    EVALUATE VARCHAR2(30);
    COUNT_NUM NUMBER;
    ERR_CODE NUMBER;
    ERR_MSG VARCHAR2(100);
  BEGIN
    DELETE FROM DETAILS;
    
  --搜索90以上 = A
    OPEN C_SCOREDATA(90,100);
    LOOP
      FETCH C_SCOREDATA INTO CNAME2, TNAME2, SNAME2, SCORE2;
      EVALUATE := 'A';
      
      EXIT WHEN C_SCOREDATA%notfound;
      INSERT INTO DETAILS VALUES(CNAME2, TNAME2, SNAME2, SCORE2, EVALUATE);
    END LOOP;
    CLOSE C_SCOREDATA;
    
   --搜索80~89 = B 
    OPEN C_SCOREDATA(80,89);
    LOOP
      FETCH C_SCOREDATA INTO CNAME2, TNAME2, SNAME2, SCORE2;
      EVALUATE := 'B';
      
      EXIT WHEN C_SCOREDATA%notfound;
      INSERT INTO DETAILS VALUES(CNAME2, TNAME2, SNAME2, SCORE2, EVALUATE);
    END LOOP;
    CLOSE C_SCOREDATA;
    
     --搜索70~79 = C   
    OPEN C_SCOREDATA(70,79);
    LOOP
      FETCH C_SCOREDATA INTO CNAME2, TNAME2, SNAME2, SCORE2;
      EVALUATE := 'C';
      
      EXIT WHEN C_SCOREDATA%notfound;
      INSERT INTO DETAILS VALUES(CNAME2, TNAME2, SNAME2, SCORE2, EVALUATE);
    END LOOP;
    CLOSE C_SCOREDATA;
    
     --搜索60~69 = D  
    OPEN C_SCOREDATA(60,69);
    LOOP
      FETCH C_SCOREDATA INTO CNAME2, TNAME2, SNAME2, SCORE2;
      EVALUATE := 'D';
      
      EXIT WHEN C_SCOREDATA%notfound;
      INSERT INTO DETAILS VALUES(CNAME2, TNAME2, SNAME2, SCORE2, EVALUATE);
    END LOOP;
    CLOSE C_SCOREDATA;
    
    --搜索59分以下=E
    OPEN C_SCOREDATA(0,59);
    LOOP
      FETCH C_SCOREDATA INTO CNAME2, TNAME2, SNAME2, SCORE2;
      EVALUATE := 'E';
      
      EXIT WHEN C_SCOREDATA%notfound;
      INSERT INTO DETAILS VALUES(CNAME2, TNAME2, SNAME2, SCORE2,EVALUATE);
    END LOOP;
    CLOSE C_SCOREDATA;
    
    --判斷是否錯誤
    SELECT COUNT(*) INTO COUNT_NUM FROM SCORE_DATA WHERE CNO = CLASSNUM;
    IF COUNT_NUM = 0 THEN
      raise_application_error (-20001,'error happened...negative mgr_id not allowed here');
    END IF;
    
    -- 如果有錯誤，將錯誤訊息寫入 LOG 表中
    EXCEPTION 
      WHEN OTHERS THEN
      ERR_CODE := SQLCODE;
      ERR_MSG := SUBSTR(SQLERRM, 1, 100);
         INSERT INTO LOG VALUES (ERR_CODE, ERR_MSG, SYSDATE);
    
END GET_DETAILS;
```

### * TRIGGER
#### 觸發器 Trigger 概念描述
觸發器的定義就是說某個條件成立的時候，觸發器裡面所定義的語句就會被自動的執行。因此觸發器不需要人為的去調用，也不能調用。

資料參考： https://www.ruyut.com/2023/05/oracle-trigger.html 、 http://www.aspphp.online/shujuku/oraclesjk/oraclezs/201701/227025.html

#### 序列 SEQUENCE 概念描述
序列(Sequence)是用來生成連續的整數數據的對象，常常用來作為主鍵中增長列，序列中的可以升序生成，也可以降序生成。

* nextval：生成序列的下一個序列號，調用時需要指出序列名，即：序列名.nextval
* currval：用於產生序列的當前值，無論調用多少次都不會產生序列的下一個值。用法同上。

*語法結構：創建序列*
```java
CREATE SEQUENCE sequence_name 
[START WITH num] 
[INCREMENT BY increment] 
[MAXVALUE num|NOMAXVALUE] 
[MINVALUE num|NOMINVALUE] 
[CYCLE|NOCYCLE] 
[CACHE num|NOCACHE]
```

資料參考： https://kknews.cc/zh-tw/code/9gzqypq.html 、 https://www.cnblogs.com/hider/p/12993386.html

*題目說明：*
```java
對 student 表進行操作時：
  ‧ 觸發 trigger ，於 student_log 表中新增操作紀錄。
  ‧ 建立序列 SEQUENCE 作為 log 表的流水號
  ‧ log 表請包含流水號, 狀態(), 建立時間
```
*實作結果：*
```java
-- 建立序列 SEQUENCE --
CREATE SEQUENCE STUDENT_SEQUENCE
START WITH 1
MINVALUE 1
NOMAXVALUE
INCREMENT BY 1
NOCYCLE;

-- 建立觸發 TRIGGER --
    
create or replace TRIGGER STUDENT_TRIGGER
    BEFORE INSERT OR DELETE OR UPDATE
    ON STUDENT
    FOR EACH ROW
DECLARE
    STATE2 STUDENT_LOG.STATE%TYPE;
BEGIN

    IF INSERTING THEN -- 新增
      STATE2 := '新增';
      INSERT INTO STUDENT_LOG (SNO, SNAME, SBDAY, SSEX, SMAIL, SPWD, SEQUENCE_N0, STATE, CREATED_DATE) VALUES (:NEW.SNO, :NEW.SNAME, :NEW.SBDAY, :NEW.SSEX, :NEW.SMAIL, :NEW.SPWD, STUDENT_SEQUENCE.NEXTVAL, STATE2, SYSDATE);

    ELSIF UPDATING THEN -- 更新
      STATE2 := '修改前';
        INSERT INTO STUDENT_LOG (SNO, SNAME, SBDAY, SSEX, SMAIL, SPWD, SEQUENCE_N0, STATE, CREATED_DATE) VALUES (:OLD.SNO, :OLD.SNAME, :OLD.SBDAY, :OLD.SSEX, :OLD.SMAIL, :OLD.SPWD, STUDENT_SEQUENCE.NEXTVAL, STATE2, SYSDATE);

      STATE2 := '修改後';
        INSERT INTO STUDENT_LOG (SNO, SNAME, SBDAY, SSEX, SMAIL, SPWD, SEQUENCE_N0, STATE, CREATED_DATE) VALUES (:NEW.SNO, :NEW.SNAME, :NEW.SBDAY, :NEW.SSEX, :NEW.SMAIL, :NEW.SPWD, STUDENT_SEQUENCE.NEXTVAL, STATE2, SYSDATE);
    
    ELSIF DELETING THEN -- 刪除
     STATE2 := '刪除';
        INSERT INTO STUDENT_LOG (SNO, SNAME, SBDAY, SSEX, SMAIL, SPWD, SEQUENCE_N0, STATE, CREATED_DATE) VALUES (:OLD.SNO, :OLD.SNAME, :OLD.SBDAY, :OLD.SSEX, :OLD.SMAIL, :OLD.SPWD, STUDENT_SEQUENCE.NEXTVAL, STATE2, SYSDATE);
    END IF;

END ;
```


### * 其他練習資源

SQL ZOO: https://sqlzoo.net/wiki/SQL_Tutorial

可以線上練習基礎到進階的 SQL 語法。

---
## JDBC 練習
### * 增查改刪 CRUD
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

---
## Servlet + JDBC 練習 (學生建檔系統)
建立一個 MVC 架構的 web 環境，model 使用上一題作 model 層，view 頁面使用 jsp 製作 、以 Servlet 作為 Controller 連接 model 及 view。
### MVC 概念描述
* M：model 模型，負責和資料庫溝通， Model 管理的功能層被稱做「邏輯層」，更明確一點說，是和「商業邏輯」有關的功能。
* V：view 視圖，View 所管理的功能層叫作「表現層 (presentation layer)」，顧名思義是負責管理畫面的呈現。
* C: controller 控制器，掌握使用者互動邏輯，也是應用程式收發 request/response 的核心。來自路由的 request 會先被送到 Controller，再由 Controller 通知 Model 調度資料，並且把資料傳遞給 View 來產生樣板 (template)，並將呈現資料的 HTML 頁面回傳給客戶端。
![](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696070107768.jpg)
 
參考資料: https://tw.alphacamp.co/blog/mvc-model-view-controller

### Servlet 概念描述
Servlet（Server Applet），全稱Java Servlet。是用Java編寫的伺服器端程式。

其主要功能在於互動式地瀏覽和修改資料，生成動態Web內容。狹義的Servlet是指Java語言實現的一個介面，廣義的Servlet是指任何實現了這個Servlet介面的類別，一般情況下，人們將Servlet理解為後者。

一般在實際項目開發中，都是使用**繼承 HttpServlet 類**的方式去實現 Servlet 程序。

1. 編寫一個類去繼承 HttpServlet 類
2. 根據業務需要重寫 doGet 或 doPost 方法
3. 到 web.xml 中的配置 Servlet 程序的訪問地址

當客戶端發出請求時，調用 service 方法並傳遞一個請求和響應對象。Servlet首先判斷該請求是post還是get操作，然後再調用doPost和doGet方法中的一個，doPost和doGet方法都接收 HttpServletRequest 和 HttpServletResponse。

* 參考資料: http://c.biancheng.net/servlet2/what-is-servlet.html 、
https://medium.com/%E5%B7%A5%E7%A8%8B%E5%B8%AB%E8%AC%9B%E5%8F%A4%E6%99%82%E9%96%93/%E5%B7%A5%E7%A8%8B%E5%B8%AB%E8%AC%9B%E5%8F%A4%E6%99%82%E9%96%93-jsp-servlet-%E5%82%BB%E5%82%BB%E5%88%86%E4%B8%8D%E6%B8%85-f6814b693b54

```java
// 繼承 HttpServlet，改寫 doGet 方法

@WebServlet("/ShowStudentServlet")
public class ShowStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowStudentServlet() {
        super();
    }
    // 改寫 Get 方法，收到前端請求後，調用 Service方法，並回傳從資料庫查詢到的資料到網頁。
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService studentService = new StudentServiceImpl();
		List<Student> studentList = studentService.selectStudent();
		
		request.setAttribute("studentList", studentList);
		request.getRequestDispatcher("showStudent.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}

}
```


```java
// 繼承 HttpServlet，改寫 doPost 方法

@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
        // 改寫 Post 方法，收到前端請求後，透過從前端獲得的資料，調用 Service 方法，並回傳從資料庫查詢到的資料到網頁。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insert = request.getParameter("insertNum");
		int insertNum = 0;
		
		if(insert != null) {
			insertNum = Integer.valueOf(insert);
		}
		
		StudentService studentService = new StudentServiceImpl();
		List<Integer> resultList = studentService.insertStudent(insertNum);
		
		request.setAttribute("success", "成功" + resultList.get(0) + "筆");
		request.setAttribute("fail", "失敗" + resultList.get(1) + "筆");
		request.setAttribute("resultButton", "查詢學生資料");
		request.getRequestDispatcher("insertStudent.jsp").forward(request, response);	
	}
}
```

### 專案說明
實現上萬筆隨機產生學生資料建檔，並可查詢建立的學生資料、顯示於網頁上面。

* jsp 參考: https://www.runoob.com/jsp/jsp-jstl.html
* javaMail 參考: https://www.cnblogs.com/h--d/p/6139509.html

**1. index.jsp (首頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696069162806_0.jpg)

**2. insertStudent.jsp (輸入數字前)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696069186117_0.jpg)

**2. insertStudent.jsp (輸入數字後)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696069202065_0.jpg)

**3. showStudent.jsp (顯示資料庫已建立的資料)**
![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696069221027_0.jpg)

---
## Spring MVC + JDBC 練習 (會員系統)
建立一個 MVC 架構的 web 環境，model 使用同樣的 model 層，view 頁面使用 jsp 製作 、以 Spring MVC 作為 Controller 連接 model 及 view。



### Spring MVC 概念描述
在 Spring 的 Web MVC 框架中，擔任前端控制器角色的是 org.springframework.web.servlet.DispatcherServlet， DispatcherServlet 負責將客戶的請求分派給對應於請求的控制物件，所以使用 Spring Web MVC 的第一步，就是在 web.xml 中定義 DispatcherServlet。

依下圖運作模式，front Controller會依據使用者請求URL決定處理的Controller，並回傳ModelAndView的資料，接者dispatcher Servlet藉由view Resolver與ModelAndView將頁面顯示；Bean的設定主要基於XML、Java Configuration。

**Spring MVC 的運作方式**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1_TejeUv77UN6ExNQzgpKPsw.webp)

* 建立SpringMVC 專案資料參考: https://dotblogs.com.tw/raylee/2019/04/15/143704#google_vignette
* 其他資料參考: 
https://openhome.cc/Gossip/SpringGossip/FirstSpringMVC.html 、 https://medium.com/%E5%B7%A5%E7%A8%8B%E7%8D%85%E6%97%A5%E5%B8%B8/spring-spring-mvc-spring-boot%E6%AF%94%E8%BC%83-6494107261f3 、 https://hackmd.io/@LeeLo/leelo

*專案功能說明：*
```java
1. 註冊功能
 · Email 需做簡單驗證。
 · 密碼需透過 MD5 加密。
 · 帳號需透過 ajax ，於使用者註冊時判斷帳號是否重複。

2. 登入功能
 · 登入成功後需於頁面顯示使用者資料。

3. 忘記密碼功能
 · 使用者透過輸入帳號，可至註冊的信箱收取忘記密碼的信件(產生亂數密碼給使用者登入)。
 · 使用 javaMail
```

*Controller 層代碼 (連接 Model 和 View)：*
```java
@RestController
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/registerProccess", method=RequestMethod.GET)
	public ModelAndView enterRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userRegister");
		mav.addObject("userRegisterParam", new UserRegisterParam());
		
		return mav;
	}	
	
	@RequestMapping(value="register/user", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userRegisterParam") UserRegisterParam userRegisterParam) {
		ModelAndView mav = null;
		
		User user = userService.register(userRegisterParam);
		
		if(user != null) {
			mav = new ModelAndView("userRegisterSuccess");
			mav.addObject("user", user);
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrong");
			
			mav = new ModelAndView("redirect:/registerProccess", map);
		}
		return mav;
	}
	
	@RequestMapping(value="/loginProccess", method=RequestMethod.GET)
	public ModelAndView enterLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userLogin");
		mav.addObject("userLoginParam", new UserLoginParam());
		
		return mav;
	}
	
	@RequestMapping(value="login/user", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userLoginParam") UserLoginParam userLoginParam) {
		ModelAndView mav = null;
		
		User user = userService.login(userLoginParam);
		
		if(user != null) {
			mav = new ModelAndView("userLoginSuccess");
			mav.addObject("user", user);
		}else {
			Map<String, Object> map = new HashMap<>();
			map.put("message", "wrongPassword");
			
			mav = new ModelAndView("redirect:/loginProccess", map);
		}
		return mav;
    }
	
	@RequestMapping(value="/passwordProccess", method=RequestMethod.GET)
	public ModelAndView enterPasswordForgot(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("userForgotPassword");
		mav.addObject("userForgotPasswordParam", new UserForgotPasswordParam());
		
		return mav;
	}	
	
	@RequestMapping(value="findPassword/user", method=RequestMethod.POST)
	public ModelAndView forgotPassword(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userForgotPasswordParam") UserForgotPasswordParam userForgotPasswordParam) {
		
		ModelAndView mav = new ModelAndView("userForgotPassword");
		mav.addObject("message", "將發送新密碼至信箱，請至註冊信箱確認收件");
		
		userService.sendResetPasswordMail(userForgotPasswordParam);

		return mav;
	}
	
	@RequestMapping("/registerAccount")
    public String registerAccount(String account){
		String message = "";
		
		if(account == null | account == "") {
			message = "帳號為必填欄位，請輸入帳號";
			
		}else {
			User user = userService.checkUserAccount(account);
			
			if(user != null) {
				message = "此帳號已被註冊，請輸入新帳號";
			}else {
				message = "OK";
			}
		}
		return message;
    }
	
}	
```

**1. index.jsp (首頁)**
![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697078959529.jpg)

**2-1. userLogin.jsp (登入頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697078978179.jpg)

**2-2. userLoginSuccess.jsp (登入後會員資料顯示頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079236054.jpg)

**3-1. userForgotPassword.jsp (忘記密碼頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079009921.jpg)

**3-2. userForgotPassword.jsp (忘記密碼信件)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079095254.jpg)

**4-1. userRegister.jsp (註冊頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079153184.jpg)

**4-2. userRegisterSuccess.jsp (註冊成功頁)**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079206587.jpg)

**5. 專案結構圖**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1697079308256.jpg)

---
## Spring MVC + Hibernate 練習 (會員系統)
依續上題，將 UserDaoImpl 改為透過 Hibernate 來進行資料的增刪改查操作。

### Hibernate 概念描述
Hibernate 是「物件/關係對應」（Object/Relational Mapping）的解決方案，簡寫為ORM，簡單的說就是將 Java 中的物件與物件關係，映射至關聯式資料庫中的表格與表格之間的關係， Hibernate 提供了這個過程中自動對應轉換的方案。

* Hibernate 操作參考: http://c.biancheng.net/hibernate/crud.html 、 https://openhome.cc/Gossip/HibernateGossip/

*專案功能說明：*
```java
1. 註冊功能
 · Email 需做簡單驗證。
 · 密碼需透過 MD5 加密。
 · 帳號需透過 ajax ，於使用者註冊時判斷帳號是否重複。

2. 登入功能
 · 登入成功後需於頁面顯示使用者資料。

3. 忘記密碼功能
 · 使用者透過輸入帳號，可至註冊的信箱收取忘記密碼的信件(產生亂數密碼給使用者登入)。
 · 使用 javaMail
 
4. 刪除帳號功能 (New)
 · 使用者點擊刪除按鈕後，即可刪除該使用者的註冊資料。
```
Hibernate 操作範例：
```java
// Hibernate 的新增方法示例

public User createUser(RegisterParam registerParam) {
	    // 加載 Hibernate 核心配置文件
	    Configuration configuration = new Configuration().configure();
	    // 創建 SessionFactory 用來獲取 Session 連接對象
	    SessionFactory sessionFactory = configuration.buildSessionFactory();
	    // 獲取 session 連接對象
	    Session session = sessionFactory.openSession();
	    // 開始事務
	    Transaction transaction = session.beginTransaction();
	    // 創建實體類對象
	    User user = new User();
	    user.setAccount(registerParam.getAccount());
	    user.setPassword(registerParam.getPassword());
	    user.setName(registerParam.getName());
	    user.setEmail(registerParam.getEmail());
	    user.setBirthday(registerParam.getBirthday());
	    user.setSex(registerParam.getSex());
	    user.setCreatedDate(new Date());
	    
	    // 插入數據,返回值為新增數據的主鍵 id
	    Serializable save = session.save(user);
	    
	    // 提交事務
	    transaction.commit();
	    // 釋放資源
	    session.close();
	    sessionFactory.close();
	    
	    return user;
	}
```

---
## Struts2 + Spring MVC + Hibernate 練習 (會員系統)
依續上題，改為透過 Struts2 作為 Controller 來連接 model 及 view。

### Struts2 概念描述
Struts2是一個基於MVC設計模式的Web應用框架，它本質上相當於一個servlet，在MVC設計模式中，Struts2作為控制器(Controller)來建立模型與視圖的數據交互。

**用戶請求的生命週期在Struts 2如下所示：**
1. 用戶的請求發送到服務器，用於請求某些資源（即頁面）。
1. 該過濾器調度的要求和確定適當的動作。
1. 配置攔截器的功能，適用於如驗證、文件上傳等。
1. 選擇的動作執行，執行所請求的操作。
1. 同樣，配置攔截器做任何後期處理，如果需要的話。
1. 最後的結果由視圖準備，並且將結果返回給用戶。

* Struts2 操作參考: https://www.yiibai.com/struts_2/struts-2-hello-world-annotation-example.html 、 https://blog.csdn.net/qq1515312832/article/details/85253488 、 http://www.mi1k7ea.com/2020/06/27/Struts2%E5%9F%BA%E7%A1%80%E7%AF%87%E4%B9%8B%E5%9F%BA%E6%9C%AC%E6%A6%82%E5%BF%B5/

*專案功能說明：*
```java
1. 註冊功能
 · Email 需做簡單驗證。
 · 密碼需透過 MD5 加密。
 · 帳號需透過 ajax ，於使用者註冊時判斷帳號是否重複。

2. 登入功能
 · 登入成功後需於頁面顯示使用者資料。

3. 忘記密碼功能
 · 使用者透過輸入帳號，可至註冊的信箱收取忘記密碼的信件(產生亂數密碼給使用者登入)。
 · 使用 javaMail
 · 登入後可再點擊「重設密碼」按鈕，重新設定密碼(New)。
 
4. 刪除帳號功能
 · 使用者點擊刪除按鈕後，於刪除帳號頁面需再次輸入使用者帳號，以確認刪除該使用者的註冊資料。
```

Struts2 操作範例：
1. 登入頁面操作範例

*login.jsp*
```java
<form action="LoginAction" >
    <h1>會員登入頁面</h1>
    
    帳號：<input type="text" name="loginParam.account" placeHolder="請輸入帳號" />
    
    <p>密碼：<input type="password" name="loginParam.password" placeHolder="請輸入密碼"/>
    
    <s:if test="message == 'wrong'">
    <p><font color="red">密碼輸入錯誤，請再次登入</font>
    </s:if>
    
    <p><button>登入</button>
</form>

```
*LoginAction.java*
```java
public class LoginAction extends ActionSupport {
    @Autowired
    UserService userService;
    
    LoginParam loginParam;
    
    public LoginParam getLoginParam() {
        return loginParam;
    }
    
    public void setLoginParam(LoginParam loginParam) {
        this.loginParam = loginParam;
    }
    
    User user;
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    String message;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String execute(){
        if(loginParam == null) {
            return "login";
        }
		
    	user = userService.login(loginParam);
    	
        if(user != null){
            return "success";
        }else{
            setMessage("wrong");
            return "error";
        }    
    }
}
```

*struts.xml*
```java
<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN"
        "http://struts.apache.org/dtds/struts-2.5.dtd">
<struts>
    
    <constant name="struts.enable.DynamicMethodInvocation" value="true"/>
    <constant name="struts.devMode" value="true"/>
    
    <package name="default" namespace="/" extends="struts-default">
    
    <global-allowed-methods>regex:.*</global-allowed-methods>
    
        <action name="LoginAction" class="userSystem.action.LoginAction">

            <result name="login">login.jsp</result>
            <result name="success">/WEB-INF/view/loginSuccess.jsp</result>
            <result name="error">login.jsp</result>
                <param name="message"></param>
    
        </action>
    
    </package>
    
</struts>

<!--配置參考: https://blog.csdn.net/pan_junbiao/article/details/102658194  -->
<!-- struts2中实现返回json格式请求: https://blog.csdn.net/feinifi/article/details/81114268 -->

```

* 操作參考: https://www.itread01.com/articles/1495138759.html 、  https://tw.gitbook.net/struts_2/struts-2-hello-world-example.html
* ajax操作參考(如何使用 Struts2 回傳 json 數據): https://blog.csdn.net/feinifi/article/details/81114268

---
## Spring Boot + JPA + Vue3 練習 (會員系統)
依續上題，將 UserDao 改為透過 JPA 來進行資料的增刪改查操作，view 頁面改為使用 Vue 製作 、改為以 Spring Boot 作為 Controller 連接 model 及 view。

### JPA 概念描述