package jp.nw.fileUpload;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FileUploadLogic extends HttpServlet {

	public FileUploadLogic() {
	}

	public void fileUpload(HttpServletRequest request, HttpServletResponse response, String fileName) throws ServletException, IOException {
		// Java 8以降
		Path file = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(file)) {
			String text = "";
			while ((text = br.readLine()) != null) {
				System.out.println(text);
			}
		}
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
