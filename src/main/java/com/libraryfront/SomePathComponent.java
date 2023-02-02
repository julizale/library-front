package com.libraryfront;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("some/path")
public class SomePathComponent extends Div {
    public SomePathComponent() {
        setText("Hello @Route!");
    }
}
