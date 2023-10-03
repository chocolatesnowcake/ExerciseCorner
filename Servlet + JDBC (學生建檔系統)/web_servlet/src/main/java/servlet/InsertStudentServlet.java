package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;
import service.StudentServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
