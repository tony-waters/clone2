package uk.bit1.clone1.vo;

public class Password {

	private String value;
	
	public Password() {
		super();
	}
	
	public Password(String value) {
		if(!isValid(value)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.value = value;
	}
	
	public static boolean isValid(String value) {
		if(value.contains(" ")) {
			return false;
		}
		return true;
	}
	
	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
