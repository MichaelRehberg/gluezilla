/*
 * Copyright 2014 rehberg.
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

package de.mrehberg.gluezilla.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.mrehberg.gluezilla.business.GluezillaFacade;
import de.mrehberg.gluezilla.entities.GProduct;

/**
 * 
 * @author rehberg
 */
@Stateless
@Path("/product")
public class GProductResource {

        @Inject
        GluezillaFacade facade;
        
	@PersistenceContext
	EntityManager manager;

	@GET
	public GProduct getProduct(@QueryParam(value = "p") int productId) {

		GProduct product = manager.find(GProduct.class, productId);

		if (product == null)
			throw new WebApplicationException(
					Response.status(Status.BAD_REQUEST)
							.entity("Product witn id=" + productId
									+ " does not exist!").build());
		return product;
	}

	@GET
	@Path("all")
	public List<GProduct> getProducts() {
		return facade.getAllProducts();
	}

	@POST
	public int createProduct(@FormParam(value = "n") String name,
			@FormParam(value = "d") String description) {
		if (name == null)
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity("Product name required")
					.build());

		if (description == null)
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST)
					.entity("Product description required").build());

		GProduct gProduct = new GProduct();
		gProduct.setProductName(name);
		gProduct.setProductDescription(description);
		manager.persist(gProduct);
		return gProduct.getID();
	}
}
