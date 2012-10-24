package eng.utah.edu.bookface;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class Posts {
	
	@SerializedName("PostID")
	public int PostID;
	
	@SerializedName("StudentID")
	public int StudentID;
	
	@SerializedName("StudentName")
	public String StudentName;
	
	@SerializedName("Text")
	public String Text;
	
	@SerializedName("Success")
	public Boolean Success;
	
	@SerializedName("Message")
	public String Message;
	
	@SerializedName("CommentID")
	public int CommentID;

	@SerializedName("CreateDate")
	public Date CreateDate;
	
	@SerializedName("ModDate")
	public Date ModDate;
	
	@SerializedName("CommentCount")
	public int CommentCount;
	
	/**
	 * Posts constructor that represents a Boolean return for post deletion.
	 * @param Success
	 * @param Message
	 */
	public Posts(Boolean Success, String Message)
	{
		this.Success = Success;
		this.Message = Message;
	}
	
	/**
	 * Posts constructor that represents a comment creation (/Posts/Create) call. 
	 * @param Success
	 * @param PostID
	 * @param CommentID
	 * @param Message
	 */
	public Posts(Boolean Success, int PostID, int CommentID, String Message){
		this.Success = Success;
		this.PostID = PostID;
		this.CommentID = CommentID;
		this.Message = Message;
	}
	
	/**
	 * Posts constructor that represents a comment list (/Posts/List) and Get call.
	 * @param CommentCount
	 * @param PostID
	 * @param StudentID
	 * @param StudentName
	 * @param Text
	 * @param CreateDate
	 * @param ModDate
	 */
	public Posts(int PostID, int StudentID, String StudentName, String Text, Date CreateDate, Date ModDate, int CommentCount)
	{
		this.CommentCount= CommentCount;
		this.PostID = PostID;
		this.StudentID = StudentID;
		this.StudentName = StudentName;
		this.Text = Text;
		this.CreateDate = CreateDate;
		this.ModDate = ModDate;
	}
	
	/**
	 * Posts constructor that represents a comment edit (/Posts/Edit) call.
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
	public Posts(Boolean Success, String Message, int CommentCount, int PostID, int StudentID, String StudentName, String Text, Date CreateDate, Date ModDate)
	{
		this.Success = Success;
		this.Message = Message;
		this.CommentCount = CommentCount;
		this.PostID = PostID;
		this.StudentID = StudentID;
		this.StudentName = StudentName;
		this.Text = Text;
		this.CreateDate = CreateDate;
		this.ModDate = ModDate;
	}
}
