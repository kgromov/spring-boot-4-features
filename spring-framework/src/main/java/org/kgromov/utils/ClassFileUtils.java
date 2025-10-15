package org.kgromov.utils;

import org.kgromov.concurrentLimit.ConcurrentLimitRunner;
import org.kgromov.declarativeClient.declarative.PostClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.classfile.ClassFile;
import java.lang.classfile.CodeModel;
import java.lang.classfile.MethodModel;
import java.lang.classfile.constantpool.MemberRefEntry;
import java.lang.classfile.instruction.InvokeInstruction;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class ClassFileUtils {
    private static final Logger log = LoggerFactory.getLogger(ClassFileUtils.class);
    // preview
//    private final Supplier<Logger> loggerSupplier = () -> StableValue.supplier(() -> LoggerFactory.getLogger(ClassFileUtils.class));

    private ClassFileUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    static void main() {
//        loggerSupplier.get().info("StableValue feature");
        getClassInfo(classToBytes(ConcurrentLimitRunner.class)).forEach(classInfo -> log.info("{}", classInfo));
    }

    public static List<MethodInfo> getClassInfo(byte[] classAsBytes) {
        return ClassFile.of()
                .parse(classAsBytes)
                .methods().stream()
                .flatMap(MethodModel::elementStream)
                .flatMap(keepOnly(CodeModel.class))
                .flatMap(CodeModel::elementStream)
                .flatMap(keepOnly(InvokeInstruction.class))
                .map(MethodInfo::of)
                .toList();
    }

    public static <T> byte[] classToBytes(Class<T> clazz) {
        try (InputStream resourceAsStream = clazz.getClassLoader()
                .getResourceAsStream(clazz.getName().replace('.', '/').concat(".class"))) {
            return resourceAsStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <E, T> Function<E, Stream<T>> keepOnly(Class<T> type) {
        return element -> type.isInstance(element)
                ? Stream.of(type.cast(element))
                : Stream.empty();
    }

    record MethodInfo(String type, String method, String returnType) {

        public static MethodInfo of(InvokeInstruction instruction) {
            MemberRefEntry method = instruction.method();
            return new MethodInfo(
                    instruction.type().stringValue(),
                    method.name().stringValue(),
                    instruction.typeSymbol().returnType().displayName()
            );
        }
    }
}
