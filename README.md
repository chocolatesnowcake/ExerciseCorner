# ExerciseCorner
程式練習

https://hackmd.io/@QAg8aC4wQNm57FIDB0eiOQ/B1M1y4Bla

---
## Java 程式練習
### * 邏輯練習
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
#### MVC 概念描述
* M：model 模型，負責和資料庫溝通， Model 管理的功能層被稱做「邏輯層」，更明確一點說，是和「商業邏輯」有關的功能。
* V：view 視圖，View 所管理的功能層叫作「表現層 (presentation layer)」，顧名思義是負責管理畫面的呈現。
* C: controller 控制器，掌握使用者互動邏輯，也是應用程式收發 request/response 的核心。來自路由的 request 會先被送到 Controller，再由 Controller 通知 Model 調度資料，並且把資料傳遞給 View 來產生樣板 (template)，並將呈現資料的 HTML 頁面回傳給客戶端。
![](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1696070107768.jpg)
 
參考資料: https://tw.alphacamp.co/blog/mvc-model-view-controller

#### Servlet 概念描述
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

#### 專案說明
實現上萬筆隨機產生學生資料建檔，並可查詢建立的學生資料、顯示於網頁上面。

* 參考資料( jsp ): https://www.runoob.com/jsp/jsp-jstl.html

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



#### Spring MVC 概念描述
在 Spring 的 Web MVC 框架中，擔任前端控制器角色的是 org.springframework.web.servlet.DispatcherServlet， DispatcherServlet 負責將客戶的請求分派給對應於請求的控制物件，所以使用 Spring Web MVC 的第一步，就是在 web.xml 中定義 DispatcherServlet。

依下圖運作模式，front Controller會依據使用者請求URL決定處理的Controller，並回傳ModelAndView的資料，接者dispatcher Servlet藉由view Resolver與ModelAndView將頁面顯示；Bean的設定主要基於XML、Java Configuration。

**Spring MVC 的運作方式**

![image](https://github.com/chocolatesnowcake/ExerciseCorner/blob/main/%E5%9C%96%E7%89%87/1_TejeUv77UN6ExNQzgpKPsw.webp)

* 建立SpringMVC 專案資料參考: https://dotblogs.com.tw/raylee/2019/04/15/143704#google_vignette
* 其他資料參考: 
https://openhome.cc/Gossip/SpringGossip/FirstSpringMVC.html 、 https://medium.com/%E5%B7%A5%E7%A8%8B%E7%8D%85%E6%97%A5%E5%B8%B8/spring-spring-mvc-spring-boot%E6%AF%94%E8%BC%83-6494107261f3 、 https://hackmd.io/@LeeLo/leelo

*專案功能說明：*
```
1. 註冊功能
 · Email 需做簡單驗證。
 · 密碼需透過 MD5 加密。
 · 帳號需透過 ajax ，於使用者註冊時判斷帳號是否重複。

2. 登入功能
 · 登入成功後需於頁面顯示使用者資料。

3. 忘記密碼功能
 · 使用者透過輸入帳號，可至註冊的信箱收取忘記密碼的信件(產生亂數密碼給使用者登入)。
 · 使用 java Mail
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
