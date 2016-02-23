package io.dveamer.chopsticks.core.suporter;

import java.sql.ResultSet;
import java.util.Iterator;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class ResultSetIterator implements Iterator<ResultSet> {

    private ResultSet rs;
    public ResultSetIterator(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public boolean hasNext() {
        try{
            return rs.next();
        }catch(Exception ex) {
            return false;
        }
    }

    @Override
    public ResultSet next() {
        return rs;
    }
}
