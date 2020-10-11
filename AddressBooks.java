
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.function.Predicate;

public class AddressBooks {

	public Map<String, AddressBookMain> addressBooks = new TreeMap<String, AddressBookMain>();

	public void showAddressBooks() {

		System.out.println("\nList of Address Books Added: \n");
		addressBooks.forEach((k, v) -> System.out.println(k + " " + v.addressBookList + "\n"));
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AddressBooks a = new AddressBooks();
		System.out.println("\n***** Welcome to Address Book Program *****\n");

		while (true) {
			AddressBookMain m = new AddressBookMain();
			System.out.println("Enter name of the Address Book");
			String name = sc.next();
			if (a.addressBooks.containsKey(name))
				System.out.println("\nAddress Book already exists !!!\n");
			else {
				a.addressBooks.put(name, m);
				System.out.println("\nEnter Details for " + name);
				m.maintainAddressBook();
			}
			System.out.println("Want to add more Address Books (y/n)");
			String response = sc.next();
			if (response.equals("y"))
				continue;
			else
				break;
		}
		a.showAddressBooks();
	}
}

class AddressBookMain {

	Scanner sc = new Scanner(System.in);
	public List<ContactPerson> addressBookList;
	private Map<String, ContactPerson> addressBookMap;

	public AddressBookMain() {
		addressBookList = new ArrayList<ContactPerson>();
		addressBookMap = new HashMap<String, ContactPerson>();
	}

	private void addContactPerson(ContactPerson c) {
		addressBookList.add(c);
		addressBookMap.put(c.getFirstName(), c);
		System.out.println("\nContact Added\n");
	}

	private void editContactPerson(String firstName) {
		ContactPerson cp = addressBookMap.get(firstName);
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
		boolean b2 = false;
		ContactPerson cp = addressBookMap.get(name);
		addressBookMap.remove(name);
		for (int i = 0; i < addressBookList.size(); i++) {
			if (addressBookList.get(i).getFirstName().equals(name)) {
				addressBookList.remove(i);
			}
		}
		System.out.println("Contact Deleted !!!");
	}

	public boolean checkForDuplicateName(ContactPerson person) {

		Predicate<ContactPerson> compareName = n-> n.equals(person);
		boolean value = addressBookList.stream().anyMatch(compareName);
		return value;
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
					if(checkForDuplicateName(c)) {
						System.out.println("Person already exist in the Address Book !!\n Please try with a different name");
						continue;
					}

					addContactPerson(c);
					System.out.println("Want to add more Contacts (y/n)");
					String option1 = sc.next();
					if ((option1.equals("y")))
						continue;
					else
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
					System.out.println("Want to edit more Contacts (y/n)");
					String option2 = sc.next();
					if ((option2.equals("y")))
						continue;
					else
						break;
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
					if (addressBookMap.containsKey(dname))
						deleteContactPerson(dname);
					else
						System.out.println("No such Contact available !");
					System.out.println("Want to delete more Contacts (y/n)");
					String option3 = sc.next();
					if ((option3.equals("y")))
						continue;
					else
						break;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean equals(Object o) {
		ContactPerson c = (ContactPerson) o;
		if (c.firstName.equals(this.firstName))
			return true;
		else
			return false;
	}

	public String toString() {
		return "\n Name " + firstName + " " + lastName + "\n Address " + address + "\n city " + city + "\n state "
				+ state + "\n zip " + zip + "\n Phone " + phone + "\n email " + email;
	}
}
