package de.mrehberg.gluezilla.wicket.pages;

import javax.inject.Inject;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.mrehberg.gluezilla.wicket.Resources;
import de.mrehberg.gluezilla.wicket.util.EntityResolver;

public class BrandedPage extends WebPage {

    private static final long serialVersionUID = 8057157977690204059L;

    private WebMarkupContainer content;
    
	@Inject
	private EntityResolver resolver;
    
    public BrandedPage() {
        this(new PageParameters());
    }

    public BrandedPage(PageParameters params) {
        super(params);
        content = new WebMarkupContainer("content");
        final Component sidebar = createSidebar("sidebar");
        super.add(sidebar);
        content.add(new AttributeModifier("class", new AbstractReadOnlyModel() {
            @Override
            public Object getObject() {
                return sidebar.isVisible() ? "col-sm-10" : "col-sm-12";
            }
        }));
        super.add(content);
    }
    
    @Override
    protected void onInitialize() {
    	super.onInitialize();
        Navbar navbar = new Navbar("navbar");
        navbar.setInverted(true);
        navbar.setPosition(Position.TOP);
        navbar.brandName(Model.of("Gluezilla"));
//		navbar.setBrandImage(Resources.IMAGE_LOGO, Model.of("Gluezilla"));
        createNavbarContents(navbar);
        super.add(navbar);



    }

    
    @Override
    public MarkupContainer add(Component... children) {
    	if(children!=null)
    		content.add(children);
    	return content;
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(Resources.MAIN_CSS));
        super.renderHead(response);
    }

    protected void createNavbarContents(Navbar navbar) {
    }

    protected Component createSidebar(String id) {
        WebMarkupContainer dummy = new WebMarkupContainer(id);
        dummy.setVisible(false);
        return dummy;
    }
    
	public EntityResolver getResolver() {
		return resolver;
	}

}
