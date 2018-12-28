package com.wdm.tool.pb;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

/**
 * Created by wdmyong on 2018/10/06.
 */
public class JavaAndProtoModelTransferUtils {
    private static Map<Class<?>, String> javaType2ProtoTypeMap = ImmutableMap
            .<Class<?>, String> builder() //
            .put(Boolean.TYPE, "bool") //
            .put(Boolean.class, "bool") //
            .put(Double.TYPE, "double") //
            .put(Double.class, "double") //
            .put(Float.TYPE, "float") //
            .put(Float.class, "float") //
            .put(Integer.TYPE, "uint32") //
            .put(Integer.class, "uint32") //
            .put(Long.TYPE, "uint64") //
            .put(Long.class, "uint64") //
            .put(String.class, "string") //
            .build();

    public static void main(String[] args) {
        // 传入要转换的java model定义
        Class<?> javaClass = User.class;

        // 生成 proto定义
        generateProtoDefinitionClass(javaClass);

        // 生成 proto 和 java class 转换util
        generateJavaProtoTransUtilClass(javaClass);
    }

    private static void generateProtoDefinitionClass(Class<?> javaClass) {
        System.out.println("message " + generateProtoClassName(javaClass) + " {");
        int[] row = { 1 };
        Arrays.stream(javaClass.getDeclaredFields())
                .filter(field -> !Modifier.isTransient(field.getModifiers())) //
                .forEach(field -> {
                    String name = LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                    if (field.getType().isAssignableFrom(List.class)) {
                        System.out
                                .println("    repeated " + field.getDeclaringClass().getSimpleName()
                                        + " " + field.getName() + " " + " = " + (row[0]++) + ";");
                    } else {
                        String type = javaType2ProtoTypeMap.get(field.getType());
                        Preconditions.checkNotNull(type);
                        System.out.println("    " + type + " " + name + " = " + (row[0]++) + ";");
                    }
                });
        System.out.println("}");
    }

    private static String generateProtoClassName(Class<?> javaClass) {
        return javaClass.getSimpleName() + "Proto";
    }

    private static void generateJavaProtoTransUtilClass(Class<?> javaClass) {
        String javaClassName = javaClass.getSimpleName();
        String protoClassName = generateProtoClassName(javaClass);
        String javaObjectName = UPPER_CAMEL.to(LOWER_CAMEL, javaClassName);
        String protoObjectName = UPPER_CAMEL.to(LOWER_CAMEL, protoClassName);

        // 类定义
        System.out.println(
                "\n\n" + StringUtils.replace(javaClassName, "Model", "") + "CacheUtils {\n");

        // toProto 方法
        System.out.println("    public static " + protoClassName + " toProto(" + javaClassName + " "
                + javaObjectName + ") {");
        System.out.println("        return " + protoClassName + ".newBuilder() //");
        Arrays.stream(javaClass.getDeclaredFields())
                .filter(field -> !Modifier.isTransient(field.getModifiers())) //
                .forEach(field -> setProtoField(javaObjectName, field));
        System.out.println("        .build();");

        // fromProto 方法
        System.out.println("\n\n");
        System.out.println("    public static " + javaClassName + "fromProto(Object bs) {");
        System.out.println("        if (bs == null) {");
        System.out.println("            return null;\n        }");
        System.out.println("        if (bs instanceof byte[]) {\n" + "            try {");
        System.out.println("                " + protoClassName + " protoModel = " + protoClassName
                + ".parseFrom((byte[]) bs);");
        System.out.println("                " + javaClassName + " " + javaObjectName + " = new "
                + javaClassName + "();");
        Arrays.stream(javaClass.getDeclaredFields())
                .filter(field -> !Modifier.isTransient(field.getModifiers())) //
                .forEach(field -> setJavaModelField(javaObjectName, protoObjectName, field));
        System.out.println("            } catch (InvalidProtocolBufferException e) {");
        System.out.println("                throw new RuntimeException(e);\n" + "            }");
        System.out
                .println("        } else {\n" + "            throw new IllegalArgumentException(");
        System.out.println(
                "                    \"param should be of type byte[], but actual \" + bs.getClass());");

        // 类定义结尾
        System.out.println("        }\n" + "    }\n" + "}");
    }

    private static void setJavaModelField(String javaObjectName, String protoObjectName,
                                          Field field) {
        String fieldName = LOWER_CAMEL.to(UPPER_CAMEL, field.getName());
        System.out.println("                " + javaObjectName + ".set" + fieldName + "("
                + protoObjectName + ".get" + fieldName + "());");
    }

    private static void setProtoField(String javaObjectName, Field field) {
        if (field.getType().isAssignableFrom(List.class)) {
            return;
        }
        String type = javaType2ProtoTypeMap.get(field.getType());
        Preconditions.checkNotNull(type);
        String fieldName = field.getName();
        System.out.println("                .set" + LOWER_CAMEL.to(UPPER_CAMEL, fieldName) + "("
                + javaObjectName + ".get" + LOWER_CAMEL.to(UPPER_CAMEL, fieldName + "()) //"));
    }
}
