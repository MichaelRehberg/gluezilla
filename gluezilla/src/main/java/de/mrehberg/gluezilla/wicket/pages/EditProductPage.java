package de.mrehberg.gluezilla.wicket.pages;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
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
import de.mrehberg.gluezilla.wicket.model.EntityModel;

public class EditProductPage extends DefaultPage<GProduct> {

    private static final long serialVersionUID = -3608128785543543610L;

    @Inject
    private GluezillaFacade facade;

    private FileUploadField imageUpload;

    public EditProductPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        BootstrapForm<GProduct> form = new BootstrapForm<GProduct>("product-form", getModel()) {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                List<FileUpload> images = imageUpload.getConvertedInput();
                if (images != null && !images.isEmpty()) {
                    FileUpload upload = images.get(0);
                    getModel().getObject().setImage(upload.getBytes());
                }
                submit(getModel());
                setResponsePage(BrowsePage.class, getResolver().expand(getModelObject()));
            }
        };
        form.type(FormType.Horizontal);

        FormGroup nameGroup = new FormGroup("name-group", Model.of("Name"), Model.of("the name of the product"));
        IModel<String> nameModel = PropertyModel.of(getModel(), "productName");
        TextField<String> nameField = new TextField<String>("name", nameModel);

        nameField.add(new InputBehavior(Size.Medium));
        nameField.setRequired(true);
        nameGroup.add(nameField);
        form.add(nameGroup);

        FormGroup descriptionGroup = new FormGroup("description-group", Model.of("Description"), Model.of("a short description for the product"));
        IModel<String> descriptionModel = PropertyModel.of(getModel(), "productDescription");
        TextField<String> descriptionField = new TextField<String>("description", descriptionModel);
        descriptionField.add(new InputBehavior(Size.Medium));
        descriptionGroup.add(descriptionField);
        form.add(descriptionGroup);

        imageUpload = new FileUploadField("image-upload");
        FormGroup uploadGroup = new FormGroup("upload-group", Model.of("Image"), Model.of("uploads an image for the product"));
        uploadGroup.add(imageUpload);
        imageUpload.add(new InputBehavior(Size.Medium));
        form.add(uploadGroup);

        BootstrapButton submit = new BootstrapButton("submit", Type.Primary);
        submit.setModelObject("Submit");
        form.add(submit);

        add(form);
    }

    @Override
    protected IModel<GProduct> createModel(PageParameters pageParameters) {
        IModel<GProduct> model = super.createModel(pageParameters);
        if (model == null) {
            GProduct p = new GProduct();
            return new EntityModel<>(p);
        }
        return model;
    }

    protected void submit(IModel<GProduct> model) {
        model.setObject(facade.persist(model.getObject()));
    }

}
