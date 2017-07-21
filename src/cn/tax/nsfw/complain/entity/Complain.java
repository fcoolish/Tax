package cn.tax.nsfw.complain.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Complain entity. @author MyEclipse Persistence Tools
 */

public class Complain implements java.io.Serializable {

	// Fields

	private String compId;
	private String compCompany;
	private String compName;
	private String compMobile;
	private Boolean isNm;
	private Timestamp compTime;
	private String compTitle;
	private String toCompName;
	private String toCompDept;
	private String compContent;
	private String state;
	private Set complainReplies = new HashSet(0);
	
	//状态
	public static String COMPLAIN_STATE_UNDONE = "0";
	public static String COMPLAIN_STATE_DONE = "1";
	public static String COMPLAIN_STATE_INVALID = "2";
	public static Map<String, String> COMPLAIN_STATE_MAP;
	static {
		COMPLAIN_STATE_MAP = new HashMap<String, String>();
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_UNDONE, "待受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_DONE, "已受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_INVALID, "已失效");
	}

	// Constructors

	/** default constructor */
	public Complain() {
	}

	/** minimal constructor */
	public Complain(String compTitle) {
		this.compTitle = compTitle;
	}

	/** full constructor */
	public Complain(String compCompany, String compName, String compMobile, Boolean isNm, Timestamp compTime, String compTitle, String toCompName, String toCompDept, String compContent, String state,
			Set complainReplies) {
		this.compCompany = compCompany;
		this.compName = compName;
		this.compMobile = compMobile;
		this.isNm = isNm;
		this.compTime = compTime;
		this.compTitle = compTitle;
		this.toCompName = toCompName;
		this.toCompDept = toCompDept;
		this.compContent = compContent;
		this.state = state;
		this.complainReplies = complainReplies;
	}

	// Property accessors

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompCompany() {
		return this.compCompany;
	}

	public void setCompCompany(String compCompany) {
		this.compCompany = compCompany;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompMobile() {
		return this.compMobile;
	}

	public void setCompMobile(String compMobile) {
		this.compMobile = compMobile;
	}

	public Boolean getIsNm() {
		return this.isNm;
	}

	public void setIsNm(Boolean isNm) {
		this.isNm = isNm;
	}

	public Timestamp getCompTime() {
		return this.compTime;
	}

	public void setCompTime(Timestamp compTime) {
		this.compTime = compTime;
	}

	public String getCompTitle() {
		return this.compTitle;
	}

	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	public String getToCompName() {
		return this.toCompName;
	}

	public void setToCompName(String toCompName) {
		this.toCompName = toCompName;
	}

	public String getToCompDept() {
		return this.toCompDept;
	}

	public void setToCompDept(String toCompDept) {
		this.toCompDept = toCompDept;
	}

	public String getCompContent() {
		return this.compContent;
	}

	public void setCompContent(String compContent) {
		this.compContent = compContent;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set getComplainReplies() {
		return this.complainReplies;
	}

	public void setComplainReplies(Set complainReplies) {
		this.complainReplies = complainReplies;
	}

}