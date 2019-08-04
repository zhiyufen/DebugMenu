package com.android.zhiyufen.debugmenu_compiler;

import com.android.zhiyufen.debugmenu.annotation.DebugMenuFragment;
import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.android.zhiyufen.debugmenu.annotation.DebugMenuFragment")
public class DebugMenuFragmentProcessor extends AbstractProcessor {
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //init tool
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mMessager.printMessage(Diagnostic.Kind.WARNING, "yufen/init");
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        mMessager.printMessage(Diagnostic.Kind.WARNING, "yufen/process");
        mMessager.printMessage(Diagnostic.Kind.NOTE,"[yufen]process..start");

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(DebugMenuFragment.class);
        for (Element element : annotatedElements) {
            DebugMenuFragment annotation = element.getAnnotation(DebugMenuFragment.class);
        }

        return false;
    }
}

