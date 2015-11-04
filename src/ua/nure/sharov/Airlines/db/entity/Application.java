package ua.nure.sharov.Airlines.db.entity;

public class Application extends Entity {

	private static final long serialVersionUID = 1759399732221829321L;
	private String problem_description;
	private int team_id;
	private int user_id;
	private int application_status_id;
	private String email;

	public String getProblem_description() {
		return problem_description;
	}

	public void setProblem_description(String problem_description) {
		this.problem_description = problem_description;
	}

	public int getTeam_id() {
		return team_id;
	}

	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getApplication_status_id() {
		return application_status_id;
	}

	public void setApplication_status_id(int application_status_id) {
		this.application_status_id = application_status_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Application [problem_description=" + problem_description
				+ ", team_id=" + team_id + ", user_id=" + user_id
				+ ", application_status_id=" + application_status_id
				+ ", email=" + email + "]";
	}
}
