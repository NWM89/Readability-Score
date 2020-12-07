class Employee {

    String name;
    int salary;
    String address;

    public Employee() {
        this.salary = 0;
        this.name = "unknown";
        this.address = "unknown";
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.address = "unknown";
    }

    public Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}