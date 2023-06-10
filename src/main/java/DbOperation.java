import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbOperation {
    public static void main(String[] args) {
        //deleteFromDB(7);
//        updateDataDB(8, "77775678-1234-1234-1234-1234567890AB",888);
//        insertToDB(8, "2343546547567", "paid", 444, 567567567);
        List<Data> dataList = getDataFromDB();
        System.out.println("ID       | UUID                                | Type         | Carrier Offer ID | MSISDN");
        System.out.println("---------+-------------------------------------+--------------+------------------+------------");
        for (Data data : dataList) {
            System.out.printf("%-9d| %-36s| %-13s| %-17d| %-12d%n", data.getId(), data.getUuid(), data.getType(), data.getCarrier_offer_id(), data.getMsisdn());
        }
    }

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USER_NAME = "postgres";
    private final static String USER_PASSWORD = "mysecretpassword";
    private final static String QUERY_SELECT_ALL = "select * from operation";
    private final static String QUERY_INSERT = "insert into operation (?,?,?,?,?)";
    private final static String QUERY_UPDATE = "update operation set uuid=?, carrier_offer_id=? where id=?";
    private final static String QUERY_DELETE = "delete from operation where id=?";

    public static List<Data> getDataFromDB() {
        List<Data> dataList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            int rowCount = 0;
            while (resultSet.next()) {
                Data data = new Data(resultSet.getInt("id"), resultSet.getString("uuid"), resultSet.getString("type"), resultSet.getInt("carrier_offer_id"), resultSet.getLong("msisdn"));
                dataList.add(data);
                rowCount++;
            }
            System.out.println("Total rows: " + rowCount);
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
        return dataList;
    }

    static void insertToDB(int id, String uuid, String type, int carrier_offer_id, long msisdn) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_INSERT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, uuid);
            preparedStatement.setString(3, type);
            preparedStatement.setInt(4, carrier_offer_id);
            preparedStatement.setLong(5, msisdn);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));

        }
    }

    public static void updateDataDB(int id, String uuid, int carrier_offer_id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, carrier_offer_id);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));
        }
    }

    public static void deleteFromDB(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, USER_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(String.format("Please check connection string " + "URL [%s]"
                    + " name [%s]" + " pass [%s]", URL, USER_NAME, USER_PASSWORD));

        }
    }
}