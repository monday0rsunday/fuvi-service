package com.broduce.fuvi.service.model;

public class Item {

	private long id;
	private String src;
	private String title;
	private String sapo;
	private String cover;
	private String link;
	private String site;
	private long ctime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSapo() {
		return sapo;
	}

	public void setSapo(String sapo) {
		this.sapo = sapo;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String gifUrl) {
		this.link = gifUrl;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

}
