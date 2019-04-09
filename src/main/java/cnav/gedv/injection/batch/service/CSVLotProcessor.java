package cnav.gedv.injection.batch.service;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CSVLotProcessor implements ItemProcessor<String,String> {

    private int i = 0;

    public String process(String item) {
        if (i%10 == 0) {
            System.out.println(String.format("Processing line : %s - %s",i,item));
            i = 0;
        }
        i++;
        return item;
    }
}
