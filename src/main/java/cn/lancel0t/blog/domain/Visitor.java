package cn.lancel0t.blog.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 用户 实体
 * 
 * @author Work
 *
 */
@Entity // 实体
public class Visitor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主键

	@NotEmpty
	@Size(max = 20)
	@Column(nullable = false, length = 20, unique = true)
	private String ipAddress; // ip地址

	@NotEmpty
	@Size(min = 2, max = 20)
	@Column(nullable = false, length = 20)
	private String name; // 用户昵称

	@Size(max = 50)
	@Email
	@Column(length = 50)
	private String email; // 邮箱地址

	@Size(max = 50)
	@Column(length = 50)
	private String website; // 个人网址

	protected Visitor() {
	}

	public Visitor(String name, String email, String website) {
		this.name = name;
		this.email = email;
		this.website = website;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
