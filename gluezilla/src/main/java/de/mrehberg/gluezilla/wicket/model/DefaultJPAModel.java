/**
 * 
 */
package de.mrehberg.gluezilla.wicket.model;

import org.apache.wicket.model.LoadableDetachableModel;

import de.mrehberg.gluezilla.entities.Identifiable;
import de.mrehberg.gluezilla.wicket.GluezillaSession;

/**
 * @author joe
 *
 */
public class DefaultJPAModel<T extends Identifiable> extends LoadableDetachableModel<T> {

	
	protected Class<T> type;
	
	private int primaryKey;
	
	@SuppressWarnings("unchecked")
	public DefaultJPAModel(T object) {
		super(object);
		this.type = (Class<T>) object.getClass();
		primaryKey = object.getID();
	}

	@Override
	protected T load() {
		return GluezillaSession.get().getEntityManager().getReference(type, primaryKey);
	}
	
	public static <T extends Identifiable> DefaultJPAModel<T> of(T object) {
		return new DefaultJPAModel<T>(object);
	}
	
}
