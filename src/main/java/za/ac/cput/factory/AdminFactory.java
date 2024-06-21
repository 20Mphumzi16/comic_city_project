package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.User;
import za.ac.cput.util.Helper;

public class AdminFactory {



    public static Admin buildAdminWithoutMiddleName(Long employeeId, String firstName , String lastName ,String password){

        if(Helper.isValidInt(Math.toIntExact(employeeId))||Helper.isStringNullorEmpty(firstName)||Helper.isStringNullorEmpty(lastName)||Helper.isStringNullorEmpty(password))
            return null;
        return (Admin) new Admin.AdminBuilder()
                .setPassword(password)
                .setEmployeeId(employeeId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }



    public static Admin buildAdminWithoutLastName(Long employeeId, String firstName ,String password){

        if(Helper.isValidInt(Math.toIntExact(employeeId))||Helper.isStringNullorEmpty(firstName)||Helper.isStringNullorEmpty(password))
            return null;
        return (Admin) new Admin.AdminBuilder()
                .setPassword(password)
                .setEmployeeId(employeeId)
                .setFirstName(firstName)
                .build();
    }
}
