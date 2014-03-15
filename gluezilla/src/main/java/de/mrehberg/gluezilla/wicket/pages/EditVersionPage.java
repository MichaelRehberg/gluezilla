package de.mrehberg.gluezilla.wicket.pages;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior.Size;
import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import de.mrehberg.gluezilla.wicket.model.EntityModel;

public class EditVersionPage extends DefaultPage<GVersion> {

    private static final long serialVersionUID = -3608128785543543610L;

    private IModel<GProduct> productModel;

    @Inject
    private GluezillaFacade facade;

    public EditVersionPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        BootstrapForm<GVersion> form = new BootstrapForm<GVersion>("form", getModel()) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                submit(getModel());
                setResponsePage(BrowsePage.class, getResolver().expand(productModel.getObject()));
            }
        };
        form.type(FormType.Horizontal);

        FormGroup nameGroup = new FormGroup("name-group", Model.of("Name"), Model.of("the name of the version"));
        IModel<String> nameModel = PropertyModel.of(getModel(), "name");
        TextField<String> nameField = new TextField<String>("name", nameModel);

        nameField.add(new InputBehavior(Size.Medium));
        nameField.setRequired(true);
        nameGroup.add(nameField);
        form.add(nameGroup);

//        FormGroup deprecatedGroup = new FormGroup("deprecated-group", Model.of("Deprecated"), Model.of("If this version is deprecated"));
        IModel<Boolean> deprecatedModel = PropertyModel.of(getModel(), "deprecated");
        CheckBox deprecatedField = new CheckBox("deprecated", deprecatedModel);

//        deprecatedField.add(new InputBehavior(Size.Medium));
//        deprecatedGroup.add(deprecatedField);
        form.add(deprecatedField);

        BootstrapButton submit = new BootstrapButton("submit", Type.Primary);
        submit.setModelObject("Submit");
        form.add(submit);

        add(form);
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        if (productModel != null) {
            productModel.detach();
        }
    }

    @Override
    protected IModel<GVersion> createModel(PageParameters pageParameters) {

        Object result = getResolver().resolve(pageParameters);
        if (result instanceof GProduct) {
            //it's a new version
            GProduct product = (GProduct) result;
            GVersion version = new GVersion();
            version.setProduct(product);
            result = version;

        }
        if (result instanceof GVersion) {
            GVersion version = (GVersion) result;
            productModel = new EntityModel<>(version.getProduct());
            return new EntityModel<>(version);
        }
        return null;
    }

    protected void submit(IModel<GVersion> model) {
        if (model.getObject().getProduct() == null) {
            model.getObject().setProduct(productModel.getObject());
        }
        model.setObject(facade.persist(model.getObject()));
    }

}
