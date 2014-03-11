/**
 * 
 */
package de.mrehberg.gluezilla.wicket.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.wicket.model.LoadableDetachableModel;

import de.mrehberg.gluezilla.entities.Identifiable;

/**
 * @author joe
 *
 */
public class DefaultJPAModel<T extends Identifiable> extends LoadableDetachableModel<T> {

	@Inject
	private EntityManager entityManager;
	
	private Class<T> type;
	
	private int primaryKey;
	
	@SuppressWarnings("unchecked")
	public DefaultJPAModel(T object) {
		super(object);
		this.type = (Class<T>) object.getClass();
		primaryKey = object.getID();
	}

	@Override
	protected T load() {
		return entityManager.getReference(type, primaryKey);
	}
	
	public static <T extends Identifiable> DefaultJPAModel<T> of(T object) {
		return new DefaultJPAModel<T>(object);
	}
	
}
