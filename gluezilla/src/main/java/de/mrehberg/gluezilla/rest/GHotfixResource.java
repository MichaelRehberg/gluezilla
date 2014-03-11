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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.mrehberg.gluezilla.entities.GHotfix;
import de.mrehberg.gluezilla.entities.GIssueTrackingItem;
import de.mrehberg.gluezilla.entities.GVersion;

/**
 * @author Michael Rehberg
 * 
 */
@Stateless
@Path("/glueitem")
public class GHotfixResource {

	@PersistenceContext
	EntityManager manager;

	@GET
	public List<GHotfix> getAllGItems() {
		return manager.createQuery("Select hf from GHotfix hf").getResultList();
	}

	@GET
	public List<GHotfix> getAllGItemsOfVersion(
			@FormParam(value = "v") int versionID) {
		return manager.createQuery(
				"Select hf from GHotfix hf where hf.version = '" + versionID
						+ "'").getResultList();
	}

	@POST
	public int createGHotFix(@FormParam(value = "v") int versionID,
			@FormParam(value = "d") String department,
			@FormParam(value = "c") String component,
			@FormParam(value = "i") List<String> issuedItemIDs,
			@FormParam(value = "p") int priority) {

		List<?> resultList = manager.createQuery(
				"Select hf from GHotfix hf order by hf.id").getResultList();
		GHotfix lastHF = (GHotfix) resultList.get(resultList.size() - 1);

		GVersion version = manager.find(GVersion.class, versionID);

		GHotfix hf = new GHotfix(version, lastHF.getCounter() + 1);

		hf.setComponent(component);
		hf.setDepartment(department);
		hf.setPriority(priority);

		List<GIssueTrackingItem> items = new ArrayList<GIssueTrackingItem>();
		for (Iterator<String> iterator = issuedItemIDs.iterator(); iterator
				.hasNext();) {
			String issueID = (String) iterator.next();
			GIssueTrackingItem gIssueTrackingItem = manager.find(
					GIssueTrackingItem.class, issueID);
			if (gIssueTrackingItem == null)
				throw new WebApplicationException(Response
						.status(Status.BAD_REQUEST)
						.entity("IssueItem with id = " + issueID
								+ " does not exist!").build());
			hf.addIssue(gIssueTrackingItem);
		}
		manager.persist(hf);
		return hf.getID();
	}

}
