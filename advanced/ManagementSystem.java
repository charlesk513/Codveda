import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class ManagementSystem {

    public ManagementSystem() {

    }

    // ADD BOOK
    public void addBook(String title, String author) {

        String sql = """
                INSERT INTO books (title, author, available)
                VALUES (?, ?, true)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, title);
            statement.setString(2, author);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Book added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    // VIEW BOOKS
    public void viewBooks() {

        String sql = """
                SELECT book_id, title, author, available
                FROM books
                ORDER BY book_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\nBOOKS");
            System.out.println("----------------------------------------");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                boolean available = resultSet.getBoolean("available");

                System.out.println(
                        id + " | "
                                + title + " | "
                                + author + " | Available: "
                                + available);
            }

            if (!found) {
                System.out.println("No books found.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving books: " + e.getMessage());
        }
    }

    // ADD USER
    public void addUser(String name, String email, String phone) {

        String sql = """
                INSERT INTO users (name, email, phone)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, phone);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("User added successfully.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    // VIEW USERS
    public void viewUsers() {

        String sql = """
                SELECT user_id, name, email, phone
                FROM users
                ORDER BY user_id
                """;

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            System.out.println("\nUSERS");
            System.out.println("----------------------------------------");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                System.out.println(
                        userId + " | "
                                + name + " | "
                                + email + " | "
                                + phone);
            }

            if (!found) {
                System.out.println("No users found.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

    // BORROW BOOK
    public void borrowBook(int bookId, int userId) {

        String checkBookSql = """
                SELECT available
                FROM books
                WHERE book_id = ?
                """;

        String checkUserSql = """
                SELECT user_id
                FROM users
                WHERE user_id = ?
                """;

        String updateBookSql = """
                UPDATE books
                SET available = false
                WHERE book_id = ?
                """;

        String transactionSql = """
                INSERT INTO transactions
                (book_id, user_id, borrow_date, status)
                VALUES (?, ?, CURRENT_DATE, 'BORROWED')
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {

            // Start transaction
            connection.setAutoCommit(false);

            try {

                // Check whether the book exists and is available
                try (PreparedStatement statement = connection.prepareStatement(checkBookSql)) {

                    statement.setInt(1, bookId);

                    try (ResultSet resultSet = statement.executeQuery()) {

                        if (!resultSet.next()) {
                            throw new SQLException("Book does not exist.");
                        }

                        boolean available = resultSet.getBoolean("available");

                        if (!available) {
                            throw new SQLException(
                                    "Book is already borrowed.");
                        }
                    }
                }

                // Check whether the user exists
                try (PreparedStatement statement = connection.prepareStatement(checkUserSql)) {

                    statement.setInt(1, userId);

                    try (ResultSet resultSet = statement.executeQuery()) {

                        if (!resultSet.next()) {
                            throw new SQLException("User does not exist.");
                        }
                    }
                }

                // Make the book unavailable
                try (PreparedStatement statement = connection.prepareStatement(updateBookSql)) {

                    statement.setInt(1, bookId);
                    statement.executeUpdate();
                }

                // Create borrowing transaction
                try (PreparedStatement statement = connection.prepareStatement(transactionSql)) {

                    statement.setInt(1, bookId);
                    statement.setInt(2, userId);

                    statement.executeUpdate();
                }

                // Everything succeeded
                connection.commit();

                System.out.println("Book borrowed successfully.");

            } catch (SQLException e) {

                // Undo changes if anything failed
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    System.out.println(
                            "Rollback failed: "
                                    + rollbackError.getMessage());
                }

                System.out.println(
                        "Borrow failed: " + e.getMessage());

            } finally {

                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.out.println(
                            "Could not restore auto-commit: "
                                    + e.getMessage());
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }

    // RETURN BOOK
    public void returnBook(int bookId) {

        String findTransactionSql = """
                SELECT transaction_id
                FROM transactions
                WHERE book_id = ?
                AND status = 'BORROWED'
                ORDER BY transaction_id DESC
                LIMIT 1
                """;

        String updateTransactionSql = """
                UPDATE transactions
                SET return_date = CURRENT_DATE,
                    status = 'RETURNED'
                WHERE transaction_id = ?
                """;

        String updateBookSql = """
                UPDATE books
                SET available = true
                WHERE book_id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection()) {

            // Start transaction
            connection.setAutoCommit(false);

            try {

                int transactionId;

                // Find active borrowing transaction
                try (PreparedStatement statement = connection.prepareStatement(findTransactionSql)) {

                    statement.setInt(1, bookId);

                    try (ResultSet resultSet = statement.executeQuery()) {

                        if (!resultSet.next()) {
                            throw new SQLException(
                                    "Book is not currently borrowed.");
                        }

                        transactionId = resultSet.getInt("transaction_id");
                    }
                }

                // Update the borrowing transaction
                try (PreparedStatement statement = connection.prepareStatement(updateTransactionSql)) {

                    statement.setInt(1, transactionId);

                    statement.executeUpdate();
                }

                // Make the book available again
                try (PreparedStatement statement = connection.prepareStatement(updateBookSql)) {

                    statement.setInt(1, bookId);

                    statement.executeUpdate();
                }

                // Everything succeeded
                connection.commit();

                System.out.println("Book returned successfully.");

            } catch (SQLException e) {

                // Undo changes
                try {
                    connection.rollback();
                } catch (SQLException rollbackError) {
                    System.out.println(
                            "Rollback failed: "
                                    + rollbackError.getMessage());
                }

                System.out.println(
                        "Return failed: " + e.getMessage());

            } finally {

                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.out.println(
                            "Could not restore auto-commit: "
                                    + e.getMessage());
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database error: " + e.getMessage());
        }
    }
}