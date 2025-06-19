public class TestDriverLoad {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" JDBC Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println(" JDBC Driver NOT found!");
            e.printStackTrace();
        }
    }
}
