/*
 * Copyright 2014 mt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.mrehberg.gluezilla.wicket.pages;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.http.flow.AbortWithHttpErrorCodeException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.mrehberg.gluezilla.entities.GProduct;


/**
 *
 * @author mt
 */
public class BrowsePage extends BrandedPage {

	private static final long serialVersionUID = -4829807264007202804L;
	
	public BrowsePage(PageParameters params) {
		super(params);
		GProduct product = getProduct();
		if(product==null)
			throw new AbortWithHttpErrorCodeException(HttpServletResponse.SC_NOT_FOUND);
		String message = "Browse {0} fixes";
		add(new Label("title", MessageFormat.format(message, product.getProductName())));
	}
    
}
