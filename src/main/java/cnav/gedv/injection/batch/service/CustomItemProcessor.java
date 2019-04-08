package cnav.gedv.injection.batch.service;

import cnav.gedv.injection.batch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomItemProcessor implements ItemProcessor<User, User> {

    public User process(User item) {
        System.out.println("Processing..." + item);
        return item;
    }
}
