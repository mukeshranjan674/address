import java.util.*;

public class AddressBookMain {

	private List<ContactPerson>AddressBook;
	
	public AddressBookMain() {
		AddressBook = new ArrayList<ContactPerson>();
	}
	
	private void addContactPerson(ContactPerson c) {
		AddressBook.add(c);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AddressBookMain a = new AddressBookMain();
		System.out.println("***** Welcome to Address Book Program *****");
		System.out.println("Enter Contact details of person in format first name, last name, address, city, state, zip, phone, email");
		String[]details = new String[8];
		for(int i = 0 ; i <= 7 ; i++) {
			details[i] = sc.next();
		}
		ContactPerson c = new ContactPerson(details[0], details[1], details[2], details[3], details[4], details[5], details[6], details[7]);
		a.addContactPerson(c);
		System.out.println(c);

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
	
	public String toString() {
		return "Name " + firstName + " " + lastName + " Address " + address +" city " + city + " state " + state + " zip " + zip + " Phone " + phone + " email " + email ;
	}
}