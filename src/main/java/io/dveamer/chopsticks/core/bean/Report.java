package io.dveamer.chopsticks.core.bean;

import java.util.StringJoiner;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class Report {

    private boolean result = false;

    private boolean error = false;
    private String errorMsg = "";

    private int totalCntOfLefts = 0;
    private int totalCntOfRights = 0;

    private int normalCnt = 0;

    private int cntOfOnlyLefts = 0;
    private int cntOfOnlyRights = 0;


    private int duplicatedCntOfLefts = 0;
    private int duplicatedCntOfRights = 0;

    private int crashCntOfLefts = 0;
    private int crashCntOfRights = 0;

    private Result resultData = null;


    public String printReport(){
        StringJoiner mainJoiner = new StringJoiner(", ", "{", "}");

        return mainJoiner
                .add("result" + " : " + Boolean.toString(result))
                .add("error" + " : " + Boolean.toString(error))
                .add("errorMsg" + " : " + errorMsg)
                .add("totalCntOfLefts" + " : " + Integer.toString(totalCntOfLefts))
                .add("totalCntOfRights" + " : " + Integer.toString(totalCntOfRights))
                .add("normalCnt" + " : " + Integer.toString(normalCnt))
                .add("cntOfOnlyLefts" + " : " + Integer.toString(cntOfOnlyLefts))
                .add("cntOfOnlyRights" + " : " + Integer.toString(cntOfOnlyRights))
                .add("duplicatedCntOfLefts" + " : " + Integer.toString(duplicatedCntOfLefts))
                .add("duplicatedCntOfRights" + " : " + Integer.toString(duplicatedCntOfRights))
                .add("crashCntOfLefts" + " : " + Integer.toString(crashCntOfLefts))
                .add("crashCntOfRights" + " : " + Integer.toString(crashCntOfRights))
                .toString();
    }

    public Result getResultData(){
        return resultData;
    }

    public Report setResult(boolean result) {
        this.result = result;
        return this;
    }

    public Report setError(boolean error) {
        this.error = error;
        return this;
    }

    public Report setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Report setTotalCntOfLefts(int totalCntOfLefts) {
        this.totalCntOfLefts = totalCntOfLefts;
        return this;
    }

    public Report setTotalCntOfRights(int totalCntOfRights) {
        this.totalCntOfRights = totalCntOfRights;
        return this;
    }

    public Report setNormalCnt(int normalCnt) {
        this.normalCnt = normalCnt;
        return this;
    }

    public Report setCntOfOnlyLefts(int cntOfOnlyLefts) {
        this.cntOfOnlyLefts = cntOfOnlyLefts;
        return this;
    }

    public Report setCntOfOnlyRights(int cntOfOnlyRights) {
        this.cntOfOnlyRights = cntOfOnlyRights;
        return this;
    }

    public Report setDuplicatedCntOfLefts(int duplicatedCntOfLefts) {
        this.duplicatedCntOfLefts = duplicatedCntOfLefts;
        return this;
    }

    public Report setDuplicatedCntOfRights(int duplicatedCntOfRights) {
        this.duplicatedCntOfRights = duplicatedCntOfRights;
        return this;
    }

    public Report setCrashCntOfLefts(int crashCntOfLefts) {
        this.crashCntOfLefts = crashCntOfLefts;
        return this;
    }

    public Report setCrashCntOfRights(int crashCntOfRights) {
        this.crashCntOfRights = crashCntOfRights;
        return this;
    }

    public Report setResultData(Result resultData) {
        this.resultData = resultData;
        return this;
    }

    public int getCrashCntOfRights() {
        return crashCntOfRights;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getTotalCntOfLefts() {
        return totalCntOfLefts;
    }

    public int getTotalCntOfRights() {
        return totalCntOfRights;
    }

    public int getNormalCnt() {
        return normalCnt;
    }

    public int getCntOfOnlyLefts() {
        return cntOfOnlyLefts;
    }

    public int getCntOfOnlyRights() {
        return cntOfOnlyRights;
    }

    public int getDuplicatedCntOfLefts() {
        return duplicatedCntOfLefts;
    }

    public int getDuplicatedCntOfRights() {
        return duplicatedCntOfRights;
    }

    public int getCrashCntOfLefts() {
        return crashCntOfLefts;
    }

    public boolean isResult() {
        return result;
    }
}
