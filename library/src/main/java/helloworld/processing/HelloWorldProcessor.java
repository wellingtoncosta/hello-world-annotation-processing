package helloworld.processing;

import com.google.auto.common.SuperficialValidation;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Wellington Costa on 10/07/18.
 */
@AutoService(Processor.class)
public class HelloWorldProcessor extends AbstractProcessor {

    private Messager messager;
    private Elements elementUtils;
    private Filer filer;

    @Override public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(HelloWorld.class.getName());
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.elementUtils = processingEnv.getElementUtils();
        this.filer = processingEnv.getFiler();
    }

    @Override public boolean process(
        Set<? extends TypeElement> annotations,
        RoundEnvironment env
    ) {
        List<ClassInfo> classesInfo = new ArrayList<>();
        Set<? extends Element> elements = env.getElementsAnnotatedWith(HelloWorld.class);
        for(Element element : elements) {
            if (!SuperficialValidation.validateElement(element)) continue;
            String packageName = elementUtils.getPackageOf(element).toString();
            classesInfo.add(new ClassInfo(packageName, element));
        }
        writeJava(classesInfo);
        return false;
    }

    private void writeJava(List<ClassInfo> classesInfo) {
        classesInfo.forEach(classInfo -> {
            try {
                JavaFile.builder(
                        classInfo.packageName,
                        JavaFiler.cookJava(classInfo)
                )
                        .addFileComment("Generated code. Do not modify!")
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                messager.printMessage(
                        Diagnostic.Kind.ERROR,
                        "Unable to generate the builder class.",
                        classInfo.element
                );
            }
        });
    }

}