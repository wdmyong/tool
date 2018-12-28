package com.wdm.geneKey;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.mutable.MutableLong;
import org.junit.Test;

import com.google.common.base.Joiner;

import static java.lang.System.currentTimeMillis;

/**
 * Created by wdmyong on 2018/10/05.
 */
public class HbaseKeyGeneTest {
    private static final Joiner JOINER = Joiner.on("_");
    private static final long ONE_MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("00000");

    public String generateStringKey(String primaryKey, String... keys) {
        String md5 = DigestUtils.md5Hex(primaryKey);
        return JOINER.join(md5.substring(md5.length() - 4), primaryKey, keys);
    }

    public String generateStringKey(long primaryKey, String... keys) {
        return generateStringKey(String.valueOf(primaryKey), keys);
    }

    @Test
    public void geneKey() {
        MutableLong offset = new MutableLong(TimeUnit.MINUTES.toMillis(30));
        long userId = 547824405L;
        long timestamp = currentTimeMillis();
        LongStream.rangeClosed(3573800298L, 3573900298L).boxed().forEach(lsId -> {
            offset.setValue(offset.getValue() + TimeUnit.SECONDS.toMillis(30));
            String timePartition = calculateOffset(offset.getValue());
            String rowKey = generateStringKey(lsId, timePartition,
                    String.valueOf(userId), "" + timestamp);
            System.out.println("put 'test_table', '" + rowKey +"', 'f:1', '" + userId + "'");
            System.out.println("put 'test_table', '" + rowKey +"', 'f:2', '" + ThreadLocalRandom.current().nextDouble() + "'");
            System.out.println("put 'test_table', '" + rowKey +"', 'f:3', '" + offset + "'");
            System.out.println("put 'test_table', '" + rowKey +"', 'f:4', '" + timestamp + "'");
            //System.out.println("----------------------------------------------------------------------------------------------");
        });

    }

    private String calculateOffset(long timestamp) {
        long partition = timestamp / ONE_MINUTE_IN_MILLIS;
        return DECIMAL_FORMATTER.format(partition);
    }
}
