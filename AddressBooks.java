import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AddressBooks {

	public Map<String, AddressBookMain> addressBooks = new TreeMap<String, AddressBookMain>();

	public void showAddressBooks() {

		System.out.println("\nList of Address Books Added: \n");
		addressBooks.forEach((k, v) -> System.out.println(k + "\n"));
	}

	public int searchPerson(String searchIn, boolean toView) {

		Iterator addressBookList = addressBooks.entrySet().iterator();
		int noOfPersonInCity = 0;
		int noOfPersonInState = 0;
		while (addressBookList.hasNext()) {
			Map.Entry entry = (Map.Entry) addressBookList.next();
			AddressBookMain a = (AddressBookMain) entry.getValue();

			Map<String, ContactPerson> mapCity = a.getAddressBookByCity();
			ArrayList<String> listCity = new ArrayList<>(mapCity.keySet());
			Collections.sort(listCity);
			for (String s : listCity)
				if (s.equals(searchIn)) {
					if (toView)
						System.out.println(mapCity.get(s));
					noOfPersonInCity++;
				}

			Map<String, ContactPerson> mapState = a.getAddressBookByState();
			ArrayList<String> listState = new ArrayList<>(mapState.keySet());
			Collections.sort(listState);
			for (String s : listState)
				if (s.equals(searchIn)) {
					if (toView)
						System.out.println(mapState.get(s));
					noOfPersonInState++;
				}
		}

		if (noOfPersonInCity != 0)
			return noOfPersonInCity;
		if (noOfPersonInState != 0)
			return noOfPersonInState;
		else {
			System.out.println("\nNo Person Found !!\n");
			return 0;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AddressBooks a = new AddressBooks();
		System.out.println("\n***** Welcome to Address Book Program *****\n");

		while (true) {
			System.out.println("\n1. Add a new Address Book");
			System.out.println("\n2. Search person across all address books");
			System.out.println("\n3. View names of Address Books");
			System.out.println("\n4. View count of persons according to state or city ");
			System.out.println("\n5. Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				while (true) {
					AddressBookMain m = new AddressBookMain();
					System.out.println("Enter name of the Address Book");
					String name = sc.next();
					if (a.addressBooks.containsKey(name)) {
						System.out.println("\nAddress Book already exists !!!\n");
						continue;
					} else {
						a.addressBooks.put(name, m);
						System.out.println("\n In Address Book : " + name);
						m.maintainAddressBook();
						break;
					}
				}
				break;

			case 2:
				System.out.println("Enter city or state to search a person");
				String searchIn = sc.next();
				a.searchPerson(searchIn, true);
				break;

			case 3:
				a.showAddressBooks();
				break;

			case 4:
				System.out.println("Enter city or state to view count");
				String countFor = sc.next();
				int count = a.searchPerson(countFor, false);
				if (count != 0)
					System.out.println("No of Persons in " + countFor + "is : " + count);

			default:
				break;
			}

			if (choice == 5)
				break;
			else
				System.out.println("\nEnter option");
		}
		System.out.println("\nThank You !!!");
	}
}

class AddressBookMain {

	Scanner sc = new Scanner(System.in);
	private List<ContactPerson> addressBookList;
	private Map<String, ContactPerson> addressBookByName;
	private Map<String, ContactPerson> addressBookByCity;
	private Map<String, ContactPerson> addressBookByState;

	public AddressBookMain() {
		addressBookList = new ArrayList<ContactPerson>();
		addressBookByName = new HashMap<String, ContactPerson>();
		addressBookByCity = new TreeMap<String, ContactPerson>();
		addressBookByState = new TreeMap<String, ContactPerson>();
	}

	public Map<String, ContactPerson> getAddressBookByName() {
		return addressBookByName;
	}

	public Map<String, ContactPerson> getAddressBookByCity() {
		return addressBookByCity;
	}

	public Map<String, ContactPerson> getAddressBookByState() {
		return addressBookByState;
	}

	public List<ContactPerson> getAddressBookList() {
		return addressBookList;
	}

	private void addContactPerson(ContactPerson c) {
		addressBookList.add(c);
		addressBookByName.put(c.getFirstName(), c);
		addressBookByCity.put(c.getCity(), c);
		addressBookByState.put(c.getState(), c);
		System.out.println("\nContact Added\n");
	}

	private void editContactPerson(String firstName) {
		ContactPerson cp = addressBookByName.get(firstName);
		if (cp == null) {
			System.out.println("\nNo such Person available !!\n");
		} else {
			System.out.println("Here is the Person Details to be edited " + cp);
			System.out.println(
					"Enter Updated Contact details of person in format: Address, City, State, Zip, Phone, email");
			String[] details = new String[6];
			for (int i = 0; i <= 5; i++) {
				details[i] = sc.next();
			}
			cp.setAddress(details[0]);
			cp.setCity(details[1]);
			cp.setState(details[2]);
			cp.setZip(details[3]);
			cp.setPhone(details[4]);
			cp.setEmail(details[5]);
			System.out.println("Updated Contact Details :");
			System.out.println(cp);
		}
	}

	public void deleteContactPerson(String name) {
		addressBookByName.remove(name);
		addressBookByCity.forEach((k, v) -> {
			if (v.getFirstName().equals(name))
				addressBookByCity.remove(name);
		});
		addressBookByState.forEach((k, v) -> {
			if (v.getFirstName().equals(name))
				addressBookByState.remove(name);
		});
		for (int i = 0; i < addressBookList.size(); i++) {
			if (addressBookList.get(i).getFirstName().equals(name)) {
				addressBookList.remove(i);
			}
		}
		System.out.println("Contact Deleted !!!");
	}

	public boolean checkForDuplicateName(String name) {
		for (ContactPerson cd : addressBookList) {
			if (cd.getFirstName().equals(name)) {
				System.out.println("A Person with this name already exist !!\n");
				return true;
			}
		}
		return false;
	}

	public void maintainAddressBook() {

		while (true) {
			System.out.println("\n1. Add Contact Details");
			System.out.println("\n2. Edit Contact Details");
			System.out.println("\n3. Delete Contact Details");
			System.out.println("\n4. Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				while (true) {
					System.out.println("Enter Contact details of the person\n");
					String[] details = new String[8];
					System.out.println("Enter First Name :");
					details[0] = sc.next();
					if (checkForDuplicateName(details[0]))
						continue;
					System.out.println("Enter Last Name :");
					details[1] = sc.next();
					System.out.println("Enter Address :");
					details[2] = sc.next();
					System.out.println("Enter City :");
					details[3] = sc.next();
					System.out.println("Enter State :");
					details[4] = sc.next();
					System.out.println("Enter Zip :");
					details[5] = sc.next();
					System.out.println("Enter Phone :");
					details[6] = sc.next();
					System.out.println("Enter e-mail :");
					details[7] = sc.next();
					ContactPerson c = new ContactPerson(details[0], details[1], details[2], details[3], details[4],
							details[5], details[6], details[7]);

					addContactPerson(c);
					break;
				}
				break;

			case 2:
				while (true) {
					if (addressBookList.size() == 0) {
						System.out.println("No Contacts Available !!! \n Please add some ");
						break;
					}
					System.out.println("Enter the first name to edit the contact details");
					String name = sc.next();
					editContactPerson(name);
				}
				break;

			case 3:
				while (true) {
					if (addressBookList.size() == 0) {
						System.out.println("No Contacts Available !!! \n Please add some ");
						break;
					}
					System.out.println("Enter the first name to delete the contact details");
					String dname = sc.next();
					if (addressBookByName.containsKey(dname))
						deleteContactPerson(dname);
					else
						System.out.println("No such Contact available !");
				}
				break;

			default:
				break;
			}
			if (choice == 4)
				break;
			else
				System.out.println("\nEnter option");
		}
	}
}

class ContactPerson {

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;

	public ContactPerson(String firstName, String lastName, String address, String city, String state, String zip,
			String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object o) {
		ContactPerson c = (ContactPerson) o;
		return c.firstName.equals(this.firstName);
	}

	public String toString() {
		return "\n Name " + firstName + " " + lastName + "\n Address " + address + "\n city " + city + "\n state "
				+ state + "\n zip " + zip + "\n Phone " + phone + "\n email " + email;
	}
}
