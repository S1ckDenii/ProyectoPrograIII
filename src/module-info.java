module ClaseNumero2 {

	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.annotation;
	requires javafx.base;

	exports domain;

	opens business to javafx.graphics, javafx.fxml;
}
