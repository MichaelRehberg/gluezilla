package de.mrehberg.gluezilla.wicket.util;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.business.NotFoundException;
import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.Identifiable;

public class EntityResolverImpl implements EntityResolver {

	@Inject
	private GluezillaFacade facade;
	private static Logger LOG = LoggerFactory.getLogger(EntityResolverImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Identifiable> T resolve(PageParameters params) {
		Object result = null;
		try {
			if (!params.get(PRODUCT).isEmpty()) {
				result = facade.getProductByName(params.get(PRODUCT)
						.toString());
			}
		} catch (NotFoundException e) {
			LOG.warn("Object not found: "+params,e);
		}
		return (T) result;
	}

	@Override
	public PageParameters expand(Identifiable object) {
		PageParameters params = new PageParameters();
		if (object instanceof GProduct) {
			GProduct product = (GProduct) object;
			params.add(PRODUCT, product.getProductName());
		}
		return params;
	}

}
