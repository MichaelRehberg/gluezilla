/*
 * Copyright 2014 Michael Rehberg.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.mrehberg.gluezilla.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Michael Rehberg
 * 
 */

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GHotfix implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@XmlElement
	private String glueID;

	@XmlAttribute
	private String department;
	@XmlAttribute
	private String component;

	@XmlTransient
	@ManyToMany(mappedBy = "hotfixes")
	private List<GIssueTrackingItem> issues;

	@XmlAttribute
	private int priority;
	@XmlAttribute
	private String status;

	private int versionID;

	private int counter;

	public GHotfix() {
	}

	public GHotfix(GVersion version, int counter) {
		String name = version.getName();
		this.versionID = version.getID();
		glueID = name + "-" + counter;

	}

	/**
	 * @return the id
	 */
	@Override
	public int getID() {
		return id;
	}

	/**
	 * 
	 * @return the counter value
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * @return the glueID
	 */
	public String getGlueID() {
		return glueID;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(String component) {
		this.component = component;
	}

	/**
	 * @return the issues
	 */
	public List<GIssueTrackingItem> getIssues() {
		return issues;
	}

	/**
	 * @param issues
	 *            the issues to set
	 */
	public void addIssue(GIssueTrackingItem issue) {
		this.issues.add(issue);
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
