package com.itree.entity;
// Generated 2015-11-15 16:02:00 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TNews generated by hbm2java
 */
public class TNews implements java.io.Serializable {

	private Integer id;
	private TUser TUser;
	private TNewstype TNewstype;
	private TFile TFile;
	private String newsProfile;
	private String newsContent;
	private String newsTitle;
	private Date newsDate;
	private int newsStatus;
	private Set<TNewstype> TNewstypes = new HashSet<TNewstype>(0);

	public TNews() {
	}

	public TNews(TUser TUser, TNewstype TNewstype, String newsTitle, int newsStatus) {
		this.TUser = TUser;
		this.TNewstype = TNewstype;
		this.newsTitle = newsTitle;
		this.newsStatus = newsStatus;
	}

	public TNews(TUser TUser, TNewstype TNewstype, TFile TFile, String newsProfile, String newsContent,
			String newsTitle, Date newsDate, int newsStatus, Set<TNewstype> TNewstypes) {
		this.TUser = TUser;
		this.TNewstype = TNewstype;
		this.TFile = TFile;
		this.newsProfile = newsProfile;
		this.newsContent = newsContent;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.newsStatus = newsStatus;
		this.TNewstypes = TNewstypes;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

	public TNewstype getTNewstype() {
		return this.TNewstype;
	}

	public void setTNewstype(TNewstype TNewstype) {
		this.TNewstype = TNewstype;
	}

	public TFile getTFile() {
		return this.TFile;
	}

	public void setTFile(TFile TFile) {
		this.TFile = TFile;
	}

	public String getNewsProfile() {
		return this.newsProfile;
	}

	public void setNewsProfile(String newsProfile) {
		this.newsProfile = newsProfile;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Date getNewsDate() {
		return this.newsDate;
	}

	public void setNewsDate(Date newsDate) {
		this.newsDate = newsDate;
	}

	public int getNewsStatus() {
		return this.newsStatus;
	}

	public void setNewsStatus(int newsStatus) {
		this.newsStatus = newsStatus;
	}

	public Set<TNewstype> getTNewstypes() {
		return this.TNewstypes;
	}

	public void setTNewstypes(Set<TNewstype> TNewstypes) {
		this.TNewstypes = TNewstypes;
	}

}
