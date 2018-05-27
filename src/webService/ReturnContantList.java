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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.util.ResultSetUtil;
import com.sun.org.glassfish.external.statistics.annotations.Reset;

import DataBase.DBManager;
import jdk.internal.dynalink.beans.StaticClass;
import json.test.GsonUnit;
import things.Info;
import things.User;
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
		response.setContentType("text/html;charset:GBK");
		response.setCharacterEncoding("gbk");
		//读取客户端数据
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		class_id = bufferedReader.readLine();
		//连接数据库
		DBManager dbManager = DBManager.createInstance();
		dbManager.initDB();
		dbManager.connectDB("Super", "1097300052dz");
		String sql = "select * from userx where Class_id='"+class_id+"';";
		ResultSet resultSet = dbManager.executeQuery(sql);
		out = response.getOutputStream();
		bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
		try {
			
			while(resultSet.next()) {
				UserX userX;
				if(resultSet.getString("User_name")!=null&&!resultSet.getString("User_name").equals("")) {
				String Username = resultSet.getString("Username");
				String User_name = resultSet.getString("User_name");
				String Class_id = class_id;
				String User_sex = resultSet.getString("User_sex");
				String User_phone = resultSet.getString("User_phone");
				String User_address = resultSet.getString("User_phone");
				String User_QQ = resultSet.getString("User_QQ");
				String User_image = resultSet.getString("User_image");
				String User_birth = resultSet.getString("User_birth");
				userX = new UserX(Username,User_name,Class_id,User_sex,User_phone,User_address,User_QQ,User_image,User_birth);
				infoList.add(userX);
				}
			}
			jsonBack = GsonUnit.listToJson(infoList);	
			System.out.println(jsonBack);
			bufferedWriter.write(jsonBack);
			bufferedWriter.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bufferedWriter.write("");
			System.out.println("出错");
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
