package ua.nure.sharov.Airlines.db.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 5894296456862747772L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}