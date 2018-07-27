package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 博客实体
 * 
 * @author Work
 *
 */
@Entity // 实体
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Blog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id; // 主键

	@NotNull
	@Size(min = 2, max = 50)
	@Column(nullable = false, length = 50)
	private String title; // 标题

	@NotNull
	@Size(min = 2, max = 50)
	@Column(nullable = false, length = 50)
	private String image; // 定义图片

	@Lob
	@NotNull
	@Column(nullable = false)
	private String summary; // 摘要

	@Lob
	@NotNull
	@Column(nullable = false)
	private String content; // markdown内容

	@OneToOne(cascade = CascadeType.ALL)
	private Category category;

	@Size(max = 100)
	private String tags; // 标签

	@org.hibernate.annotations.CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createTime; // 创建时间

	private Integer readSize = 0; // 访问量、阅读量

	private Integer commentSize = 0; // 评论量

	private Integer voteSize = 0; // 点赞量

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Comment> comments; // 评论

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Vote> votes; // 点赞

	public Blog() {
		this.category = new Category();
		this.comments = new HashSet<Comment>();
		this.votes = new HashSet<Vote>();
	}

	// public Blog(String title, String url, String image, String summary,
	// String content) {
	// this.title = title;
	// this.url = url;
	// this.image = image;
	// this.summary = summary;
	// this.content = content;
	// }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getReadSize() {
		return readSize;
	}

	public void setReadSize(Integer readSize) {
		this.readSize = readSize;
	}

	public Integer getCommentSize() {
		return commentSize;
	}

	public void setCommentSize(Integer commentSize) {
		this.commentSize = commentSize;
	}

	public Integer getVoteSize() {
		return voteSize;
	}

	public void setVoteSize(Integer voteSize) {
		this.voteSize = voteSize;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

}
