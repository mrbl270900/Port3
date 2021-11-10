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
        String sql="SELECT D1.StudentID as StudentID, D1.Name as Name,\n" +
                "       D2.CourseID as CourseID, D3.Name as CourseName, D2.Grade as Grade\n" +
                "FROM Students as D1\n" +
                "         JOIN StudentsCourses as D2 ON D1.StudentID=D2.StudentID\n" +
                "         JOIN Courses D3 on D3.CourseID = D2.CourseID\n" +
                "WHERE D1.StudentID= ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, StudentID);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            String AVGGrade = rs.getString("Grade");
            System.out.println(AVGGrade);
        }
    }
}
