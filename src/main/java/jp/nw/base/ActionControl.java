package jp.nw.base;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ActionControl {


	protected void actionDoGet(HttpServletRequest request, HttpServletResponse response,
			String screenId, String sqlParam) throws ServletException, IOException {
		// 画面起動
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(screenId);
		dispatcher.forward(request, response);

	}

}
