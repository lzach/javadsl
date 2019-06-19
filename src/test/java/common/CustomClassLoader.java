package common;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    @Override
    public synchronized Class loadClass(String name) throws ClassNotFoundException {
        if ( ! name.equals("ExpansionExpansion") ) {
            return getParent().loadClass(name);
        }
        byte[] b = loadClassFromFile(name);
        Class c = defineClass(name, b, 0, b.length);
        //resolveClass(c);
        return c;
    }

    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("target/classes/" + fileName + ".class"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}