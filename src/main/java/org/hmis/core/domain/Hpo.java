package org.hmis.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="term")
public class Hpo {
	
	@Id
	private String docId;
	@Field
	private Integer id;
	@Field
	private String name;
	@Field
	private Integer is_obsolete;
	@Field
	private Integer is_root;
	@Field
	private String subontology;
	@Field
	private String comment;
	@Field
	private String acc;
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
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
	public Integer getIs_obsolete() {
		return is_obsolete;
	}
	public void setIs_obsolete(Integer is_obsolete) {
		this.is_obsolete = is_obsolete;
	}
	public Integer getIs_root() {
		return is_root;
	}
	public void setIs_root(Integer is_root) {
		this.is_root = is_root;
	}
	public String getSubontology() {
		return subontology;
	}
	public void setSubontology(String subontology) {
		this.subontology = subontology;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	
}
