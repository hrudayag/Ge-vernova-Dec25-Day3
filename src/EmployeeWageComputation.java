import java.util.*;
class Employee {
    String name;
    int totalHours;
    int totalDays;
    int monthlyWage;

    Employee(String empName) {
        name = empName;
        totalHours = 0;
        totalDays = 0;
        monthlyWage = 0;
    }
}
class CompanyEmpWage {
    static int WAGE_PER_HOUR = 20;
    static int FULL_DAY_HOURS = 8;
    static int PART_TIME_HOURS = 8;
    static int MAX_WORKING_DAYS = 20;
    static int MAX_WORKING_HOURS = 100;
    ArrayList<Employee> employeeList;
    CompanyEmpWage() {
        employeeList = new ArrayList<>();
    }
    // UC 1: Check employee attendance using random
    int checkAttendance() {
        return (int)(Math.random() * 3);
    }
    // UC 2, UC 3, UC 4: Calculate daily wage using switch case
    int calculateDailyWage(int attendance) {
        int hoursWorked = 0;
        switch(attendance) {
            case 0:
                hoursWorked = 0;
                break;
            case 1:
                hoursWorked = FULL_DAY_HOURS;
                break;
            case 2:
                hoursWorked = PART_TIME_HOURS;
                break;
        }
        return hoursWorked * WAGE_PER_HOUR;
    }
    // UC 5, UC 6: Calculate monthly wage till 100 hours or 20 days
    void computeEmployeeWage(Employee emp) {
        System.out.println("\nCalculating wage for: " + emp.name);
        while(emp.totalDays < MAX_WORKING_DAYS && emp.totalHours < MAX_WORKING_HOURS) {
            int attendance = checkAttendance();
            int hoursWorked = 0;
            switch(attendance) {
                case 0:
                    hoursWorked = 0;
                    break;
                case 1:
                    hoursWorked = FULL_DAY_HOURS;
                    break;
                case 2:
                    hoursWorked = PART_TIME_HOURS;
                    break;
            }
            if(emp.totalHours + hoursWorked > MAX_WORKING_HOURS) {
                hoursWorked = MAX_WORKING_HOURS - emp.totalHours;
            }
            emp.totalHours += hoursWorked;
            int dailyWage = hoursWorked * WAGE_PER_HOUR;
            emp.monthlyWage += dailyWage;
            emp.totalDays++;
        }
        System.out.println("Total Working Days: " + emp.totalDays);
        System.out.println("Total Working Hours: " + emp.totalHours);
        System.out.println("Total Monthly Wage: Rs." + emp.monthlyWage);
    }
    void addEmployee(String name) {
        Employee emp = new Employee(name);
        employeeList.add(emp);
        computeEmployeeWage(emp);
    }
}
public class EmployeeWageComputation {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Wage Computation Program");
        CompanyEmpWage company = new CompanyEmpWage();
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter employee name: ");
            String name = sc.nextLine();
            company.addEmployee(name);
        }
        sc.close();
    }
}