package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;
@Entity
public class Admin extends User {
    @Id
    private Long employeeId;

    public Admin() {
        super();

    }

    public Admin(AdminBuilder builder) {
        super();
        this.employeeId = builder.employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(employeeId, admin.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }

    @Override
    public String toString() {
        return "Admin{" +
                 super.toString()+
                "employeeId='" + employeeId + '\'' +
                '}';
    }


    public static class AdminBuilder {

        private Long employeeId;

        public AdminBuilder setEmployeeId(Long employeeId) {
            this.employeeId = employeeId;
            return this;
        }


       public  AdminBuilder copy (Admin admin){

            this.employeeId = admin.employeeId;
            return this;
       }

       public  Admin build (){return new Admin(this);}

    }


}
