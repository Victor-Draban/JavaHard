package ua.nure.draban.Practice6.part3;

public class Part3 {
	
	public static void main(String[] args) {
		Car car1 = new Car("ferrary");
		Car car2 = new Car("BMW");
		Car car3 = new Car("BMW");
		Parking parking = new Parking();
        parking.carParking(0, car1);
        parking.carParking(0, car2);
        parking.carParking(1, car3);
		parking.stateParking();
		parking.carGoOut(car1);
		parking.stateParking();
	}

}
