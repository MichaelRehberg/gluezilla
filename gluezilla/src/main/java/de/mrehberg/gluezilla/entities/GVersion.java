/*
 * Copyright 2014 rehberg.
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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author rehberg
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GVersion {
    
    @Id @GeneratedValue
    @XmlElement
    private int id;
    
    @ManyToOne
    @XmlTransient
    private GProduct product;
    
    @XmlElement
    private String name;
    
    @XmlElement
    private boolean deprecated;
    
    public GVersion()
    {}
    
    public GVersion(String name, GProduct product)
    {
        this.name = name;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public GProduct getProduct() {
        return product;
    }

}