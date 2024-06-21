package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;



@Entity
public class Admin extends User {
    @Id
    private Long employeeId;
    private String password;


    public Admin() {
        super();

    }

    public Admin(AdminBuilder builder) {
        super();
        this.employeeId = builder.employeeId;
        this.password = builder.password;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(employeeId, admin.employeeId) && Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId, password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                 super.toString()+
                "employeeId='" + employeeId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public static class AdminBuilder extends UserBuilder{
        private Long employeeId;
        private String password;

        public AdminBuilder setEmployeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

       public  AdminBuilder copy (Admin admin){

            this.employeeId = admin.employeeId;
            this.password = admin.password;
            return this;
       }

       public  Admin build (){return new Admin(this);}

    }


}
