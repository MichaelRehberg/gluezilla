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

import de.mrehberg.gluezilla.entities.GProduct;
import de.mrehberg.gluezilla.entities.GVersion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author rehberg
 */
@Stateless
@Path("/version")
public class GVersionResource {

    @PersistenceContext
    EntityManager manager;

    @GET
    public List<GVersion> getGVersions(@QueryParam("p") int productId) {
        GProduct product = manager.find(GProduct.class, productId);
        if(product == null)
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Product required").build());

        return product.getVersions();
    }

    @POST
    public int createGVersion(@FormParam(value = "n") String name, @FormParam(value ="p") int productID) {
        GProduct product = manager.find(GProduct.class, productID);
        if(product == null)
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity("Product required").build());
        
        GVersion gVersion = new GVersion(name, product);
        manager.persist(gVersion);
        return gVersion.getId();
    }
}
