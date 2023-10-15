package jp.nw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/SelectApp")
public class SelectApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectApp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/html/userupd.html");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// 実行アプリケーション取得
		String appLogic = request.getQueryString();
		String[] params = appLogic.split("=");
		String result = "";

		if(params[1].equals("NC30001")) {
//			NC30001 nc30001 = new NC30001();
//			result = nc30001.initialize();
			result = "NC3 Apploci";
		} else if (params[1].equals("NC40001")) {
//			NC40001 nc40001 = new NC40001();
//			result = nc40001.initialize();
			result = "NC4 Applogic";
		}
		HttpSession session = request.getSession();
		session.setAttribute("resultApp", result);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/appLogic/app.jsp");
		dispatcher.forward(request, response);

	}

}
