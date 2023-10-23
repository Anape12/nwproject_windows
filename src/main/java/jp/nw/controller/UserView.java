package jp.nw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.nw.base.BaseModel;
import jp.nw.model.User;
import jp.nw.model.UserViewLogic;


/**
 * Servlet implementation class UserView
 */
@WebServlet("/UserView")
public class UserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BaseModel logger = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserView() {
        super();
        this.logger = new BaseModel();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ユーザー情報一覧取得処理
		UserViewLogic userview = new UserViewLogic();
		List<User> userList = userview.findAll();
		request.setAttribute("userList",userList);

		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		for(User userinfo : userList) {
			this.logger.writeInfo(userinfo.getName());
		}
		if(loginUser == null) {
			response.sendRedirect("/nwproject/");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/otherUser/userList.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=Shift_JIS");
		// 選択されたユーザーIDを取得
		String userId = request.getParameter("radiobutton");
		// ユーザー情報編集
		UserViewLogic userview = new UserViewLogic();
		List<User> userList = userview.editUserInfo(userId);
		request.setAttribute("userList",userList);

		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");

		if(loginUser == null) {
			response.sendRedirect("/nwproject/");
			request.setCharacterEncoding("UTF-8");
			request.setAttribute("errorMsg","エラー");
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/otherUser/editUserInfo.jsp");
			dispatcher.forward(request, response);
		}
	}

}
