package cnav.gedv.injection.batch.service;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.SimpleResourceSuffixCreator;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.core.io.Resource;

import java.util.List;


public class CSVOutputFileWriter extends MultiResourceItemWriter<String> {

    public CSVOutputFileWriter(Resource resource) {

        // suffix creator
        setResourceSuffixCreator(new SimpleResourceSuffixCreator());

        // output pattern
        setResource(resource);

        // chunk size
        setItemCountLimitPerResource(200);

        // delegate
        FlatFileItemWriter<String> writer = new FlatFileItemWriter<String>();
        writer.setLineAggregator(new PassThroughLineAggregator<>());
        setDelegate(writer);

    }

    @Override
    public void write(List<? extends String> items) throws Exception {
        super.write(items);
        System.out.println("Chunk written to file : " + items.size());
    }
}
