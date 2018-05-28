package webService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;
import json.test.GsonUnit;
import things.UserX;

/**
 * Servlet implementation class ReturnUserX
 */
@WebServlet("/ReturnUserX")
public class ReturnUserX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnUserX() {
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
		
		BufferedWriter bufferedWriter;
		String username;
		String jsonBack;
		InputStream in;
		OutputStream out;
		response.setContentType("text/html;charset:utf-8");
		response.setCharacterEncoding("gbk");
		//读取客户端数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		username = bufferedReader.readLine();
		//连接数据库
		DBManager dbManager = DBManager.createInstance();
		dbManager.initDB();
		dbManager.connectDB("Super", "1097300052dz");
		String sql = "select * from userx where Username='"+username+"';";
		ResultSet resultSet = dbManager.executeQuery(sql);
		out = response.getOutputStream();
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
		try {
			UserX userX = null;
				while(resultSet.next()) {
				
				String Username = username;
				String User_name = resultSet.getString("User_name");
				String Class_id = resultSet.getString("Class_id");
				String User_sex = resultSet.getString("User_sex");
				String User_phone = resultSet.getString("User_phone");
				String User_address = resultSet.getString("User_address");
				String User_QQ = resultSet.getString("User_QQ");
				String User_image = resultSet.getString("User_image");
				String User_birth = resultSet.getString("User_birth");
				userX = new UserX(Username,User_name,Class_id,User_sex,User_phone,User_address,User_QQ,User_image,User_birth);
				}
			
			jsonBack = GsonUnit.javabeanToJson(userX);	
			System.out.println(jsonBack);
			bufferedWriter.write(jsonBack);
			bufferedWriter.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bufferedWriter.write("");
		}finally {
			out.close();
			bufferedReader.close();
			bufferedWriter.close();
			in.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
