/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket.pages;

import de.mrehberg.gluezilla.wicket.Resources;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.PriorityHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.ResourceReference;

/**
 *
 * @author joe
 */
public abstract class BasicPage extends WebPage{

    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(new PriorityHeaderItem(CssHeaderItem.forReference(Resources.MAIN_CSS)));
        super.renderHead(response); 
    }
    
    
    
}
