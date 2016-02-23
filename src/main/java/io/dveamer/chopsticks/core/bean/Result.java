package io.dveamer.chopsticks.core.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dveamer on 15. 12. 5.
 */
public class Result{

    private List<Chopstick> leftChopsticks;
    private HashMap<String, List<Chopstick>> leftMap;

    private List<Chopstick> rightChopsticks;
    private HashMap<String, List<Chopstick>> rightMap;

    private List<Chopstick> normalList;

    private List<Chopstick> leftNotSameKeyChopsticks;
    private List<Chopstick> leftOnlyChops;
    private List<Chopstick> leftCrashChops;
    private Map<String, List<Chopstick>> leftDuplicatedMap;

    private List<Chopstick> rightNotSameKeyChopsticks;
    private List<Chopstick> rightOnlyChops;
    private List<Chopstick> rightCrashChops;
    private Map<String, List<Chopstick>> rightDuplicatedMap;

    public List<Chopstick> getLeftChopsticks() {
        return leftChopsticks;
    }

    public void setLeftChopsticks(List<Chopstick> leftChopsticks) {
        this.leftChopsticks = leftChopsticks;
    }

    public Map<String, List<Chopstick>> getLeftDuplicatedMap() {
        return leftDuplicatedMap;
    }

    public void setLeftDuplicatedMap(Map<String, List<Chopstick>> leftDuplicatedMap) {
        this.leftDuplicatedMap = leftDuplicatedMap;
    }

    public HashMap<String, List<Chopstick>> getLeftMap() {
        return leftMap;
    }

    public void setLeftMap(HashMap<String, List<Chopstick>> leftMap) {
        this.leftMap = leftMap;
    }

    public List<Chopstick> getRightChopsticks() {
        return rightChopsticks;
    }

    public void setRightChopsticks(List<Chopstick> rightChopsticks) {
        this.rightChopsticks = rightChopsticks;
    }

    public HashMap<String, List<Chopstick>> getRightMap() {
        return rightMap;
    }

    public void setRightMap(HashMap<String, List<Chopstick>> rightMap) {
        this.rightMap = rightMap;
    }

    public List<Chopstick> getNormalList() {
        return normalList;
    }

    public void setNormalList(List<Chopstick> normalList) {
        this.normalList = normalList;
    }

    public List<Chopstick> getLeftNotSameKeyChopsticks() {
        return leftNotSameKeyChopsticks;
    }

    public void setLeftNotSameKeyChopsticks(List<Chopstick> leftNotSameKeyChopsticks) {
        this.leftNotSameKeyChopsticks = leftNotSameKeyChopsticks;
    }

    public List<Chopstick> getLeftOnlyChops() {
        return leftOnlyChops;
    }

    public void setLeftOnlyChops(List<Chopstick> leftOnlyChops) {
        this.leftOnlyChops = leftOnlyChops;
    }

    public List<Chopstick> getLeftCrashChops() {
        return leftCrashChops;
    }

    public void setLeftCrashChops(List<Chopstick> leftCrashChops) {
        this.leftCrashChops = leftCrashChops;
    }

    public List<Chopstick> getRightNotSameKeyChopsticks() {
        return rightNotSameKeyChopsticks;
    }

    public void setRightNotSameKeyChopsticks(List<Chopstick> rightNotSameKeyChopsticks) {
        this.rightNotSameKeyChopsticks = rightNotSameKeyChopsticks;
    }

    public List<Chopstick> getRightOnlyChops() {
        return rightOnlyChops;
    }

    public void setRightOnlyChops(List<Chopstick> rightOnlyChops) {
        this.rightOnlyChops = rightOnlyChops;
    }

    public List<Chopstick> getRightCrashChops() {
        return rightCrashChops;
    }

    public void setRightCrashChops(List<Chopstick> rightCrashChops) {
        this.rightCrashChops = rightCrashChops;
    }

    public Map<String, List<Chopstick>> getRightDuplicatedMap() {
        return rightDuplicatedMap;
    }

    public void setRightDuplicatedMap(Map<String, List<Chopstick>> rightDuplicatedMap) {
        this.rightDuplicatedMap = rightDuplicatedMap;
    }
}
