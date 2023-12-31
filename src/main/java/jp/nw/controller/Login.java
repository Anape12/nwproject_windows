package jp.nw.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import jp.nw.base.BaseModel;
import jp.nw.model.LoginLogic;
import jp.nw.model.User;
import jp.nw.parts.DaoPart;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BaseModel baseModel = null;

    /**
     * @see HttpServlet#
     *
     *
     * HttpServlet()
     */
    public Login() {
        super();
        this.baseModel = new BaseModel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// パラメータ取得処理
		Map<String,Object> loginParam = this.baseModel.getParameter(request, response);
		String name = (String)loginParam.get("userId");
		String pass = (String)loginParam.get("password");

		// ID/password取得クラス
		User user = new User(name,pass);

		// SQL情報Map
		Map<String,Object> sqlInfo = new HashMap<String,Object>();
		//SELECT情報格納
		List<String> colum = new ArrayList<>();
		//WHERE情報格納
		List<String> sInfo = new ArrayList<>();
		//SELECT情報格納
		colum.add("password");
		colum.add("permission");
		sqlInfo.put(DaoPart.KOMOKU_INFO.SELECT_INFO,colum);
		//WHERE情報格納
		sInfo.add("name");
		sqlInfo.put(DaoPart.KOMOKU_INFO.WHERE_INFO,sInfo);
		//Table Name
		String table = "users_info";

		LoginLogic loginLogic = new LoginLogic();
		Map<String,Object> isLogin = loginLogic.execute(user, sqlInfo,table);

		// SQL実行結果を取得
		boolean result = (boolean)isLogin.get("result");
		// 権限レベルを取得
		String level = (String) isLogin.get("permission");

		// ログイン後の遷移先画面を選択
		if(result) {
			if(level.equals("1")) {
				this.baseModel.writeInfo("ログイン成功（管理者）");
				// ログイン成功（管理者画面）
				HttpSession session = request.getSession();
				session.setAttribute("loginUser",user);
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/Menu/perMenu.jsp");
				dispatcher.forward(request, response);
			} else {
				this.baseModel.writeInfo("ログイン成功（一般）");
				// ログイン成功（一般）
				HttpSession session = request.getSession();
				session.setAttribute("loginUser",user);
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("/WEB-INF/jsp/Menu/genMenu.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			this.baseModel.writeInfo("ログイン失敗");
			// ログイン失敗
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/WEB-INF/jsp/login/loginMiss.jsp");
			dispatcher.forward(request, response);
		}
	}
}
