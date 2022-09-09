import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;
import com.example.reimbursemenonspringver.shared.EmployeeDao;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TestEmployeeDao {

    EmployeeDao employeeDao = new EmployeeDao();

    @Test
    public void testGetEmployeeByLoginCredentials(){
        System.out.println("Checking employee by login...");
        Employee testEmployee  = employeeDao.login("janedoe@sate.com","password123");
        System.out.println(testEmployee.toString());
    }

    @Test
    public void testGetEmployeeById(){
        System.out.println("Checking employee by ID");
        Employee employee = employeeDao.getEmployeeById(1);
        System.out.println(employee.toString());
    }

    @Test
    public void testRequestReimbursement(){
        int employeeId = 3;
        double amount = 900.00;
        String receiptLink = "https://fake_receipt.com/fake_receipt1";
        String comment = "testRequestReimbursement()";
        employeeDao.requestReimbursement(employeeId, amount, receiptLink, comment);
    }

    @Test
    public void testShowAllEmployeeRequestsByIdAndStatus(){
        Employee employee = new Employee();
        employee.setEmployeeId(3);
        ArrayList<Reimbursement> reimbursementList = employeeDao.getReimbursementRequestsByIdAndStatus(employee, "approved");
        for(Reimbursement item : reimbursementList){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testUpdateEmployeeInformation(){
        Employee employee = new Employee();
        employee.setPassword("updatedpassword");
        employee.setEmail("update@update.com");
        employee.setEmployeeId(4);
        employee.setName("updated name");
        employee.setDept("UPD");
        employeeDao.updateEmployeeInformation(employee);
    }
}
