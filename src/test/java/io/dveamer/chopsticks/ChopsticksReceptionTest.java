package io.dveamer.chopsticks;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Report;
import org.junit.Before;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class ChopsticksReceptionTest {

    String basedPath = "/data/test/";
    String normalFile = "normal";
    String rightOnlyFile = "rightOnly";
    String leftOnlyFile = "leftOnly";
    String rightCrashFile = "rightCrash";
    String leftCrashFile = "leftCrash";

    int defaultSize = 10000;
    int normalFileCnt = 12;

    int rightOnlyFileCnt = 2;
    int leftOnlyFileCnt = 3;

    int crashFileCnt = 1;
    int leftDuplicatedFileCnt = 3;
    int rightDuplicatedFileCnt = 2;

    final Function<String, Chopstick> parse = s -> {
        String[] values = s.split(":");
        return new TestChopstick(values[0], values[1]);
    };

    final UnaryOperator<Chopstick> mapper = c ->{
        TestChopstick ct = (TestChopstick) c;
        ct.value = ct.getValue() + "__";
        return ct;
    };

    final BiFunction<Chopstick,Chopstick,Boolean> compare = (c1, c2) -> {
        TestChopstick c1t = (TestChopstick) c1;
        TestChopstick c2t = (TestChopstick) c2;

        return c1t.getKey().equals(c2t.getKey()) && c1t.getValue().equals(c2t.getValue());
    };

    @Before
    public void before() throws Exception{

        long start = System.currentTimeMillis();
        makeFiles(0, normalFileCnt, normalFile);
        makeFiles(normalFileCnt+1, rightOnlyFileCnt, rightOnlyFile);
        makeFiles(normalFileCnt+rightOnlyFileCnt+1, leftOnlyFileCnt, leftOnlyFile);
        makeFiles(normalFileCnt+rightOnlyFileCnt+leftOnlyFileCnt+1, crashFileCnt, rightCrashFile);
        makeFiles(normalFileCnt+rightOnlyFileCnt+leftOnlyFileCnt+1, crashFileCnt, leftCrashFile);

        System.out.println("before : " + (System.currentTimeMillis()-start) + " ms");
    }

    public void makeFiles(int start, int fileCnt, String fileName){
        IntStream.range(0,fileCnt).forEach(index -> {
            Path path = Paths.get(basedPath, makeFileName(fileName,index));
            if (!path.toFile().exists()) {
                makeChopsticksAndWriteFile((start+index) * defaultSize, (start+index + 1) * defaultSize - 1, path);
            }
        });
    }

    public String makeFileName(String fileName, int index){
        return fileName + "_" + index;
    }

    public void makeChopsticksAndWriteFile(int start, int end, Path path ){

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            IntStream.rangeClosed(start, end)
                    .parallel()
                    .forEach(index->{
                        try {
                            writer.write(Integer.toString(index) + ":value_" + index + "_" + System.currentTimeMillis() + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @org.junit.Test
    public void testNormalFile2File() throws Exception {

        System.out.println("######################## Normal" );

        final ChopsticksReception reception = new ChopsticksReception();

        IntStream.range(0, normalFileCnt).forEach(index->{
            reception.addLeftFileLineParser(basedPath + makeFileName(normalFile,index), parse)
                    .addRightFileLineParser(basedPath + makeFileName(normalFile,index), parse);
        });

        final Long start = System.currentTimeMillis();

        Report report = reception
                .setCompare(compare)
                .setLeftMapper(mapper)
                .setRightMapper(mapper)
                .execute();


        System.out.println((System.currentTimeMillis() - start) + " ms ");

        System.out.println("defaultSize : " + defaultSize);
        System.out.println("normalFileCnt : " + normalFileCnt);

        System.out.println("Report : " + report.printReport());
        System.out.println("leftChop 0 : " + ((TestChopstick) report.getResultData().getLeftChopsticks().get(0)).print());
        System.out.println("rightChop 0 : " + ((TestChopstick) report.getResultData().getRightChopsticks().get(0)).print());

        assert(report.getNormalCnt() == normalFileCnt*defaultSize);
        assert(report.getTotalCntOfLefts() == normalFileCnt*defaultSize);
        assert(report.getTotalCntOfRights() == normalFileCnt*defaultSize);

        assert(report.isResult());
        assert(!report.isError());

        assert(report.getCrashCntOfLefts()==0);
        assert(report.getCrashCntOfRights()==0);
        assert(report.getCntOfOnlyLefts()==0);
        assert(report.getCntOfOnlyRights()==0);
        assert(report.getDuplicatedCntOfLefts()==0);
        assert(report.getDuplicatedCntOfRights()==0);


    }

    @org.junit.Test
    public void testGapFile2File() throws Exception {

        System.out.println("######################## Gap");


        final ChopsticksReception reception = new ChopsticksReception();

        IntStream.range(0, normalFileCnt).forEach(index->{
            reception.addLeftFileLineParser(basedPath + makeFileName(normalFile, index), parse)
                    .addRightFileLineParser(basedPath + makeFileName(normalFile, index), parse);
        });

        IntStream.range(0, leftOnlyFileCnt).forEach(index->{
            reception.addLeftFileLineParser(basedPath + makeFileName(leftOnlyFile, index), parse);
        });

        IntStream.range(0, rightOnlyFileCnt).forEach(index->{
            reception.addRightFileLineParser(basedPath + makeFileName(rightOnlyFile, index), parse);
        });

        IntStream.range(0, crashFileCnt).forEach(index -> {
            reception.addLeftFileLineParser(basedPath + makeFileName(leftCrashFile, index), parse);
            reception.addRightFileLineParser(basedPath + makeFileName(rightCrashFile, index), parse);
        });

        List<Integer> leftRandom = new Random().ints(leftDuplicatedFileCnt, 0, normalFileCnt - 1)
                                                .boxed()
                                                .collect(Collectors.toList());

        leftRandom.forEach(index -> {
            String dupFileName = basedPath + makeFileName(normalFile, index);
            System.out.format("Left Duplicated File Name : %s\n", dupFileName);
            reception.addLeftFileLineParser(dupFileName, parse);
        });

        List<Integer> rightRandom = new Random().ints(rightDuplicatedFileCnt, 0, normalFileCnt - 1)
                .boxed()
                .collect(Collectors.toList());

        rightRandom.forEach(index -> {
            String dupFileName = basedPath + makeFileName(normalFile, index);
            System.out.format("Right Duplicated File Name : %s\n", dupFileName);
            reception.addRightFileLineParser(dupFileName, parse);
        });




        final Long start = System.currentTimeMillis();

        Report report = reception
                .setCompare(compare)
                .setLeftMapper(mapper)
                .setRightMapper(mapper)
                .execute();

        System.out.println((System.currentTimeMillis() - start) + " ms ");

        System.out.println("defaultSize : " + defaultSize);
        System.out.println("normalFileCnt : " + normalFileCnt);
        System.out.println("leftOnlyFileCnt : " + leftOnlyFileCnt);
        System.out.println("rightOnlyFileCnt : " + rightOnlyFileCnt);
        System.out.println("crashFileCnt : " + crashFileCnt);
        System.out.println("leftDuplicatedFileCnt : " + leftDuplicatedFileCnt);
        System.out.println("rightDuplicatedFileCnt : " + rightDuplicatedFileCnt);

        System.out.println("Report : " + report.printReport());
        System.out.println("leftChop 0 : " + ((TestChopstick) report.getResultData().getLeftChopsticks().get(0)).print());
        System.out.println("rightChop 0 : " + ((TestChopstick) report.getResultData().getRightChopsticks().get(0)).print());

        assert(report.getNormalCnt() == normalFileCnt*defaultSize);
        assert(report.getTotalCntOfLefts() == defaultSize*(normalFileCnt+leftOnlyFileCnt+crashFileCnt+leftDuplicatedFileCnt));
        assert(report.getTotalCntOfRights() == defaultSize*(normalFileCnt+rightOnlyFileCnt + crashFileCnt +rightDuplicatedFileCnt));

        assert(!report.isResult());
        assert(!report.isError());

        assert(report.getCrashCntOfLefts()==crashFileCnt*defaultSize);
        assert(report.getCrashCntOfRights()== crashFileCnt*defaultSize);
        assert(report.getCntOfOnlyLefts()==leftOnlyFileCnt*defaultSize);
        assert(report.getCntOfOnlyRights()==rightOnlyFileCnt*defaultSize);


        Set<Integer> hashSet = new HashSet<>();
        hashSet.addAll(leftRandom);
        assert(report.getDuplicatedCntOfLefts()== (hashSet.size() + leftDuplicatedFileCnt)*defaultSize);

        hashSet.clear();
        hashSet.addAll(rightRandom);
        assert(report.getDuplicatedCntOfRights()== (hashSet.size() +rightDuplicatedFileCnt)*defaultSize);

    }

    public class TestChopstick implements Chopstick{

        private final String key;
        private String value;

        public TestChopstick(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return key;
        }

        public String getValue(){
            return value;
        }

        public String print(){
            return key + ":" + value;
        }

    }

}