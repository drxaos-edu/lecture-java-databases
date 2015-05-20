package ex2stmt;


import java.sql.*;

public class Ex2Stmt {
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
            Statement st = null;
            st = conn.createStatement();
            String sql = "CREATE TABLE TEST " +
                    "(id bigint auto_increment, " +
                    " name varchar(255), " +
                    " PRIMARY KEY ( id ))";
            st.executeUpdate(sql);
            st.execute("INSERT INTO TEST VALUES(default,'HELLO')");
            st.execute("INSERT INTO TEST(NAME) VALUES('JOHN')");
            String name1 = "Jack";
            String q = "insert into TEST(name) values(?)";
            PreparedStatement st1 = null;

            st1 = conn.prepareStatement(q);
            st1.setString(1, name1);
            st1.execute();

            ResultSet result;
            result = st.executeQuery("SELECT * FROM TEST");
            ResultSetMetaData metaData = result.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.println(metaData.getColumnName(i) + ": " + metaData.getColumnTypeName(i));
            }
            while (result.next()) {
                String name = result.getString("NAME");
                System.out.println(result.getString("ID") + " " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
