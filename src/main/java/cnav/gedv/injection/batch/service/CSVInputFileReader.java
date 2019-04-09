package cnav.gedv.injection.batch.service;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.PassThroughLineMapper;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

public class CSVInputFileReader  extends FlatFileItemReader<String> {

    public CSVInputFileReader(Resource resource) {

        super();

        // input resource
        setResource(resource);

        // default encoding
        setEncoding(StandardCharsets.UTF_8.name());

        // default line mapper
        setLineMapper(new PassThroughLineMapper());
    }

}
