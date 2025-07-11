public class LibrarySQL{
    /**
     * Tutte le operazioni di SQL/JDBC per le operazioni sulle librerie
     *
     *
     * @author Lorenzo Beretta
     * @see com.bookrecommender.model.Book
     * @see com.bookrecommender.controller.BookController
     */
    public static libreria getAllLibraries(String id) {
        try {
            Connection conn = DriverManager.getConnection(DbInfo.url, DbInfo.user, DbInfo.pass);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Libreria WHERE UserId = ?");
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new libreria(
                    rs.getString("IdLibreria"),
                    rs.getString("UserId")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static libreria




}
