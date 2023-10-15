package jp.nw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.nw.model.Dairy;
import jp.nw.model.DairyLogic;

/**
 * Servlet implementation class DairyWrite
 */
@WebServlet("/DairyWrite")
public class DairyWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DairyWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/html/dairywrite.html");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String text = request.getParameter("text1");

		Dairy dairy = new Dairy(title, text);

		DairyLogic dylg = new DairyLogic();
		dylg.execute(dairy);

		HttpSession session = request.getSession();
		session.setAttribute("dairy",dairy);
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/html/dairyresult.html");
		dispatcher.forward(request, response);

	}
}
