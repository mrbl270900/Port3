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

    public void connectToStudentData() throws SQLException {
        conn= DriverManager.getConnection(url);
    }

    public void closeStudentDataConnection() throws  SQLException{
        if(conn!= null)
            conn.close();
    }

    public void CreateStatement() throws SQLException{
        this.stmt=conn.createStatement();
    }

    public void PstmtAVGGradeFromStudentID(String StudentID) throws SQLException {
        String sql="SELECT AVG(Grade) as Grade FROM StudentsCourses WHERE StudentID = ?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, StudentID);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            String AVGGrade = rs.getString("Grade");
            System.out.println(AVGGrade);
        }
    }

    public void PstmtGetCoursesAndGradesFromStudentID(String StudentID) throws SQLException {
        String sql="SELECT AVG(Grade) as Grade FROM StudentsCourses WHERE StudentID = ?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, StudentID);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            String AVGGrade = rs.getString("Grade");
            System.out.println(AVGGrade);
        }
    }
}
