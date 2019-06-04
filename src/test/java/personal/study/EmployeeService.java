package personal.study;

public class EmployeeService {
	public int getEmployeeCount() {
		return Employee.count();
	}

	public void saveEmployee(Employee employee) {
		if(employee.isNew()) {
			employee.create();
			return;
		}
		employee.update();
	}

	public boolean giveIncrementToAllEmployeesOf(int percentage) {
		try{
			Employee.giveIncrementOf(percentage);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
