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
package de.mrehberg.gluezilla.wicket.model;

import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.entities.Identifiable;
import javax.inject.Inject;
import org.apache.wicket.cdi.NonContextual;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

/**
 * {@link IModel} implementation for working with JPA Entities.
 */
public class EntityModel<T extends Identifiable> extends LoadableDetachableModel<T> {

    private Class<T> clazz;
    private int id;

    @Inject
    GluezillaFacade facade;
    
    public EntityModel(T object) {
        super();
        NonContextual.of(EntityModel.class).postConstruct(this);
        setObject(object);
    }

    @Override
    public void setObject(T object) {
        super.setObject(object);
        this.clazz = (Class<T>) object.getClass();
        this.id = object.getID();
    }

    @Override
    public void detach() {
        if (this.id != 0) {
            // detach only if the object has yet been persisted
            super.detach();
        }
    }
    
    @Override
    protected T load() {
        return facade.load(clazz, id);
    }

}
