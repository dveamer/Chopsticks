package io.dveamer.chopsticks;

import io.dveamer.chopsticks.core.analytics.Analyst;
import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Report;
import io.dveamer.chopsticks.core.report.Reporter;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class Controller {

    protected Controller(){

    }

    public Report execute(final ReceptionData receptionData){

        try{
            receptionData.getLeftParser().forEach(listSupplier -> receptionData.getLeftChopsticks().addAll(listSupplier.get()));
        }catch(Exception ex){
            return Reporter.reportException("Error when parsing left chopsticks", ex);
        }

        if(receptionData.getLeftChopsticks().size()<1){
            return Reporter.reportException("Left chopsticks have no data", null);
        }

        try{
            receptionData.getRightParser().forEach(listSupplier -> receptionData.getRightChopsticks().addAll(listSupplier.get()));
        }catch(Exception ex){
            return Reporter.reportException("Error when parsing right chopsticks", ex);
        }

        if(receptionData.getRightChopsticks().size()<1){
            return Reporter.reportException("Right chopsticks have no data", null);
        }

        StreamSupporter<Chopstick> streamSupporter = new StreamSupporter<>();

        List<Chopstick> lefts = receptionData.getLeftChopsticks();
        try{
            if(receptionData.getLeftMapper()!=null){
                lefts = streamSupporter.stream(receptionData.getLeftChopsticks())
                        .map(receptionData.getLeftMapper())
                        .collect(Collectors.toList());
            }
        }catch(Exception ex){
            return Reporter.reportException("Error when mapping left chopsticks", ex);
        }

        List<Chopstick> rights = receptionData.getRightChopsticks();
        try{
            if(receptionData.getRightMapper()!=null){
                rights = streamSupporter.stream(receptionData.getRightChopsticks())
                        .map(receptionData.getRightMapper())
                        .collect(Collectors.toList());
            }
        }catch(Exception ex){
            return Reporter.reportException("Error when mapping right chopsticks", ex);
        }

        return new Reporter(new Analyst(receptionData.getCompare(), lefts, rights)).execute();
    }

}
