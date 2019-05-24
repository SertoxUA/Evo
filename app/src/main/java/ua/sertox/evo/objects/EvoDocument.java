package ua.sertox.evo.objects;

import java.io.Serializable;
import java.util.Date;

public class EvoDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7367289796391092618L;

	public EvoDocument() {
		// TODO Auto-generated constructor stub
	}

	public EvoDocument(String name, String content, Date createDate) {
		super();
		this.name = name;
		this.content = content;
		this.createDate = createDate;
	}

	private Integer number;
	private String name;
	private String content;
	private Date createDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	

	@Override
	public String toString() {
		return name;
	}



}
