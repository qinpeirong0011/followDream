package com.qinpr.follow.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Created by qinpr on 2020/4/15.
 */
public class JVMDynamicClassOOM {
    public static void main(String[] args) {
        URL url = null;
        ArrayList<ClassLoader> classLoaders = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaders.add(loader);
                loader.loadClass("com.qinpr.follow.jvm.Test");
            }
        } catch(Exception e) {
           e.printStackTrace();
        }
    }
}
