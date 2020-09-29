public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("***** Welcome to Address Book Program *****");
		ContactPerson c = new ContactPerson("Mukesh" , "Ranjan" , "Sijua" , "Dhanbad" , "Jharkhand" , "828108" , "1111111111" , "abc@gmail.com");
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