package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectSample2 {

    public static void main(String[] args) {
        //データベース接続と結果取得のための変数
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            //1,ドライバーのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/world?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "RnKynKn95216"
                );
            // 3. DBとやりとりする窓口（Statementオブジェクト）の作成
            stmt = con.createStatement();
            // 4, 5. Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM country LIMIT 50";
            rs = stmt.executeQuery(sql);
            // 6. 結果を表示する
            while(rs.next()) {
                //Name列の値を取得
                String name = rs.getString("Name");
                //Population列の値を取得
                int population = rs.getInt("Population");
                //取得した値を表示
                System.out.println(name);
                System.out.println(population);
            }
            
            //6-1データの更新を行う
            sql = "update country set Population = 105000 where Code = 'ABW'";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
            
            // 7. 接続を閉じる
        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }finally {
            //接続を閉じる
            if(rs != null) {
                try {
                    rs.close();
                }catch(SQLException e) {
                    System.out.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                } catch(SQLException e) {
                    System.err.println("Statementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if(con !=null) {
                try {
                    con.close();
                }catch (SQLException e) {
                    System.out.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }         
        }
    }
}

