package webService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
 * Servlet implementation class LookRecordSevrvice
 */
@WebServlet("/LookRecordService")
public class LookRecordService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookRecordService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		//来自客户端的数据
		InputStream in;
		OutputStream out;
		BufferedReader bufferedReader;
		PrintWriter printWriter;
		in = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(in));
		String data = bufferedReader.readLine();
		String datas[] = data.split("/");
		String username = datas[0],info_id = datas[1];
		printWriter = response.getWriter();
		//数据库连接
		DBManager db = DBManager.createInstance();
		db.initDB();
		db.connectDB("Super", "1097300052dz");
		Connection conn = db.conn;
		CallableStatement proc=null;
		String sql = "{call Look_Proc(?,?)}";
		try {
			proc = conn.prepareCall(sql);
			proc.setString(1, username);
			proc.setString(2, info_id);
			proc.executeQuery();
			printWriter.write("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("0");
		}
		in.close();
	    printWriter.close();
	    bufferedReader.close();
	    db.closeDB();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
