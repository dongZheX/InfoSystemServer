package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;
import jdk.internal.util.xml.impl.Input;
import sun.awt.image.DataBufferNative;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//变量声明
		PrintWriter printWriter;
		BufferedReader bufferedReader;
		InputStream in;
		String data;
		String param[];
		response.setContentType("text/html;charset:utf-8");
		//读取数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in,"GBK"));
		data = bufferedReader.readLine();
		System.out.println(data);
		param = data.split("/");
		String username = param[0];
		System.out.println(param[0]);
		String old_pass = param[1];
		System.out.println(param[1]);
		String new_pass = param[2];
		System.out.println(param[2]);
		int result = 0;
		//数据库部分存储过程
		 CallableStatement proc=null;
		 printWriter = response.getWriter();
	     String sql="{call update_pass_user(?,?,?,?)}";
	     DBManager dbManager =DBManager.createInstance();
	     dbManager.initDB();
	     dbManager.connectDB("Super", "1097300052dz");
	     try {
	    	 Connection conn  = dbManager.conn;
			proc =  conn.prepareCall(sql);
			proc.setString(1, username);
			proc.setString(2, old_pass);
			proc.setString(3, new_pass);
			proc.setInt(4, result);
			proc.executeQuery();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     printWriter.write(""+1);
	     in.close();
		 printWriter.close();
		 bufferedReader.close();
		 dbManager.closeDB();
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
