package wanderer2357.redwire.dto;

import java.util.Objects;

public class ImmutableClientDto {
	
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	
	public ImmutableClientDto(String firstname,
			String lastname,
			String email,
			String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableClientDto that = (ImmutableClientDto) object;
        return (this.firstname.equals(that.firstname)
        		&& this.lastname.equals(that.lastname)
        		&& this.email.equals(that.email)
        		&& this.phone.equals(that.phone));
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.firstname, this.lastname, this.email, this.phone);
    }
	
	@Override
	public String toString() {
		return "ImmutableClientDto{" +
				", firstname='" + this.firstname + '\'' +
				", lastname='" + this.lastname + '\'' +
				", email='" + this.email + '\'' +
				", phone='" + this.phone + '\'' +
				'}';
	}

}
