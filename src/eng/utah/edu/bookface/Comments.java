package eng.utah.edu.bookface;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class Comments {
	
	@SerializedName("CadeLogin")
	public String CadeLogin;
	
	@SerializedName("PostID")
	public int PostID;
	
	@SerializedName("Text")
	public String Text;
	
	@SerializedName("Success")
	public Boolean Success;
	
	@SerializedName("Message")
	public String Message;
	
	@SerializedName("CommentID")
	public int CommentID;

	@SerializedName("StudentName")
	public String StudentName;
	
	@SerializedName("CreateDate")
	public Date CreateDate;
	
	@SerializedName("ModDate")
	public Date ModDate;

	@SerializedName("StudentID")
	public int StudentID;
	
	/**
	 * Comments constructor that represents a Boolean return for comment deletion.
	 * @param Success
	 * @param Message
	 */
	public Comments(Boolean Success, String Message)
	{
		this.Success = Success;
		this.Message = Message;
	}
	
	/**
	 * Comments constructor that represents a comment creation (/Comments/Create) call. 
	 * @param Success
	 * @param PostID
	 * @param CommentID
	 * @param Message
	 */
	public Comments(Boolean Success, int PostID, int CommentID, String Message){
		this.Success = Success;
		this.PostID = PostID;
		this.CommentID = CommentID;
		this.Message = Message;
	}
	
	/**
	 * Comments constructor that represents a comment list (/Comments/List) call.
	 * @param CommentID
	 * @param PostID
	 * @param StudentID
	 * @param StudentName
	 * @param Text
	 * @param CreateDate
	 * @param ModDate
	 */
	public Comments(int CommentID, int PostID, int StudentID, String StudentName, String Text, Date CreateDate, Date ModDate)
	{
		this.CommentID = CommentID;
		this.PostID = PostID;
		this.StudentID = StudentID;
		this.StudentName = StudentName;
		this.Text = Text;
		this.CreateDate = CreateDate;
		this.ModDate = ModDate;
	}
	
	/**
	 * Comments constructor that represents a comment edit (/Comments/Edit) call.
	 * @param Success
	 * @param Message
	 * @param CommentID
	 * @param PostID
	 * @param StudentID
	 * @param StudentName
	 * @param Text
	 * @param CreateDate
	 * @param ModDate
	 */
	public Comments(Boolean Success, String Message, int CommentID, int PostID, int StudentID, String StudentName, String Text, Date CreateDate, Date ModDate)
	{
		this.Success = Success;
		this.Message = Message;
		this.CommentID = CommentID;
		this.PostID = PostID;
		this.StudentID = StudentID;
		this.StudentName = StudentName;
		this.Text = Text;
		this.CreateDate = CreateDate;
		this.ModDate = ModDate;
	}
}
