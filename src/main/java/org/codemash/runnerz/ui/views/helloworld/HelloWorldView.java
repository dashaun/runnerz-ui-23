package org.codemash.runnerz.ui.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.codemash.runnerz.ui.views.MainLayout;
import org.springframework.web.client.RestTemplate;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;


    public HelloWorldView() {
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show(new RestTemplate()
                    .getForObject("http://localhost:8080/hello?name={name}", String.class, name.getValue()));
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }

}
