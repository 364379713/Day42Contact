package com.qfedu.entity;

/**
 * 
 *  µÃÂ¿‡
 * @author Admin
 *
 */
public class Contact {
	
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String tel;
	private String qq;
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender2) {
		this.gender = gender2;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", tel=" + tel + ", qq="
				+ qq + ", email=" + email + "]";
	}
}



