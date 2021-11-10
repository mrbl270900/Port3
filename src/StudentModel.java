import java.sql.*;
import java.util.ArrayList;

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

    public String PstmtAVGGradeFromStudentName(String Student) throws SQLException {
        String sql="SELECT AVG(Grade) as Grade FROM StudentsCourses as D1 JOIN Students as D2 on D1.StudentID=D2.StudentID WHERE Name = ?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, Student);
        rs=pstmt.executeQuery();
        String AVGGrade = rs.getString("Grade");
        System.out.println(AVGGrade);
        return AVGGrade;
    }

    public String PstmtAVGGradeFromCourse(String Course) throws SQLException {
        String sql="SELECT AVG(D1.Grade) as Grade FROM StudentsCourses as D1 JOIN Courses as D2 on D1.CourseID=D2.CourseID WHERE Name = ?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, Course);
        rs=pstmt.executeQuery();
            String AVGGrade = rs.getString("Grade");
            System.out.println(AVGGrade);
        return AVGGrade;
    }

    public ArrayList<StudentInfo> PstmtGetCoursesAndGradesFromStudentName(String StudentInput) throws SQLException {
        ArrayList<StudentInfo> StudentsInfos = new ArrayList<>();
        String sql="SELECT D1.StudentID as StudentID, D1.Name as Name,\n" +
                "       D2.CourseID as CourseID, D3.Name as CourseName, D2.Grade as Grade\n" +
                "FROM Students as D1\n" +
                "         JOIN StudentsCourses as D2 ON D1.StudentID=D2.StudentID\n" +
                "         JOIN Courses D3 on D3.CourseID = D2.CourseID\n" +
                "WHERE D1.Name= ?;";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, StudentInput);
        rs=pstmt.executeQuery();
        while(rs!=null && rs.next()){
            Integer StudentID = rs.getInt("StudentID");
            String Name = rs.getString("Name");
            Integer CourseID = rs.getInt("CourseID");
            String CourseName = rs.getString("CourseName");
            Integer Grade = rs.getInt("Grade");
            System.out.println("Studen with: ID " + StudentID + " named " + Name + " have attendet " + CourseName + " with courseID " + CourseID + " and have gotten the grade " + Grade);
            StudentInfo I = new StudentInfo(StudentID, Name, CourseID, CourseName, Grade);
            StudentsInfos.add(I);
        }
        return StudentsInfos;
    }
    public ArrayList<String> SQLQueryStudents() throws SQLException{
        ArrayList<String> Students=new ArrayList<>();
        String sql = "Select Name from Students;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String name=rs.getString(1);
            System.out.println(name);
            Students.add(name);
        }
        return Students;
    }
    public ArrayList<String> SQLQueryCourses() throws SQLException{
        ArrayList<String> Courses=new ArrayList<>();
        String sql = "Select Name from Courses;";
        rs=stmt.executeQuery(sql);
        while(rs!=null && rs.next()){
            String name=rs.getString(1);
            System.out.println(name);
            Courses.add(name);
        }
        return Courses;
    }
}

class StudentInfo{
    Integer StudentID;
    String Name;
    Integer CourseID;
    String CourseName;
    Integer Grade;
    StudentInfo(Integer StudentID, String Name, Integer CourseID, String CourseName, Integer Grade){
        this.StudentID = StudentID;
        this.Name = Name;
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Grade = Grade;
    }
}
