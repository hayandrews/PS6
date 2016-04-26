package base;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.PersonDomainModel;

public class Person_Test {
	
	private static PersonDomainModel per1;
	private static PersonDomainModel per2;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Date dDate = null;
		try {
			dDate = new SimpleDateFormat("yyyy-MM-dd").parse("1985-11-24");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per1 = new PersonDomainModel();
		per1.setFirstName("Dirk");
		per1.setLastName("Yed");
		per1.setBirthday(dDate);
		per1.setCity("Somewhere");
		per1.setPostalCode(11111);
		per1.setStreet("100 Nowhere Land");
		
		Date dDate2 = null;
		try {
			dDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("1990-02-03");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		per2 = new PersonDomainModel();
		per2.setFirstName("Julie");
		per2.setLastName("For");
		per2.setBirthday(dDate2);
		per2.setCity("Nowhere");
		per2.setPostalCode(11112);
		per2.setStreet("100 Herethere");
		
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void PersonTest()
	{		
		//tries to get per1 from per, not there so its null//
		PersonDomainModel per;		
		ArrayList<PersonDomainModel> pers = new ArrayList<PersonDomainModel>();
		per = PersonDAL.getPerson(per1.getPersonID());		
		assertNull("No person like that here",per);	
		
		//checks if person 2 is in list
		per = PersonDAL.getPerson(per2.getPersonID());
		assertNull("No person like that here",per);
		
		//add persons in, adding test
		PersonDAL.addPerson(per1);
		PersonDAL.addPerson(per2);	
		
		//checks to if person is successfully added
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNotNull("Person is here",per);
		System.out.println("get has found" + per1.getPersonID());
		
		per = PersonDAL.getPerson(per2.getPersonID());
		assertNotNull("Person is here",per);
		System.out.println("get has found" + per2.getPersonID());

		
		//get all persons
		pers = PersonDAL.getPersons();
		assertTrue("There are multiple people in this list", pers.size() >= 2);
		System.out.println(pers);
		
		//Now to Update Julies Last Name
		per1.setLastName("Whal");
		PersonDAL.updatePerson(per1);
		per = PersonDAL.getPerson(per1.getPersonID());
		assertTrue(per1.getLastName() == "Whal");

		
		//Now since person was added per1 and per2 gets deleted, then checked to see if they're no longer there//
		PersonDAL.deletePerson(per1.getPersonID());
		per = PersonDAL.getPerson(per1.getPersonID());
		assertNull("No person like that here",per);
		
		PersonDAL.deletePerson(per2.getPersonID());
		per = PersonDAL.getPerson(per2.getPersonID());
		assertNull("No person like that here",per);

	}


}
