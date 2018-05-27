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

import com.sun.org.apache.xml.internal.security.utils.Base64;

import DataBase.DBManager;
import json.test.GsonUnit;
import things.Info;
import things.UserX;

/**
 * Servlet implementation class PerfectUserXservice
 */
@WebServlet("/PerfectUserXservice")
public class PerfectUserXservice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfectUserXservice() {
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
		String line,jsonFrom;
		StringBuilder stringBuilder = new StringBuilder();
		InputStream in;
		OutputStream out;
		response.setContentType("text/html;charset:utf-8");
		//读取客户端数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		while((line=bufferedReader.readLine())!=null) {
			stringBuilder.append(line);
		}
		
		jsonFrom = stringBuilder.toString();
		System.out.println(jsonFrom);
		UserX userX = GsonUnit.jsonToJavaBean(jsonFrom, UserX.class);
		String Username = userX.getUsername();
		String User_name = userX.getUser_name();
		String User_sex = userX.getUser_sex().equals("man")?"男":"女";
		String User_phone = userX.getUser_phone();
		String User_address = userX.getUser_address();
		String User_QQ = userX.getUser_QQ();
		String User_birth = userX.getBirth();
		//数据库操作
		DBManager dbManager = DBManager.createInstance();
		dbManager.initDB();
		dbManager.connectDB("Super", "1097300052dz");
		String sql = "update userx set User_name = '"+User_name+"',"
				+ "User_sex = '"+User_sex+"',"
				+ "User_phone = '"+User_phone+"',"
				+ "User_QQ = '"+User_QQ+"',"
				+ "User_address = '"+User_address+"',"
				+ "User_birth = '"+User_birth+"'"
				+"where Username='"+Username+"';";
		int result = dbManager.executeUpdate(sql);
		String sql2 ="update user set firstLogin = 1 where Username ='"+Username+"'";
		dbManager.executeUpdate(sql2);
		printWriter = response.getWriter();
		//返回客户端
		if(result>0) {
			printWriter.write("success");
		}
		else{
			printWriter.write("fault");
		}
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
