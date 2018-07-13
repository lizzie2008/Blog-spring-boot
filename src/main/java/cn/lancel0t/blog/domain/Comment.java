package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;


/**
 * 评论实体
 * 
 * @author Work
 *
 */
@Entity // 实体
public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id; // 主键
	
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private Visitor visitor;	//评论用户

	@NotEmpty
	@Size(min=2, max=500)
	@Column(nullable = false, length = 500)
	private String content;	//评论内容
	
	@CreationTimestamp 
	@Column(nullable = false)
	private Timestamp createTime; // 创建时间
 
	protected Comment() {
	}
	
	public Comment(Visitor user, String content) {
		this.visitor = user;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

}
