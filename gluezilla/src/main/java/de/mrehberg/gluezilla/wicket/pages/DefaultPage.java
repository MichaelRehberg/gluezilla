package de.mrehberg.gluezilla.wicket.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mrehberg.gluezilla.entities.Identifiable;
import de.mrehberg.gluezilla.wicket.model.DefaultJPAModel;

public class DefaultPage<T extends Identifiable> extends BrandedPage {
	
	private IModel<T> model;
	
	public DefaultPage(PageParameters params) {
		super(params);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		if(model!=null)
			model.detach();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		model = createModel(getPageParameters());
	}
	
	protected IModel<T> createModel(PageParameters pageParameters) {
		T result = getResolver().resolve(pageParameters);
		if(result!=null)
			return new DefaultJPAModel<T>(result);
		return null;
	}

	public IModel<T> getModel() {
		return model;
	}
}
