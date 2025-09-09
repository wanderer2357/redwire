package wanderer2357.redwire.model.embeddable;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Embeddable
public class ImmutableClient {
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	private String firstname;
	
	@Column(nullable = false)
	@NotBlank
	@Size(max = 50)
	private String lastname;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 254)
	@Email
	private String email;
	
	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(max = 20)
	@Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric")
	private String phone;
	
	public ImmutableClient() {
	}
	
	public ImmutableClient(@NotBlank @Size(max=50) String firstname,
			@NotBlank @Size(max=50) String lastname,
			@NotBlank @Email @Size(max=254) String email,
			@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric") @Size(max=20) String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(@NotBlank @Size(max=50) String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(@NotBlank @Size(max=50) String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(@NotBlank @Email @Size(max=254) String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(@NotBlank @Pattern(regexp = "^[0-9]+$", message = "Phone number must be numeric") @Size(max=20) String phone) {
		this.phone = phone;
	}
	
	@Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        ImmutableClient that = (ImmutableClient) object;
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
		return "ImmutableClient{" +
				", firstname='" + this.firstname + '\'' +
				", lastname='" + this.lastname + '\'' +
				", email='" + this.email + '\'' +
				", phone='" + this.phone + '\'' +
				'}';
	}

}
