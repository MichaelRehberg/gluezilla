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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Michael Rehberg
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "productName" }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GProduct implements Serializable, Identifiable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "product")
	@XmlTransient
	private List<GVersion> versions = new ArrayList<GVersion>();

	@Lob
	private byte[] imageResource;

	@Column(nullable=false)
	private String productName;

	private String productDescription;

	public int getID() {
		return id;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<GVersion> getVersions() {
		return versions;
	}

	public void setImage(byte[] image) {
		this.imageResource = image;
	}

	public byte[] getImage() {
		return imageResource;
	}
}
