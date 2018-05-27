package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import DataBase.DBManager;

/**
 * Servlet implementation class ReturnUnLookPeople
 */
@WebServlet("/ReturnUnLookPeople")
public class ReturnUnLookPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnUnLookPeople() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
	    BufferedReader bufferedReader;
	    PrintWriter printWriter;
	    InputStream in = request.getInputStream();
	    bufferedReader = new BufferedReader(new InputStreamReader(in));
	    String info_id = bufferedReader.readLine();
	    
	    //数据库连接
	    DBManager db = DBManager.createInstance();
	    db.initDB();
	    db.connectDB("Super", "1097300052dz");
	    String sql = "{call unLooked_select(?)}";
	    StringBuilder stringBuilder = new StringBuilder();
	    printWriter = response.getWriter();
	    try {
			CallableStatement proc = db.conn.prepareCall(sql);
			proc.setString(1, info_id);
			ResultSet resultSet = proc.executeQuery();
			while(resultSet.next()) {
				if(resultSet.getString("User_name")!=null&&!resultSet.getString("User_name").equals("")) {
					stringBuilder.append(resultSet.getString("User_name")+" ");	
					stringBuilder.append(resultSet.getString("User_phone")+"/");
				}
			}
			int length = stringBuilder.toString().length();			
			String data = stringBuilder.toString().substring(0,length-1);
			System.out.println(data);
			printWriter.write(data);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("0");
		}
	    in.close();
	    printWriter.close();
	    bufferedReader.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
