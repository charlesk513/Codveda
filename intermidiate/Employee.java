import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee(String name, String id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        EmployeeManager manage = new EmployeeManager();

        while (true) {

            System.out.print("read/update/add/delete?: ");
            String operation = reader.readLine().toLowerCase();

            if (operation.equals("read")) {
                manage.view();
            } else if (operation.equals("delete")) {
                System.out.print("Enter the id: ");
                String id = reader.readLine();
                manage.delete(id);
            } else if (operation.equals("add")) {
                manage.add(reader);
            } else if (operation.equals("update")) {

                manage.update(reader);

            } else {
                break;
            }
        }

    }

}

class EmployeeManager {
    static List<Employee> data = new ArrayList<>();

    public EmployeeManager() {

    }

    public void add(BufferedReader reader) throws IOException {
        System.out.print("Enter the name: ");
        String name = reader.readLine();
        System.out.print("Enter the id: ");
        String id = reader.readLine();
        System.out.print("Enter the salary: ");
        double salary = Double.parseDouble(reader.readLine());
        data.add(new Employee(name, id, salary));
        System.out.println("Employee successfully added");

    }

    public void view() {
        if (data.isEmpty()) {
            System.out.println("The List is currently empty!");
        } else {
            for (Employee i : data) {
                System.out.println("Name: " + i.getName() + " Id: " + i.getId() + " Salary: " + i.getSalary());
            }
        }
    }

    public void update(BufferedReader read) throws IOException {
        if (data.isEmpty()) {
            System.out.println("The List is currently empty!");
            return;
        }
        System.out.print("Enter the id: ");
        String id = read.readLine();
        boolean found = false;
        for (Employee i : data) {
            if (i.getId().equals(id)) {
                found = true;
                System.out.println("Name: " + i.getName() + " Id: " + i.getId() + " Salary: " + i.getSalary());

                System.out.print("Enter the name, (press enter to keep current): ");
                String name = read.readLine();
                System.out.print("Enter the id, (press enter to keep current): ");
                String identity = read.readLine();
                System.out.print("Enter the salary, (press enter to keep current): ");
                String money = read.readLine();
                if (money.equals("")) {

                } else {
                    double salary = Double.parseDouble(money);
                    i.setSalary(salary);
                }

                if (!(name.equals(""))) {
                    i.setName(name);
                }
                if (!(identity.equals(""))) {
                    i.setId(identity);
                }
            }

        }
        if (found == false) {
            System.out.println("Employee record with Id: " + id + " doesn't exist");
        }
    }

    public void delete(String id) {
        if (data.isEmpty()) {
            System.out.println("The List is currently empty!");
            return;
        }
        boolean found = false;
        for (Employee m : data) {
            if (m.getId().equals(id)) {
                found = true;
                data.remove(m);
                break;
            }
        }
        if (found == false) {
            System.out.println("Employee record with Id: " + id + " doesn't exist");
        }

    }
}
