module com.bio.biosmart {
    requires javafx.controls;
    requires org.fxmisc.richtext;
    requires org.fxmisc.flowless;
    requires reactfx;

    exports com.bio.biosmart;
    exports com.bio.biosmart.UI to javafx.graphics;
}