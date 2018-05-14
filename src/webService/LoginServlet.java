package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.DatabaseMetaData;

import DataBase.AES;
import DataBase.DBManager;
import jdk.internal.util.xml.impl.Input;
import json.test.GsonUnit;
import security.MD5;
import things.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter back_print = response.getWriter();
		InputStream in =  request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		String line,jsonDataAS,jsonBack;
		User user1,user2;//user1来用户，user2去用户
		//取得客户端数据
		StringBuilder builder = new StringBuilder();
		while((line=bufferedReader.readLine())!=null) {
			builder.append(line);
		}
		jsonDataAS = builder.toString();
		user1 = GsonUnit.jsonToJavaBean(jsonDataAS, User.class);
		  //数据库数据请求                            	
		String username = user1.getUsername();
		DBManager dbManager = DBManager.createInstance();
	    dbManager.initDB();//数据库初始化
	    dbManager.connectDB("Super","1097300052dz");//登陆数据库
	    String sql = "select Username,"
	        		+ "AES_DECRYPT(password,'1097300052dz') as pass,"
	        		+ "Class_id,"
	        		+ "firstLogin,"
	        		+ "User_power "
	        		+ "from user where Username = '"+username+"';";
	    ResultSet resultSet=dbManager.executeQuery(sql);
	  
			try {
				if(resultSet.next()) {
				String pass = MD5.getMD5(resultSet.getString("pass"));
				String Class_id = resultSet.getString("Class_id");
				int firstLogin = resultSet.getInt("firstLogin");
				int User_Power = resultSet.getInt("User_power");
				user2 = new User(username,MD5.getMD5(pass),User_Power,Class_id,firstLogin);
				jsonBack = GsonUnit.javabeanToJson(user2);
				back_print.write(jsonBack);
			     
				}
				back_print.close();
				dbManager.closeDB();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				  System.out.println("ERROR");
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	}

}
