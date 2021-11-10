import java.sql.*;

public class StudentModel {
    Connection conn=null;
    String url=null;
    Statement stmt=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;

    StudentModel(String url){
        this.url=url;
    }

    public void connectToTrainData() throws SQLException {
        conn= DriverManager.getConnection(url);
    }
    public void closeTrainDataConnection() throws  SQLException{
        if(conn!= null)
            conn.close();
    }
    public void CreateStatement() throws SQLException{
        this.stmt=conn.createStatement();
    }



}
