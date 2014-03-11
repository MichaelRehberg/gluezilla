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
package de.mrehberg.gluezilla.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Rehberg
 * 
 */
public enum GStatus {

	PLANNED(1, "Planned"), 
	STARTED(2, "In Development"), 
	IMPLEMENTED(3,"Implemented"), 
	REVIEWED(4, "In Review"), 
	FINISHED(5, "Finished");

	private static Map<Integer, GStatus> statusEnumIntegerGStatusMap = new HashMap<Integer, GStatus>();
	private static Map<String, GStatus> statusEnumStringGStatusMap = new HashMap<String, GStatus>();

	static {
		for (GStatus g : GStatus.values()) {
			if (statusEnumIntegerGStatusMap.put(g.getID(), g) != null) {
				throw new IllegalArgumentException("duplicate id: " + g.getID());
			}
		}
		for (GStatus g : GStatus.values()) {
			if (statusEnumStringGStatusMap.put(g.getValue(), g) != null) {
				throw new IllegalArgumentException("duplicate value: "
						+ g.getValue());
			}
		}
	}
	private String value;
	private Integer ID;

	private GStatus(Integer id, String value) {
		this.value = value;
		this.ID = id;
	}

	public String getValue() {
		return value;
	}

	public Integer getID() {
		return ID;
	}

	/**
	 * Returns a {@link GStatus} by its ID.
	 * 
	 * @param id
	 * @return {@link GStatus}
	 */
	public static GStatus getByID(int id) {
		return statusEnumIntegerGStatusMap.get(id);
	}

	/**
	 * Returns a {@link GStatus} by its value.
	 * 
	 * @param value
	 * @return {@link GStatus}
	 */
	public static GStatus getByValue(String value) {
		return statusEnumStringGStatusMap.get(value);
	}
}
