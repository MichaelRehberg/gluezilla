package de.mrehberg.gluezilla.wicket.util;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mrehberg.gluezilla.entities.Identifiable;

public interface EntityResolver {

	String PRODUCT = "product";
	String VERSION = "version";
	String HOTFIX = "hotfix";
	
	public <T extends Identifiable> T resolve(PageParameters params);
	
	
}
