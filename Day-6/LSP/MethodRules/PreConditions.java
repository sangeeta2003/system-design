
// A Precondition must be statisfied before a method can be executed.
// Sub classes can weaken the precondition but cannot strengthen it.
// if parent class follow a condition example password must be 8 char then child class can follow same or weak that condition like password msut be 8 char or 6 char (weak)

class User {
    // Precondition: Password must be at least 8 characters long
    public void setPassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long!");
        }
        System.out.println("Password set successfully");
    }
}

class AdminUser extends User {
    // Precondition: Password must be at least 6 characters
    @Override
    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long!");
        }
        System.out.println("Password set successfully");
    }
}

public class PreConditions {
    public static void main(String[] args) {
        User user = new AdminUser();
        user.setPassword("Admin1");  // Works fine: AdminUser allows shorter passwords
    }
}
