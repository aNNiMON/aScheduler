-printmapping out.map
-printusage out.txt
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,Deprecated,Signature,InnerClasses
-keepattributes *Annotation*

#-printseeds out.seeds

-keepclasseswithmembers public class * {
    public static void main(java.lang.String[]);
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Your application may contain more items that need to be preserved; 
# typically classes that are dynamically created using Class.forName:

# -keep public class mypackage.MyClass
# -keep public interface mypackage.MyInterface
# -keep public class * implements mypackage.MyInterface


-optimizationpasses 9
-allowaccessmodification
-dontusemixedcaseclassnames
# -repackageclasses ''