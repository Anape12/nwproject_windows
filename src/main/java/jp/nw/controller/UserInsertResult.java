package jp.nw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import jp.nw.model.UpdateLogic;
import jp.nw.model.User;
import jp.nw.model.UserInsertLogic;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserInsertResult")
public class UserInsertResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInsertResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		String userBirth = request.getParameter("userBirth");
		String userPermiss = request.getParameter("userPermis");
		int figPermis = Integer.parseInt(userPermiss);
		User user = new User(userId,userPass,userBirth,figPermis);
		// 新規ユーザ－登録処理
		UserInsertLogic ul = new UserInsertLogic();
		boolean flg = ul.insertProcess(user);

		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("WEB-INF/jsp/RegUser/userInsertConf.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
