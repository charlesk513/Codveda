import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title,
            String author, boolean available) {

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return bookId + " | "
                + title + " | "
                + author + " | Available: "
                + available;
    }
}

class User {

    private int userId;
    private String name;
    private String email;
    private String phone;

    public User(int userId, String name,
            String email, String phone) {

        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return userId + " | "
                + name + " | "
                + email + " | "
                + phone;
    }
}

public class LibraryManagement {

    public static void main(String[] args) throws IOException {

        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
        ManagementSystem library = new ManagementSystem();
        int choice;
        do {
            System.out.println("\n==============================");
            System.out.println("   LIBRARY MANAGEMENT SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");

            choice = Integer.parseInt(scanner.readLine());
            switch (choice) {
                case 1 -> {

                    System.out.print("Enter book title: ");
                    String title = scanner.readLine();

                    System.out.print("Enter author: ");
                    String author = scanner.readLine();
                    library.addBook(title, author);
                }

                case 2 -> {
                    library.viewBooks();
                }
                case 3 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.readLine();

                    System.out.print("Enter email: ");
                    String email = scanner.readLine();

                    System.out.print("Enter phone: ");
                    String phone = scanner.readLine();

                    library.addUser(name, email, phone);
                }

                case 4 -> {
                    library.viewUsers();
                }

                case 5 -> {

                    System.out.print("Enter book ID: ");
                    int bookId = Integer.parseInt(scanner.readLine());

                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.readLine());
                    library.borrowBook(bookId, userId);
                }
                case 6 -> {

                    System.out.print("Enter book ID: ");
                    int bookId = Integer.parseInt(scanner.readLine());

                    library.returnBook(bookId);
                }

                case 7 -> {
                    System.out.println("Thank you for using the Library Management System.");
                }

                default -> {
                    System.out.println("Invalid choice.");
                }
            }

        } while (choice != 7);

        scanner.close();
    }
}
