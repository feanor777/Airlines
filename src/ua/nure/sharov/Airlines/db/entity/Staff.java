package ua.nure.sharov.Airlines.db.entity;

public class Staff extends Entity {
	private static final long serialVersionUID = 2727996458557888492L;
	private String first_name;
	private String last_name;
	private boolean is_free;
	private int profession_id;
	private boolean is_deleted;

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public boolean isIs_free() {
		return is_free;
	}

	public void setIs_free(boolean is_free) {
		this.is_free = is_free;
	}

	public int getProfession_id() {
		return profession_id;
	}

	public void setProfession_id(int proffesion_id) {
		this.profession_id = proffesion_id;
	}

	@Override
	public String toString() {
		return "Staff [first_name=" + first_name + ", last_name=" + last_name
				+ ", is_free=" + is_free + ", proffesion_id=" + profession_id
				+ ", getId()=" + getId() + "]";
	}

}
