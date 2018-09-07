package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "blog", type = "blog", shards = 1, replicas = 0)
public class BlogES implements Serializable {
	private static final long serialVersionUID = 1L;

	@Field(index = false)
	private String id;
	@Field(type = FieldType.Text, index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String title;
	@Field(type = FieldType.Text, index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String summary; // 摘要
	@Field(type = FieldType.Text, index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String content;
	@Field(type = FieldType.Text, index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String category;
	@Field(type = FieldType.Text, index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String tags;
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
	private Date createTime;

	public BlogES(Blog blog) {
		this.id = blog.getId();
		this.title = blog.getTitle();
		this.summary = blog.getSummary();
		this.content = blog.getContent();
		this.category = blog.getCategory().getName();
		this.tags = String.join(",", blog.getTags().stream().map(x -> x.getName()).collect(Collectors.toList()));
		this.createTime = blog.getCreateTime();
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
