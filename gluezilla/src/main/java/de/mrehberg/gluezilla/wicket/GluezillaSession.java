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
package de.mrehberg.gluezilla.wicket;

import de.mrehberg.gluezilla.entities.GProduct;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 *
 * @author mt
 */
public class GluezillaSession extends WebSession {

    public static GluezillaSession get() {
        return (GluezillaSession) WebSession.get();
    }

    private GProduct selectedProduct = null;

    public GluezillaSession(Request request) {
        super(request);
    }

    public GProduct getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(GProduct selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

}