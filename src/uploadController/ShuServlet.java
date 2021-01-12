package uploadController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ShuServlet
 */
@WebServlet("/fileupload")
public class ShuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", "アップロードするテキストファイルを指定してください");

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/fileupload.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dataDir = "C:\\Users\\shu sagara\\shu\\uploadController\\uploadPlace";		//アップロード先の指定
		String message = "アップロードするテキストファイルを指定してください";
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory = new DiskFileItemFactory();		//ファイルの保存などが可能
			ServletFileUpload uploader = new ServletFileUpload(factory);	//アップロードされたファイルデータの取得
			uploader.setFileSizeMax(1024*1024);	//1MB
			try{
				 List<FileItem> fList = uploader.parseRequest(request);
				for(FileItem fItem : fList){
					if(!fItem.isFormField()){
						File tmpFile = new File(fItem.getName());
						String fileName = tmpFile.getName();
						if(fileNameCheck(fileName)){
							File file = new File(dataDir + File.separator + fileName);
							fItem.write(file);
							message = "テキストファイル「" + fileName + "」をアップロードしました。";
						}else{
							message = "テキストファイルを正しく指定してください";
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "テキストファイルをアップロードできませんでした。";
			}
		}else{
			message = "formタグのenctype属性にmultipart/form-dataを指定してください。";
		}
		request.setAttribute("message", message);

		//jspへの転送
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/fileupload.jsp");
		dispatcher.forward(request, response);
	}

	private boolean fileNameCheck(String fileName){
		String regex = "^\\S+\\.jpg$";		//正規表現の利用
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}
}