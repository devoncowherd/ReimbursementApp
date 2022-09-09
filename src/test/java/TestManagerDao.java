import com.example.reimbursemenonspringver.data.entity.Employee;
import com.example.reimbursemenonspringver.data.entity.Reimbursement;
import com.example.reimbursemenonspringver.shared.EmployeeDao;
import com.example.reimbursemenonspringver.shared.ManagerDao;
import com.example.reimbursemenonspringver.ui.console.ConsoleStyle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestManagerDao {

    ManagerDao managerDao = new ManagerDao();
    EmployeeDao employeeDao = new EmployeeDao();
    private static String APPROVED_STATUS = "approved";
    private static String DENIED_STATUS = "denied";
    String PENDING_STATUS = "pending";

    @Test
    public void testGetAllRequests(){
        ArrayList<Reimbursement> reimbursementList = managerDao.getAllReimbursements();

        for(Reimbursement reimbursement : reimbursementList){
            System.out.println(reimbursement.toString());
        }
    }

    @Test
    public void testGetReimbursementByStatus(){
        ArrayList<Reimbursement> pendingReimbursements = managerDao
                .getReimbursementByStatus(PENDING_STATUS);
        ArrayList<Reimbursement> approvedReimbursements = managerDao
                .getReimbursementByStatus(APPROVED_STATUS);
        ArrayList<Reimbursement> deniedReimbursements = managerDao
                .getReimbursementByStatus(DENIED_STATUS);

        printReimbursementList(pendingReimbursements);
        //ConsoleStyle.squiggleCount(25);
        //printReimbursementList(deniedReimbursements);
        //ConsoleStyle.squiggleCount(25);
        //printReimbursementList(approvedReimbursements);

    }

    private void printReimbursementList(ArrayList<Reimbursement> reimbursementList){
        for(Reimbursement reimbursement : reimbursementList){
            System.out.println(reimbursement.toString());
        }
    }

    @Test
    public void testGetAllEmployees(){
        ArrayList<Employee> allEmployeeList = managerDao.getAllEmployees();
        for(Employee employee : allEmployeeList){
            System.out.println(employee.toString());
        }
    }

    @Test
    public void testUpdateReimbursement(){
        testGetReimbursementByEmployeeId();
        managerDao.setReimbursementStatus("approved",4);
        testGetReimbursementByEmployeeId();

    }

    @Test
    public void testGetReimbursementByEmployeeId() {
        Employee employee = employeeDao.getEmployeeById(3);
        ArrayList<Reimbursement> reimbursementList = managerDao
                .getReimbursementByEmployeeId(employee);
        for(Reimbursement reimbursement : reimbursementList) {
            ConsoleStyle.squiggleCount(100);
            System.out.println(reimbursement.toString());
        }
    }
}
