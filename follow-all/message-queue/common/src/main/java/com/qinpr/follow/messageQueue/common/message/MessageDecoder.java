package com.qinpr.follow.messageQueue.common.message;

import com.qinpr.follow.messageQueue.common.UtilAll;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by qinpr on 2019/2/21.
 */
public class MessageDecoder {
    public final static int MSG_ID_LENGTH = 8 + 8;

    public final static Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public static String createMessageId(final ByteBuffer input, final ByteBuffer addr, final long offset) {
        input.flip();
        input.limit(MessageDecoder.MSG_ID_LENGTH);

        input.put(addr);
        input.putLong(offset);
        return UtilAll.bytes2string(input.array());
    }
}
