import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Junit tests for the methods in the Immigration and Workflow classes
 */
public class JUnitTest {
	//Workflow class

	/**
	 * Testing to make sure the proper email is sent out depending
	 * on whether the application was approved or not.
	 */
	@Test 
	public void checkEmail(){
		Immigrant immigrant = new Immigrant();
		Workflow wf = new Workflow(immigrant);

		//tests the case in which the form is not complete/approved
		assertEquals("Unfortunately, we cannot allow your request to go through.", False,  wf.generateEmail());
		//sets the immigrant's/form's complete field to true
		immigrant.setComplete(true);
		//tests the case in which the form is completed/approved
		assertEquals("Congratulations, your application is valid and your request has been sent through.", True, wf.generateEmail());
	}

	/**
	 * Testing to make sure setNextStep handles the possible inputs correctly,
	 * has to be either review or approval or approved
	 */
	@Test
	public void nextStepInput(){
		Workflow wf = new Workflow();

		//setting the next step to a random string should be handled with this message.
		assertEquals("The next step is not a valid workflow step, this operation was not successful.", wf.setNextStep("Hello World"));
	}



	//Immigrant class

	/**
	 * Testing to make sure the relative is checked properly to
	 * ensure they have not already been granted access under a
	 * different immigrant.
	 */
	@Test
	public void checkRelative(){
		Immigrant immigrant = new Immigrant();
		//setting the relative name to someone that has not been granted access
		immigrant.setRelativeName("Christopher Jackson");
		asserEquals(false, immigrant.relativeAccessGranted());
		//setting the relative name to someone that has been granted access
		immigrant.setRelativeName("Jackson Christopher");
		asserEquals(true, immigrant.relativeAccessGranted());
	}

	/**
	 * Testing to make sure setAN handles numbers that are out of bounds.
	 */
	@Test
	public void checkSetAN(){
		Immigrant immigrant = new Immigrant();
		assertEquals("Invalid alien number, the form was not updated.", immigrant.setAN(1000000000));
		assertEquals("Invalid alien number, the form was not updated.", immigrant.setAN(-1));
		assertEquals("Invalid alien number, the form was not updated.", immigrant.setAN(0));
	}


}
