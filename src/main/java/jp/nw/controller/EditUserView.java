package jp.nw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.nw.model.User;
import jp.nw.model.UserViewLogic;


/**
 * Servlet implementation class UserView
 */
@WebServlet("/EditUserView")
public class EditUserView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> postMap;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserView() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 受け渡しパラメータの取得
		String param = request.getQueryString();
		// パラメータ区切り処理
		String[] params = param.split("&");
		Map<String, String> map = new HashMap<String, String>();
		String[] parameter = new String[2];
		for(int i=0; i < params.length; i++) {
			String check = params[i];
			parameter = check.split("=");
			map.put(parameter[0], parameter[1]);
		}
		// グローバルMapにユーザー情報保持
		postMap = new HashMap<>(map);
		// 各ユーザー情報の取得
		String userId = map.get("userId");
		String userpass = map.get("userPass");
		String userperm = map.get("userPerm");
		UserViewLogic userInfo = new UserViewLogic();
		boolean userFlg = userInfo.userInfoCheck(userId, userpass, userperm);
		if(!userFlg) {
			out.print("0");
		} else {
			out.print("1");
			UserViewLogic userview = new UserViewLogic();
			boolean checkAfter = userview.userInfoUpdate(map);
			if(checkAfter) {
				System.out.println("Succsess");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Schedule/result.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Error.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=Shift_JIS");
		// 編集されたユーザ情報（ID,パスワード,権限レベル）を取得
		String nowUserId = postMap.get("nowId");
		String userId = postMap.get("userId");
		String userPass = postMap.get("userPass");
		String userPermission = postMap.get("userPerm");

		// ユーザー情報編集
		UserViewLogic userview = new UserViewLogic();
		List<User> userList = userview.confirUserInfo(nowUserId,userId,userPass,userPermission);

		if(userList.size() == 0) {
			// エラー処理もしくはエラー画面を導入予定
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Error.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("userList",userList);

			HttpSession session = request.getSession();
			User loginUser = (User)session.getAttribute("loginUser");

			if(loginUser == null) {
				response.sendRedirect("/nwproject_B/");
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userList.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
