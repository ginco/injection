package cnav.gedv.injection.batch.service;

import cnav.gedv.injection.batch.model.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomItemWriter implements ItemWriter<User> {

    public void write(List<? extends User> items) {
        System.out.println("Writing a list of " + items.size());
        for (User item : items) {
            System.out.println("Item is = " + item.toString());
        }
    }


}
