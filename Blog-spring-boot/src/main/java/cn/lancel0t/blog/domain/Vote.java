package cn.lancel0t.blog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

/**
 * 点赞 实体
 * 
 * @author Work
 *
 */
@Entity
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主键

	@OneToOne(cascade = CascadeType.DETACH)
	private Visitor visitor; // 点赞用户

	@CreationTimestamp
	@Column(nullable = false)
	private Timestamp createTime;// 创建时间

	protected Vote() {
	}

	public Vote(Visitor user) {
		this.visitor = user;
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

	public Timestamp getCreateTime() {
		return createTime;
	}
}
