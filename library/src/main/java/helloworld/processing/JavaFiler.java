package helloworld.processing;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author Wellington Costa on 10/07/18.
 */
public final class JavaFiler {

    private JavaFiler() { }

    public static TypeSpec cookJava(ClassInfo classInfo) {
        return TypeSpec.classBuilder(classInfo.className)
                .addModifiers(PUBLIC)
                .addMethod(createConstructor())
                .build();
    }

    private static MethodSpec createConstructor() {
        return MethodSpec.constructorBuilder()
                .addStatement(
                        "System.out.println($S)",
                        "Hello, world!"
                ).build();
    }

}
