package webService;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

/**
 * Servlet implementation class Refresh
 */

public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Refresh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setIntHeader("Refresh", 1);
		response.setContentType("text/html;charset=UTF-8");
		Calendar cale = Calendar.getInstance();
		Date tasktime = cale.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String notTime = format.format(tasktime);
		PrintWriter out = response.getWriter();
		String path = getServletContext().getRealPath("/");
		Calendar calender = Calendar.getInstance();
		String title = "自动刷新 Header 设置-菜鸟教程实例";
		String docType = 
				"<!doctype html>\n";
		out.println(docType+
				"<html>\n"+
				"<head><title>"+title+"</title><head>\n"+
				"<body bgcolor=\"#f0f0f0\">\n"+
				"<h1 align=\"center\">"+title+"<h1>\n"+
				"<p>当前时间："+notTime+"</p>\n"+
				"<p>"+getServletContext().getRealPath("/")+
				calender.getTime().toString()+"</p>"+
				"<body><html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
