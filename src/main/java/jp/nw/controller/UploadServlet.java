package jp.nw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jp.nw.base.BaseModel;
import jp.nw.fileUpload.FileUploadLogic;


/**
 * Servlet implementation class Login
 */
@WebServlet("/UploadServlet")
@MultipartConfig(location="/Users/tominagakouhei/Desktop/tmp/uploadFile", maxFileSize=1000000, maxRequestSize=1000000, fileSizeThreshold=1000000)
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BaseModel baseModel = null;

	private String fileName = "";

    /**
     * @see HttpServlet#
     *
     *
     * HttpServlet()
     */
    public UploadServlet() {
        super();
        baseModel = new BaseModel();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// UploadFile Info
		ServletOutputStream fileInfo = null;

		// FileUpload_Logic
		uloadFileRead(request);

		// FileUpload Action
		FileUploadLogic fileUpload = new FileUploadLogic();
		fileUpload.fileUpload(request, response, this.fileName);

		HttpSession session = request.getSession();
		session.setAttribute("UPLOADFILE", session);
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/appLogic/upload.jsp");
		dispatcher.forward(request, response);
	}

	private void uloadFileRead(HttpServletRequest request) throws IOException, ServletException {
		Part part = request.getPart("FILE_INFO");
        String name = this.getFileName(part);
        this.fileName = "/Users/tominagakouhei/Desktop/tmp/uploadFile/" + name;
        part.write("/Users/tominagakouhei/Desktop/tmp/uploadFile/" + name);
	}
	/**
	 *
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
