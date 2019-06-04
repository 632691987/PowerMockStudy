package personal.study;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.fail;

public class EmployeeTest {

	@Test
	public void shouldNotDoAnythingIfEmployeeWasSaved() {

		Employee employee = PowerMockito.mock(Employee.class);
		PowerMockito.doNothing().when(employee).save();
		try {
			employee.save();
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowAnExceptionIfEmployeeWasNotSaved() {

		Employee employee = PowerMockito.mock(Employee.class);
		PowerMockito.doThrow(new IllegalStateException()).when(employee).save();
		employee.save();
	}
}