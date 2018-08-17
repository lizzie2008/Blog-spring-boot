package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id; // 主键

	@NotNull
	@Size(max = 50)
	@Column(nullable = false, length = 50)
	private String title; // 标题

	@NotNull
	@Size(max = 50)
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

	@ManyToOne
	private Category category; // 分类

	@ManyToOne
	private Archive archive; // 归档

	@Size(max = 100)
	private String tags; // 标签

//	@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	private Integer readSize = 0; // 访问量

	private Integer commentSize = 0; // 评论量

	private Integer likeSize = 0; // 点赞量

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "blog")
	private List<Comment> comments; // 评论

	public Blog() {
		// this.category = new Category();
		// this.comments = new ArrayList<Comment>();
	}

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

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
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

	public Integer getLikeSize() {
		return likeSize;
	}

	public void setLikeSize(Integer likeSize) {
		this.likeSize = likeSize;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
