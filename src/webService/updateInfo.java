package webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.DBManager;

/**
 * Servlet implementation class updateInfo
 */
@WebServlet("/updateInfo")
public class updateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInfo() {
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
	    bufferedReader = new BufferedReader(new InputStreamReader(in,"GBK"));
	    String line,data;
	    StringBuilder stringBuilder = new StringBuilder();
	    while((line=bufferedReader.readLine())!=null) {
	        stringBuilder.append(line);
	    }
	    data = stringBuilder.toString();
	    System.out.println(data);
	    String datas[] = data.split("/");
	    String class_id = datas[0],info_id = datas[1],title = datas[2],content = datas[3];
	    //���ݿ�����
	    DBManager dbManager = DBManager.createInstance();
	    dbManager.initDB();
	    dbManager.connectDB("Super", "1097300052dz");
	    String sql ="update info set Info_title='"+title+"',Info_content='"+content+"' where Info_id"
	    		+ "='"+info_id+"';";
	    printWriter = response.getWriter();
	    try {
	    	dbManager.executeUpdate(sql);
	    	printWriter.write("1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter.write("0");
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
