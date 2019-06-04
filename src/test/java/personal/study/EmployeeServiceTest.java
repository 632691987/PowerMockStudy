package personal.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)//告诉JUnit用PowerMockRunner来执行测试。
@PrepareForTest(Employee.class)//告诉PowerMock准备Employee类进行测试。适用于模拟final类或有final, private, static, native方法的类。
public class EmployeeServiceTest {
	@Test
	public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {
		PowerMockito.mockStatic(Employee.class);
		PowerMockito.when(Employee.count()).thenReturn(900);

		EmployeeService employeeService = new EmployeeService();
		assertEquals(900, employeeService.getEmployeeCount());
	}



	@Test
	public void shouldReturnTrueWhenIncrementOf10PercentageIsGivenSuccessfully() {
		PowerMockito.mockStatic(Employee.class);
		PowerMockito.doNothing().when(Employee.class);
		Employee.giveIncrementOf(10);
		EmployeeService employeeService = new EmployeeService();
		assertTrue(employeeService.giveIncrementToAllEmployeesOf(10));
	}

	@Test
	public void shouldReturnFalseWhenIncrementOf10PercentageIsNotGivenSuccessfully() throws Exception {
		PowerMockito.mockStatic(Employee.class);
		PowerMockito.doThrow(new IllegalStateException()).when(Employee.class, "giveIncrementOf", ArgumentMatchers.anyInt());

		//可能代码的bug, 这句话一定要加进去
		Employee.giveIncrementOf(10);
		EmployeeService employeeService = new EmployeeService();
		assertFalse(employeeService.giveIncrementToAllEmployeesOf(10));
	}

	@Test
	public void shouldCreateNewEmployeeIfEmployeeIsNew() {
		Employee mock = PowerMockito.mock(Employee.class);
		PowerMockito.when(mock.isNew()).thenReturn(true);
		EmployeeService employeeService = new EmployeeService();
		employeeService.saveEmployee(mock);
		Mockito.verify(mock).create();
		Mockito.verify(mock, Mockito.never()).update();
	}
}