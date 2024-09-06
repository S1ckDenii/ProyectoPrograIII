package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Food {

	private String description;
	private double price;

	@JsonIgnore
	private BooleanProperty solicitar;

	public Food() {
		this.solicitar = new SimpleBooleanProperty(false);
	}

	public Food(String description, double price) {
		this.description = description;
		this.price = price;
		this.solicitar = new SimpleBooleanProperty(false);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JsonIgnore
	public BooleanProperty solicitarProperty() {
		return solicitar;
	}

	@JsonIgnore
	public boolean isSolicitar() {
		return solicitar.get();
	}

	@JsonIgnore
	public void setSolicitar(boolean solicitar) {
		this.solicitar.set(solicitar);
	}

	@Override
	public String toString() {
		return "Alimento [description=" + description + ", price=" + price + "]";
	}
}