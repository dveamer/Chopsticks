package io.dveamer.chopsticks.core.suporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by dveamer on 16. 2. 7.
 */
public class StreamSupporter<E> {

    private static final int DEFAULT_PARALLEL_THRESHOLD_SIZE = 40000; // TODO

    public static Stream<ResultSet> stream(ResultSet rs) {
        return stream(rs, DEFAULT_PARALLEL_THRESHOLD_SIZE);
    }

    public static Stream<ResultSet> stream(ResultSet rs, int parallelThresholdSize) {
        Objects.requireNonNull(rs);

        int rowCount = 0;
        try{
            if(rs.last()){
                rowCount = rs.getRow();
                rs.beforeFirst();
            }

            if( rowCount > parallelThresholdSize ){
                return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(new ResultSetIterator(rs), 0)
                        , false).parallel();
            }

        }catch(SQLException ex){
            throw new RuntimeException("SQLException when reading ResultSet", ex);
        }

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(new ResultSetIterator(rs), 0)
                , false);
    }

    public Stream<E> stream(Collection<E> collection) {
        return stream(collection, DEFAULT_PARALLEL_THRESHOLD_SIZE);
    }

    public Stream<E> stream(Collection<E> collection, int parallelThresholdSize) {
        Objects.requireNonNull(collection);

        if( collection.size() > parallelThresholdSize ){
            return collection.parallelStream();
        }

        return collection.stream();
    }


    public static Stream<String> streamString(Collection<String> collection) {
        return streamString(collection, DEFAULT_PARALLEL_THRESHOLD_SIZE);
    }

    public static Stream<String> streamString(Collection<String> collection, int parallelThresholdSize) {
        Objects.requireNonNull(collection);

        if( collection.size() > parallelThresholdSize ){
            return collection.parallelStream();
        }
        return collection.stream();
    }



    public static Stream<String> fileLineStream(String filePath) {
        return fileLineStream(filePath, DEFAULT_PARALLEL_THRESHOLD_SIZE);
    }

    public static Stream<String> fileLineStream(String filePath, int parallelThresholdSize) {
        Objects.requireNonNull(filePath);

        Path path = Paths.get(filePath);
        File file = path.toFile();

        if(!file.exists()){
            throw new RuntimeException("File Not Found : " + filePath);
        }

        try{
            // TODO
//            return Files.lines(Paths.get(filePath)).parallel();
            return Files.lines(Paths.get(filePath));

        }catch(IOException ex){
            throw new RuntimeException("IOException when reading file lines", ex);
        }
    }


}
