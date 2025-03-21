package world01;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FishList {
    // ローカルMySQLデータベースを指定
    private static final String DB_URL = "jdbc:mysql://localhost:3306/fishlist";
    private static final String USER = "user001"; // XAMPPのデフォルトユーザー
    private static final String PASSWORD = "WCeHYNy]A[lbRZU5"; // XAMPPのデフォルトパスワード（空）

    public FishList() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            // テーブルが存在しない場合に作成
            String sql = "CREATE TABLE IF NOT EXISTS fish (" +
                         "fishNO INTEGER PRIMARY KEY AUTO_INCREMENT," +
                         "fishID INTEGER NOT NULL," +
                         "fishNAME TEXT NOT NULL)";
            stmt.execute(sql);
            System.out.println("Connected to local database: fishlist");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public void addFish(Fish fish) {
        String sql = "INSERT INTO fish (fishID, fishNAME) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, fish.getFishID());
            pstmt.setString(2, fish.getFishNAME());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int fishNO = rs.getInt(1);
                System.out.println("Added to database: Fish #" + fishNO + ": " + fish.getFishNAME());
            }
        } catch (SQLException e) {
            System.out.println("Failed to add fish: " + e.getMessage());
        }
    }

    public List<Fish> getAllFish() {
        List<Fish> fishList = new ArrayList<>();
        String sql = "SELECT fishID, fishNAME, fishNO FROM fish";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int fishID = rs.getInt("fishID");
                String fishNAME = rs.getString("fishNAME");
                int fishNO = rs.getInt("fishNO");
                fishList.add(new Fish(fishID, fishNAME, fishNO));
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve fish list: " + e.getMessage());
        }
        return fishList;
    }
}