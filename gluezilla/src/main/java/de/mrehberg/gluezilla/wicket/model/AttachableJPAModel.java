package de.mrehberg.gluezilla.wicket.model;

import de.mrehberg.gluezilla.entities.Identifiable;

public class AttachableJPAModel<T extends Identifiable> extends DefaultJPAModel<T> {

	public AttachableJPAModel(T object) {
		super(object);
	}
	

	@Override
	protected T load() {
		try {
			return type.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
