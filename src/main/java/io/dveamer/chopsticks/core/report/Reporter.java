package io.dveamer.chopsticks.core.report;

import io.dveamer.chopsticks.core.analytics.Analyst;
import io.dveamer.chopsticks.core.bean.Report;
import io.dveamer.chopsticks.core.bean.Result;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.util.Optional;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class Reporter {

    private final Analyst analyst;

    public Reporter(Analyst analyst) {
        this.analyst = analyst;
    }

    public Report execute(){

        Result result = null;
        try{
            result = analyst.execute();
        }catch(Exception ex){
            return reportException("Exception when analysing chopsticks", ex);
        }

        Report report = new Report();
        report.setResultData(result);
        report.setError(false);

        report.setTotalCntOfRights(result.getRightChopsticks().size());
        report.setTotalCntOfLefts(result.getLeftChopsticks().size());
        report.setNormalCnt(result.getNormalList().size());
        if( report.getTotalCntOfLefts() == report.getNormalCnt()
                && report.getTotalCntOfRights() == report.getNormalCnt() ){
            report.setResult(true);
            return report;
        }

        Optional.ofNullable(result.getLeftCrashChops()).ifPresent(o -> report.setCrashCntOfLefts(o.size()));
        Optional.ofNullable(result.getRightCrashChops()).ifPresent(o -> report.setCrashCntOfRights(o.size()));

        Optional.ofNullable(result.getLeftOnlyChops()).ifPresent(o -> report.setCntOfOnlyLefts(o.size()));
        Optional.ofNullable(result.getRightOnlyChops()).ifPresent(o -> report.setCntOfOnlyRights(o.size()));

        Optional.ofNullable(result.getLeftDuplicatedMap()).ifPresent(o-> {
            int size = StreamSupporter.streamString(o.keySet())
                        .mapToInt(k -> o.get(k).size())
                        .sum();
            report.setDuplicatedCntOfLefts(size);
        });

        Optional.ofNullable(result.getRightDuplicatedMap()).ifPresent(o-> {
            int size = StreamSupporter.streamString(o.keySet())
                        .mapToInt(k -> o.get(k).size())
                        .sum();
            report.setDuplicatedCntOfRights(size);
        });

        return report;
    }

    public static Report reportException(String msg, Exception ex){

        Report report = new Report();
        report.setResult(false);
        report.setError(true);
        report.setErrorMsg(msg);

        report.setCntOfOnlyLefts(-1);
        report.setCntOfOnlyRights(-1);
        report.setCrashCntOfLefts(-1);
        report.setCrashCntOfRights(-1);
        report.setDuplicatedCntOfLefts(-1);
        report.setDuplicatedCntOfRights(-1);
        report.setNormalCnt(-1);
        report.setTotalCntOfLefts(-1);
        report.setTotalCntOfRights(-1);

        // TODO logging exception
        ex.printStackTrace();

        return report;
    }

}
