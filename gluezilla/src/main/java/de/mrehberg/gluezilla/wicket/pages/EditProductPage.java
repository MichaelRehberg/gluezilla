package de.mrehberg.gluezilla.wicket.pages;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.BootstrapButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons.Type;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.BootstrapForm;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormGroup;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.FormType;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior;
import de.agilecoders.wicket.core.markup.html.bootstrap.form.InputBehavior.Size;

public class EditProductPage extends BrandedPage {
	
	private static final long serialVersionUID = -3608128785543543610L;

	public EditProductPage() {
		BootstrapForm<Void> form = new BootstrapForm<Void>("product-form");
		form.type(FormType.Horizontal);
		
		FormGroup nameGroup = new FormGroup("name-group", Model.of("Name"), Model.of("the name of the product"));
		TextField<String> nameField = new TextField<String>("name");
		nameField.add(new InputBehavior(Size.Medium));
		nameField.setRequired(true);
		nameGroup.add(nameField);
		form.add(nameGroup);
		
		FormGroup descriptionGroup = new FormGroup("description-group", Model.of("Description"), Model.of("a short description for the product"));
		TextField<String> descriptionField = new TextField<String>("description");
		descriptionField.add(new InputBehavior(Size.Medium));
		descriptionGroup.add(descriptionField);
		form.add(descriptionGroup);
		
		FileUploadField imageUpload = new FileUploadField("image-upload");
		FormGroup uploadGroup = new FormGroup("upload-group", Model.of("Image"), Model.of("uploads an image for the product"));
		uploadGroup.add(imageUpload);
		imageUpload.add(new InputBehavior(Size.Medium));
		form.add(uploadGroup);
		
		BootstrapButton submit = new BootstrapButton("submit", Type.Primary);
		submit.setModelObject("Submit");
		form.add(submit);
		
		add(form);
	}

}
