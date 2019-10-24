package me.acmd.release.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.util.Map;
import me.acmd.release.kits.FileKit;
import me.acmd.release.servlets.Scanner;

/**
 * Servlet implementation class Scanner
 */
@WebServlet({ "/Scanner", "/scan" })
public class Scanner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Scanner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<File, Long> converted = FileKit.scanConverted();
	    JsonArray filelist = new JsonArray();
	    for (Map.Entry<File, Long> e : converted.entrySet()) {
	      File f = (File)e.getKey();
	      long lastModify = ((Long)e.getValue()).longValue();
	      JsonObject obj = new JsonObject();
	      obj.addProperty("filename", f.getName());
	      obj.addProperty("lastModify", Long.valueOf(lastModify));
	      filelist.add(obj);
	    } 
	    System.out.println(converted.size());
	    request.setAttribute("contentCount", Integer.valueOf(converted.size()));
	    request.setAttribute("converted", converted);
	    request.getRequestDispatcher("Main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
