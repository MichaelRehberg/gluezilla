package de.mrehberg.gluezilla.wicket.pages;

import javax.servlet.http.HttpServletResponse;

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
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.wicket.Resources;
import de.mrehberg.gluezilla.wicket.panels.VersionSidebar;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.wicket.model.IModel;

public class BrandedPage extends WebPage {

    private static final long serialVersionUID = 8057157977690204059L;

    private final WebMarkupContainer content;

    private IModel<GProduct> product;

    @Inject
    private GluezillaFacade ejb;
    
    public BrandedPage() {
        this(new PageParameters());
    }

    public BrandedPage(PageParameters params) {
        super(params);
        product = Model.of(getProduct(params));

        Navbar navbar = new Navbar("navbar");
        navbar.setInverted(true);
        navbar.setPosition(Position.TOP);
        navbar.brandName(Model.of("Gluezilla"));
//		navbar.setBrandImage(Resources.IMAGE_LOGO, Model.of("Gluezilla"));
        createNavbarContents(navbar);
        super.add(navbar);

        final Component sidebar = createSidebar("sidebar");
        super.add(sidebar);

        content = new WebMarkupContainer("content");
        content.add(new AttributeModifier("class", new AbstractReadOnlyModel() {
            @Override
            public Object getObject() {
                return sidebar.isVisible() ? "col-sm-10" : "col-sm-12";
            }
        }));
        super.add(content);

    }

    @Override
    protected void detachModel() {
        product.detach();
        super.detachModel();
    }

    
    @Override
    public MarkupContainer add(Component... childs) {
        return content.add(childs);
    }

    protected GProduct getProduct(PageParameters params) {
        if (params.getIndexedCount() == 0) {
            return null;
        }
        String productName = params.get(0).toString();
        try {
            return ejb.getProductByName(productName);
        } catch (NoResultException e) {
            throw new AbortWithHttpErrorCodeException(HttpServletResponse.SC_NOT_FOUND, productName);
        }
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(Resources.MAIN_CSS));
        super.renderHead(response);
    }

    public GProduct getProduct() {
        return product.getObject();
    }

    protected void createNavbarContents(Navbar navbar) {
        NavbarButton<Page> menu[] = new NavbarButton[]{
            new NavbarButton<Page>(BrowsePage.class, Model.of("Browse")),
            new NavbarButton<Page>(CreatePage.class, Model.of("Create")),
            new NavbarButton<Page>(ReviewPage.class, Model.of("Review"))
        };
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT, menu));

        NavbarButton<Page> admin = new NavbarButton<Page>(EditProductPage.class, Model.of("Admin"));
        navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.RIGHT, admin));
    }

    protected Component createSidebar(String id) {
        return new VersionSidebar(id, getProduct());
    }

}
