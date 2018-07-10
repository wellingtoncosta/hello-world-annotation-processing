package helloworld.processing;

import javax.lang.model.element.Element;

/**
 * @author Wellington Costa on 10/07/18.
 */
public class ClassInfo {

    public final String packageName;
    public final String className;
    public final Element element;

    ClassInfo(String packageName, Element element) {
        this.packageName = packageName;
        this.className = element.getSimpleName().toString() + "HelloWorld";
        this.element = element;
    }

}
