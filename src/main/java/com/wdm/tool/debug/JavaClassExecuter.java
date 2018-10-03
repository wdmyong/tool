package com.wdm.tool.debug;

import java.lang.reflect.Method;

/**
 * Created by wdmyong on 2018/10/03.
 */
public class JavaClassExecuter {

    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier classModifier = new ClassModifier(classByte);
        byte[] modifyBytes = classModifier.modifyUTF8Constant("java/lang/System", "com/wdm/tool/debug/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modifyBytes);
        try {
            Method method = clazz.getMethod("main", new Class[] { String[].class });
            method.invoke(null, new String[] { null });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HackSystem.getBufferString();
    }
}
