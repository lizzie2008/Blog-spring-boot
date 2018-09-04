package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	/**
	 * 博客类型
	 */
	public static enum BlogType {
		NORMAL, // 普通
		SUBJECT, // 专题
		ABOUT; // 关于
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id; // 主键

	@Column(nullable = false, length = 10)
	@Enumerated(EnumType.STRING)
	private BlogType blogType = BlogType.NORMAL; // 博客类型

	@NotNull
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	private String title; // 标题

	@Size(max = 50)
	@Column(nullable = true, length = 50)
	private String image; // 定义图片

	@Lob
	@NotNull
	@Column(nullable = false)
	private String summary; // 摘要

	@Lob
	@NotNull
	@Column(nullable = false)
	private String content; // markdown内容

	@ManyToOne
	private Category category; // 分类

	@ManyToOne
	private Archive archive; // 归档

	@ManyToMany
	@JoinTable(name = "blog_tag", joinColumns = @JoinColumn(name = "blog_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private Set<Tag> tags = new HashSet<Tag>(); // 标签

	// @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd
	// HH:mm", timezone = "GMT+8")
	private Date createTime;

	private Integer readSize = 0; // 访问量

	private Integer commentSize = 0; // 评论量

	private Integer likeSize = 0; // 点赞量

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
	private Set<Comment> comments = new HashSet<Comment>(); // 评论

	public Blog() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
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

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
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

	public Integer getLikeSize() {
		return likeSize;
	}

	public void setLikeSize(Integer likeSize) {
		this.likeSize = likeSize;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
