package ex1jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Ex1Jdbc {
    public static void main(String[] a) throws Exception {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            if (!conn.isClosed()) {
                System.out.println("Connected!");
            }

        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}
