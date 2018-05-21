package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;

/**
 * Servlet implementation class publishInfoWebService
 */
@WebServlet("/publishInfoWebService")
public class publishInfoWebService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public publishInfoWebService() {
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
	    String line,data;
	    StringBuilder stringBuilder = new StringBuilder();
	    while((line=bufferedReader.readLine())!=null) {
	        stringBuilder.append(line);
	    }
	    data = stringBuilder.toString();
	    String datas[] = data.split("/");
	    String class_id = datas[0],author = datas[1],title = datas[1],content = datas[2];
	    //数据库连接
	    DBManager dbManager = DBManager.createInstance();
	    dbManager.initDB();
	    dbManager.connectDB("Super", "1097300052dz");
	    String sql = "{call publish_Info(?,?,?,?)}";
	    printWriter = response.getWriter();
	    try {
	    	CallableStatement proc = dbManager.conn.prepareCall(sql);
			proc.setString(1, author);
			proc.setString(2, class_id);
		    proc.setString(3, title);
		    proc.setString(4, content);
		    proc.executeQuery();
		    printWriter.write("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("0");
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
