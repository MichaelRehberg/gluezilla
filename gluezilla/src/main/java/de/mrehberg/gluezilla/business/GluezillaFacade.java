/*
 * Copyright 2014 mt.
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
package de.mrehberg.gluezilla.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.Identifiable;

@Stateless
public class GluezillaFacade {

    @PersistenceContext
    EntityManager manager;

    public List<GProduct> getAllProducts() {
        return manager.createQuery("SELECT p from GProduct p").getResultList();
    }

    public GProduct getProductByName(String name) throws NotFoundException {
        try {
            return manager.createQuery("SELECT p from GProduct p WHERE p.productName=?1", GProduct.class)
                    .setParameter(1, name).getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundException();
        }
    }
    
    public void persist(Identifiable object){
    	if(manager.contains(object))
    		//already persisted
    		manager.merge(object);
    	else
    		manager.persist(object);
    }
}
