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

/**
 * @author Michael Rehberg
 * 
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GIssueTrackingItem implements Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@XmlElement
	private String issueTrackingSystem;

	@XmlAttribute
	private String issueTrackingSystemID;

	@ManyToMany
	private List<GHotfix> hotfixes;

	@XmlAttribute
	private String issueTrackingSystemDescription;

	/**
	 * @return the issueTrackingSystem
	 */
	public String getIssueTrackingSystem() {
		return issueTrackingSystem;
	}

	/**
	 * @param issueTrackingSystem
	 *            the issueTrackingSystem to set
	 */
	public void setIssueTrackingSystem(String issueTrackingSystem) {
		this.issueTrackingSystem = issueTrackingSystem;
	}

	/**
	 * @return the issueTrackingSystemID
	 */
	public String getIssueTrackingSystemID() {
		return issueTrackingSystemID;
	}

	/**
	 * @param issueTrackingSystemID
	 *            the issueTrackingSystemID to set
	 */
	public void setIssueTrackingSystemID(String issueTrackingSystemID) {
		this.issueTrackingSystemID = issueTrackingSystemID;
	}

	/**
	 * @return the issueTrackingSystemDescription
	 */
	public String getIssueTrackingSystemDescription() {
		return issueTrackingSystemDescription;
	}

	/**
	 * @param issueTrackingSystemDescription
	 *            the issueTrackingSystemDescription to set
	 */
	public void setIssueTrackingSystemDescription(
			String issueTrackingSystemDescription) {
		this.issueTrackingSystemDescription = issueTrackingSystemDescription;
	}

	/**
	 * @return the id
	 */
	@Override
	public int getID() {
		return id;
	}

}
