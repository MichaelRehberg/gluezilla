/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.mrehberg.gluezilla.wicket.pages;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

import de.mrehberg.gluezilla.wicket.Resources;

/**
 *
 * @author joe
 */
public abstract class BasicPage extends WebPage{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1941762591345291206L;

	@Override
    public void renderHead(IHeaderResponse response) {
        response.render(CssHeaderItem.forReference(Resources.MAIN_CSS));
        super.renderHead(response); 
    }
}
