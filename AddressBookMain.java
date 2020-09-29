import java.util.*;

public class AddressBookMain {
	
	static Scanner sc = new Scanner(System.in);
	private List<ContactPerson>AddressBookList;
	private Map<String,ContactPerson>AddressBookMap;
	
	public AddressBookMain() {
		AddressBookList = new ArrayList<ContactPerson>();
		AddressBookMap = new HashMap<String,ContactPerson>();
	}
	
	private void addContactPerson(ContactPerson c) {
		AddressBookList.add(c);
		AddressBookMap.put(c.getFirstName(), c);
		System.out.println("Contact Added");
	}
	
	private void editContactPerson(String firstName) {
		ContactPerson cp = AddressBookMap.get(firstName);
		System.out.println("Here is the Person Details to be edited " + cp);
		System.out.println("Enter Updated Contact details of person in format: Address, City, State, Zip, Phone, email");
		String[]details = new String[6];
		for(int i = 0 ; i <= 5 ; i++) {
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
	
	public void deleteContactPerson(String name) {
		boolean b2 = false;
		ContactPerson cp = AddressBookMap.get(name);
		AddressBookMap.remove(name);
		for(int i =0 ; i < AddressBookList.size() ; i++) {
			if(AddressBookList.get(i).getFirstName().equals(name)) {
				AddressBookList.remove(i);
			}
		}
		System.out.println("Contact Deleted !!!");
	}
	
	
	public static void maintainAddressBook(AddressBookMain a) {
		
		while(true) {
			System.out.println("\n1. Add Contact Details");
			System.out.println("\n2. Edit Contact Details");
			System.out.println("\n3. Delete Contact Details");
			System.out.println("\n4. Exit");
			System.out.println("\nEnter your choice");
			int choice = sc.nextInt();
			 
			switch(choice) {
				case 1:
					while(true) {
						System.out.println("Enter Contact details of person in format: first name, last name, address, city, state, zip, phone, email");
						String[]details = new String[8];
						for(int i = 0 ; i <= 7 ; i++) {
							details[i] = sc.next();
						}
						ContactPerson c = new ContactPerson(details[0], details[1], details[2], details[3], details[4], details[5], details[6], details[7]);
						a.addContactPerson(c);
						System.out.println("Want more transactions (y/n)");
						String option1 = sc.next(); 
						if((option1.equals("y")))
							continue;
						else break;
					}
					break;
				case 2:
					while(true) {
						System.out.println("Enter the first name to edit the contact details");
						String name = sc.next();
						a.editContactPerson(name);
						System.out.println("Want more transactions (y/n)");
						String option2 = sc.next(); 
						if((option2.equals("y")))
							continue;
						else break;
					}
					break;
				case 3:
					while(true) {
						System.out.println("Enter the first name to delete the contact details");
						String dname = sc.next();
						a.deleteContactPerson(dname);
						System.out.println("Want more transactions (y/n)");
						String option3 = sc.next(); 
						if((option3.equals("y")))
							continue;
						else break;
					}
					break;
				default:
					break;
			}
			if(choice == 4)
				break;
			else 
				System.out.println("Enter correct option");
		}
	}
	public static void main(String[] args) {
	
		AddressBookMain a = new AddressBookMain();
		System.out.println("***** Welcome to Address Book Program *****");
		maintainAddressBook(a);	
		
		System.out.println("List size " + a.AddressBookList.size());
		System.out.println("Map values " +a.AddressBookMap.values());
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
	
	public ContactPerson(String firstName, String lastName, String address, 
						String city, String state, String zip, String phone,
						String email) {
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
	
	public String toString() {
		return "\n Name " + firstName + " " + lastName + "\n Address " + address +"\n city " + city + "\n state " + state + "\n zip " + zip + "\n Phone " + phone + "\n email " + email ;
	}
}