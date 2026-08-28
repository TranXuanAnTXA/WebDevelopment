package com.baitap.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity // Đánh dấu đây là một Thực thể JPA
@Table(name = "Users") // Tên bảng trong Database
public class User implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id // Đánh dấu khóa chính (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng (IDENTITY)
    private int id;

    @Column(unique = true, nullable = false) // Không được rỗng và không được trùng
    private String email;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(columnDefinition = "NVARCHAR(255)") // Dùng NVARCHAR để gõ tiếng Việt
    private String fullName;

    private String passWord;
    private String avatar;
    private int roleid;
    private String phone;
    private Date createdDate;

    // Giữ nguyên các Constructor, Getters và Setters như bài cũ
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User(int id, String email, String userName, String fullName, String passWord, String avatar, int roleid,
			String phone, Date createdDate) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.avatar = avatar;
		this.roleid = roleid;
		this.phone = phone;
		this.createdDate = createdDate;
	}
	public User() {
		super();
		this.id = 0;
		this.email = "";
		this.userName = "";
		this.fullName = "";
		this.passWord = "";
		this.avatar = "";
		this.roleid = 0;
		this.phone = "";
		this.createdDate = null;
	}

}

