package by.kashlyak.bot.traineeTGBot.dbworker;

import java.sql.*;

public class DBWorker {

    private static final String URL = LoggingDB.getURL();
    private static final String USERNAME = LoggingDB.getUSERNAME();
    private static final String PASSWORD = LoggingDB.getPASSWORD();

    private static final String INSERT_CHATID = "insert into personal_information_from_bot(chatId) values(?);";
    private static final String UPDATE_FULLNAME = "UPDATE personal_information_from_bot SET fullName = (?) where chatId=(?);";
    private static final String UPDATE_AGE = "UPDATE personal_information_from_bot set age = (?) where chatId=(?);";
    private static final String UPDATE_ADDRESS = "UPDATE personal_information_from_bot set address = (?) where chatId=(?);";
    private static final String GET_INFO_FROM_ID = "select * from personal_information_from_bot where chatId=(?);";

    static {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void writeChatIdOnDB(long chatId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHATID);
            preparedStatement.setLong(1, chatId);
            try {
                preparedStatement.execute();
            } catch (SQLIntegrityConstraintViolationException e) {
                e.getMessage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeFullNameOnDB(String fullName, long chatId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FULLNAME);
            preparedStatement.setString(1, fullName);
            preparedStatement.setLong(2, chatId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeAgeOnDB(Integer age, long chatId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AGE);
            preparedStatement.setInt(1, age);
            preparedStatement.setLong(2, chatId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeAddressOnDB(String address, long chatId) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1, address);
            preparedStatement.setLong(2, chatId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String personFromDB(long chatId) {
        String personalInformationFromDB = "Данные отсутвуют";
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_INFO_FROM_ID);
            preparedStatement.setLong(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            String fullName = null;
            String address = null;
            Integer age = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                fullName = resultSet.getString("fullName");
                age = (Integer) resultSet.getInt("age");
                address = resultSet.getString("address");
                System.out.println(fullName + age + address);
                if (fullName == null) {
                    personalInformationFromDB = "Данные отсутвуют";
                } else {

                    personalInformationFromDB = "Вас зовут " + fullName + "\nВаш возраст: " + age + "\nАдрес: " + address;
                }
            }

            return personalInformationFromDB;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return personalInformationFromDB;
    }


}
