package webService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import things.Info;
import things.UserX;

/**
 * Servlet implementation class ReturnContantList
 */
@WebServlet("/ReturnContantList")
public class ReturnContantList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnContantList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		List<UserX> infoList = new ArrayList<UserX>();
		BufferedWriter bufferedWriter;
		String class_id;
		String jsonBack;
		InputStream in;
		OutputStream out;
		response.setContentType("text/html;charset:utf-8");
		//读取客户端数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		class_id = bufferedReader.readLine();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
