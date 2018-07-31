package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

	@NotNull
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	private String nickName; // 用户昵称

	@Size(max = 100)
	private String webSite; // 用户网址

	@Size(max = 20)
	private String browser; // 浏览器类型

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createTime; // 创建时间

	@NotEmpty
	@Size(max = 500)
	@Column(nullable = false, length = 500)
	private String content; // 评论内容

	@JsonBackReference
	@ManyToOne
	private Blog blog; // 所属博客

	private boolean isReply = false; // 是否为回复的评论

	private Long parentCommentId = null; // 如果是回复的评论，上一级评论的id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public boolean isReply() {
		return isReply;
	}

	public void setReply(boolean isReply) {
		this.isReply = isReply;
	}

	public Long getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(Long parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

}
