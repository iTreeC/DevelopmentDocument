package com.itree.entity;
// Generated 2015-11-15 16:02:00 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * TNewstype generated by hbm2java
 */
public class TNewstype implements java.io.Serializable {

	private Integer id;
	private TNews TNews;
	private String newsType;
	private Set<TNews> TNewses = new HashSet<TNews>(0);

	public TNewstype() {
	}

	public TNewstype(TNews TNews, String newsType) {
		this.TNews = TNews;
		this.newsType = newsType;
	}

	public TNewstype(TNews TNews, String newsType, Set<TNews> TNewses) {
		this.TNews = TNews;
		this.newsType = newsType;
		this.TNewses = TNewses;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TNews getTNews() {
		return this.TNews;
	}

	public void setTNews(TNews TNews) {
		this.TNews = TNews;
	}

	public String getNewsType() {
		return this.newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public Set<TNews> getTNewses() {
		return this.TNewses;
	}

	public void setTNewses(Set<TNews> TNewses) {
		this.TNewses = TNewses;
	}

}