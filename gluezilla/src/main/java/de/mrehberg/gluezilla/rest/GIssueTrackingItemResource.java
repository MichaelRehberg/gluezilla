/*
 * Copyright 2014 Michael Rehberg.
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

import de.mrehberg.gluezilla.entities.GIssueTrackingItem;

/**
 * @author Michael Rehberg
 * 
 */
@Stateless
@Path("/issueItems")
public class GIssueTrackingItemResource {

	@PersistenceContext
	EntityManager manager;

	@GET
	public GIssueTrackingItem getGIssueItem(
			@QueryParam(value = "i") int issueTrackingItemID) {
		GIssueTrackingItem issueItem = manager.find(GIssueTrackingItem.class,
				issueTrackingItemID);
		return issueItem;
	}

	@GET
	public List<GIssueTrackingItem> getAllGIssueItem() {
		List<GIssueTrackingItem> resultList = manager.createQuery(
				"Select git from GIssueTrackingItem git").getResultList();
		return resultList;
	}

	@POST
	public int createGIssueTrackingItem(@FormParam(value = "s") String system,
			@FormParam(value = "n") String name, @FormParam(value = "d") String description) {
		
		if (system == null)
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity("Issuetracking system required")
					.build());
		
		if (name == null)
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity("Issuetracking name(id) required")
					.build());
		
		if (description == null)
			throw new WebApplicationException(Response
					.status(Status.BAD_REQUEST).entity("Issuetracking description required")
					.build());

		GIssueTrackingItem item = new GIssueTrackingItem();
		item.setIssueTrackingSystem(system);
		item.setIssueTrackingSystemID(name);
		item.setIssueTrackingSystemDescription(description);
		manager.persist(item);
		return item.getId();
	}
}
